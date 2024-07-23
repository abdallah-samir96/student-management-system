package com.bank.boubyan.security.manager;

import com.bank.boubyan.dto.UserDTO;
import com.bank.boubyan.security.dto.JWTCredential;
import com.bank.boubyan.security.utils.SecurityLookup;
import com.bank.boubyan.security.utils.TokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
@Priority(1)
public class JWTAuthenticationManager implements HttpAuthenticationMechanism {
    private final static Logger logger = Logger.getLogger(JWTAuthenticationManager.class.getName());

    private final IdentityStoreHandler identityStoreHandler;

    private final TokenUtils tokenUtils;

    @Inject
    public JWTAuthenticationManager(IdentityStoreHandler handler, TokenUtils tokenUtils) {
        this.identityStoreHandler = handler;
        this.tokenUtils = tokenUtils;
    }
    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) {
        try {
            logRequestDetails(request);
            var userDTO = getUserDTO(request);
            var token = extractRequestToken(httpMessageContext);
            if (userDTO.getUsername() != null && userDTO.getPassword() != null) {
                logger.log(Level.INFO, "credentials : {0}, {1}", new String[]{userDTO.getUsername(), userDTO.getPassword()});
                CredentialValidationResult credentialValidationResult =
                        identityStoreHandler.validate(new UsernamePasswordCredential(userDTO.getUsername(), userDTO.getPassword()));
                if (credentialValidationResult.getStatus() == CredentialValidationResult.Status.VALID) {
                    return createToken(credentialValidationResult, httpMessageContext);
                }
                return httpMessageContext.responseUnauthorized();
            } else if (token != null) {
                return validateToken(token, httpMessageContext);
            } else if (httpMessageContext.isProtected()) {
                return httpMessageContext.responseUnauthorized();
            }
            return httpMessageContext.doNothing();
        } catch (IOException e) {
            logger.log(Level.SEVERE,"Message = {0}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Used for logging the request
     * */
    private void logRequestDetails(HttpServletRequest request) throws IOException {
        String requestBody = new String(request.getInputStream().readAllBytes());
        logger.log(Level.INFO,"request URL = {0}", request.getRequestURI());
        logger.log(Level.INFO, "Http Method = {0}", request.getMethod());
        logger.log(Level.INFO, "Request Body = {0}", requestBody);
    }
    private UserDTO getUserDTO(HttpServletRequest request) throws IOException {
        String requestBody = new String(request.getInputStream().readAllBytes());
        UserDTO userDTO = new ObjectMapper().readValue(requestBody, UserDTO.class);
        logger.log(Level.INFO, "UserInfo= {0}", userDTO);
        return userDTO;
    }

    /**
     * Used to extract the token from the request header
     * Authorization Bearer header.payload.signature
     * */
    private String extractRequestToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(SecurityLookup.AUTHORIZATION_HEADER.title);
        if (authorizationHeader != null && authorizationHeader.startsWith(SecurityLookup.BEARER.title)) {
             return authorizationHeader.substring(SecurityLookup.BEARER.title.length());
        }
        return null;
    }
    /**
     * Create the JWT using CredentialValidationResult received from
     * IdentityStoreHandler
     *
     * @param result  the result from validation of UsernamePasswordCredential
     * @param context the httpContext
     * @return the AuthenticationStatus to notify the container
     */
    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        logger.info("The groups that the used belongs to are : " + result.getCallerGroups());
        String jwt = tokenUtils.generateToken(result.getCallerPrincipal().getName());
        context.getResponse().setHeader(SecurityLookup.AUTHORIZATION_HEADER.title, SecurityLookup.BEARER.title + jwt);
        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    /**
     * Used to validate the token if found in the request and notify the container with the credentials
     * */
    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tokenUtils.validateToken(token)) {
                JWTCredential credential = tokenUtils.getCredential(token);
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());
            }
            return context.responseUnauthorized();
        } catch (ExpiredJwtException eje) {
            logger.log(Level.INFO, "Security exception for user {0} - {1}", new String[]{eje.getClaims().getSubject(), eje.getMessage()});
            return context.responseUnauthorized();
        }
    }
}

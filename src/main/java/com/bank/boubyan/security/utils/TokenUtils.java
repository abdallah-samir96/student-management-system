package com.bank.boubyan.security.utils;

import com.bank.boubyan.security.dto.JWTCredential;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Dependent
public class TokenUtils {
    private static final Logger logger = Logger.getLogger(TokenUtils.class.getName());
    private static final String SECRET_KEY = "463b0f67-c89e-4c24-8be9-0714c596985c";
    private long timeToLive;
    @PostConstruct
    public void init() {
        this.timeToLive = TimeUnit.MINUTES.toMillis(5);
    }
    /**
     * This method used to generate the token using the email
     * @param email representing the username or the subject
     * @return String represent the jwt token
     * */
    public String generateToken(String email) {
        long now = (new Date()).getTime();
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", "user_hd")
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setIssuedAt(new Date())
                .setExpiration(new Date(now + timeToLive))
                .compact();
    }
    /**
     * Used to validate token for signature, expiration date and so on
     * @param token the jwt token
     * @return boolean represent the status of the token
     * */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            logger.log(Level.INFO, "Invalid JWT signature: {0}", e.getMessage());
            return false;
        }
    }

    /**
     * This method used to return JWTCredential which represent the principal(current logged-in User), authorities
     * @param token jwt token
     * @return JWTCredential
     * */
    public JWTCredential getCredential(String token) {
        Claims claims = getClaims(token);
        Set<String> authorities = Set.of(claims.get("roles").toString());
        return new JWTCredential(claims.getSubject(), authorities);
    }
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}

package com.bank.boubyan.security.utils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import java.util.HashMap;
import java.util.Map;

import static javax.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import static javax.security.enterprise.identitystore.CredentialValidationResult.NOT_VALIDATED_RESULT;

@RequestScoped
public class AuthenticationIdentityStore implements IdentityStore {

    private Map<String, String> inMemoryCredentials;

    @PostConstruct
    public void init() {
        // needs to separate them into a database, file
        inMemoryCredentials = new HashMap<>();
        inMemoryCredentials.put("abdallahsameer22@gmail.com", "12345");
        inMemoryCredentials.put("ahmedmohamed@gmail.com", "1234");
    }
    @Override
    public CredentialValidationResult validate(Credential credential) {
        if(credential instanceof UsernamePasswordCredential) {
            return validateUserNamePassword((UsernamePasswordCredential) credential);
        } else {
            return NOT_VALIDATED_RESULT;
        }
    }

    private CredentialValidationResult validateUserNamePassword(UsernamePasswordCredential usernamePasswordCredential) {
        // get the password from the datastore using the email of it in UsernamePasswordCredential
        String expectedPassword = inMemoryCredentials.get(usernamePasswordCredential.getCaller());
        if (expectedPassword != null  && expectedPassword.equals(usernamePasswordCredential.getPasswordAsString())) {
           return new CredentialValidationResult(usernamePasswordCredential.getCaller());
        }
        return INVALID_RESULT;
    }
}

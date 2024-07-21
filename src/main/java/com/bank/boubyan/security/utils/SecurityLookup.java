package com.bank.boubyan.security.utils;

public enum SecurityLookup {

    AUTHORIZATION_HEADER("Authorization"),
    BEARER("Bearer ")
    ;
    public final String title;
    SecurityLookup(String title) {
        this.title = title;
    }
}

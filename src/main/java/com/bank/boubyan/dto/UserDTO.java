package com.bank.boubyan.dto;

public class UserDTO {
    private String username;
    private String password;

    private String token;

    public UserDTO() {
    }
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setToken(String token) {
        this.token = token;
    }

}

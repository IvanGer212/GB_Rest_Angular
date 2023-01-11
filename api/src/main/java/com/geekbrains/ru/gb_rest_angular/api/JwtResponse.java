package com.geekbrains.ru.gb_rest_angular.api;


public class JwtResponse {
    private String token;

    public JwtResponse(String token, String username) {
        this.token = token;
        this.username = username;
    }

    private String username;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

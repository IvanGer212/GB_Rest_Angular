package com.geekbrains.ru.gb_rest_angular.api;


public class JwtResponse {
    private Boolean isAdmin;
    private String token;
    private String username;

    public JwtResponse(String token, String username, Boolean isAdmin) {
        this.token = token;
        this.username = username;
        this.isAdmin = isAdmin;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

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

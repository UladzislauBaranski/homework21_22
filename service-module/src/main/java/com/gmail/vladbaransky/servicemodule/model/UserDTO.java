package com.gmail.vladbaransky.servicemodule.model;


import com.gmail.vladbaransky.repositorymodule.model.RoleEnum;

public class UserDTO {

    private String password;
    private String username;
    private RoleEnum role;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}

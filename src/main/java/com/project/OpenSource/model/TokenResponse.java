package com.project.OpenSource.model;

public class TokenResponse {

    private Integer userId;
    private String role;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "TokenResponse{" +
                "userId=" + userId +
                ", role='" + role + '\'' +
                '}';
    }
}

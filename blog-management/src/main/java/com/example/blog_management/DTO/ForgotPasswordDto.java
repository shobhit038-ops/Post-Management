package com.example.blog_management.DTO;

public class ForgotPasswordDto {
    String email;

    public ForgotPasswordDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

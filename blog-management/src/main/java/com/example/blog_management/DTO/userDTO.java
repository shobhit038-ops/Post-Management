package com.example.blog_management.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "USER",
        description = "User realted information"
)
public class userDTO {
    @Schema(
            description = "User id"
    )
    private int id;
    @Schema(description = "User name of user")
    private String username;
    @Schema(
            description = "Email of user"
    )
    private String Email;

    public userDTO(int id, String username, String email) {
        this.id = id;
        this.username = username;
        Email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

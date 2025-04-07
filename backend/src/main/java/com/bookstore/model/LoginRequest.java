package com.bookstore.model;

import lombok.Data; // ✅ wichtig für @Data

@Data
public class LoginRequest {
    private String username;
    private String password;
}

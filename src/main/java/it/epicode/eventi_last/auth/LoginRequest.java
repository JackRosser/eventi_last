package it.epicode.eventi_last.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}

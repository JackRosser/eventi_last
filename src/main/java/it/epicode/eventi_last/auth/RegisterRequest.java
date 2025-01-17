package it.epicode.eventi_last.auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
}

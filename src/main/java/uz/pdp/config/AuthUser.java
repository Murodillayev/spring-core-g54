package uz.pdp.config;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AuthUser {
    private String id = UUID.randomUUID().toString();
    private String fullName;
    private String username;
    private String password;
    private String role;

    public AuthUser(String fullName, String username, String password, String role) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}

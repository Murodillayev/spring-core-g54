package uz.pdp;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AuthUser {
    private String id = UUID.randomUUID().toString();
    private String fullName;
    private String username;
    private String password;
    private String role;
    private List<String> permissions;

    public AuthUser(String fullName, String username, String password, String role) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.permissions = Collections.emptyList();
    }

    public AuthUser(String fullName, String username, String password, String role, List<String> permissions) {
        this(fullName, username, password, role);
        this.permissions = permissions;
    }
}

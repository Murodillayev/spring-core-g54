package uz.pdp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUser {
    private String id;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String imageUrl;

}

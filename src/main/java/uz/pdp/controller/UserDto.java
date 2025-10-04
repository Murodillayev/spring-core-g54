package uz.pdp.controller;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private MultipartFile image;
}

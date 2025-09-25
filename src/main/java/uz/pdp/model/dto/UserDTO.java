package uz.pdp.model.dto;

import lombok.*;
import uz.pdp.model.enums.AuthRole;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private AuthRole role;

}

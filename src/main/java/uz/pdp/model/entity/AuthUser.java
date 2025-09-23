package uz.pdp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.model.entity.base.BaseEntity;
import uz.pdp.model.enums.AuthRole;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser extends BaseEntity {
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private AuthRole role;
}

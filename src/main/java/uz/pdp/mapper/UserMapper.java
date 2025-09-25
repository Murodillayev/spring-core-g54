package uz.pdp.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.model.dto.UserDTO;
import uz.pdp.model.entity.AuthUser;

@Component
public class UserMapper {
    public AuthUser fromDto(UserDTO dto) {
        AuthUser authUser = new AuthUser();
        authUser.setId(dto.getId());
        authUser.setUsername(dto.getUsername());
        authUser.setPassword(dto.getPassword());
        authUser.setFullName(dto.getFullName());
        authUser.setPhone(dto.getPhone());
        authUser.setRole(dto.getRole());
        return authUser;
    }

    public UserDTO toDto(AuthUser save) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(save.getId());
        userDTO.setUsername(save.getUsername());
        userDTO.setPassword(save.getPassword());
        userDTO.setFullName(save.getFullName());
        userDTO.setPhone(save.getPhone());
        userDTO.setRole(save.getRole());
        return userDTO;
    }
}

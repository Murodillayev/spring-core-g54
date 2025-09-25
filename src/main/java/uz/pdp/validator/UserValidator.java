package uz.pdp.validator;

import org.springframework.stereotype.Component;
import uz.pdp.model.dto.UserDTO;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.repository.UserRepository;

@Component
public class UserValidator {

    public final UserRepository repository;
    public UserValidator(UserRepository repository) {
        this.repository = repository;
    }
    public void validateOnCreate(UserDTO dto) {
        if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (dto.getPassword() == null || dto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }

    public AuthUser ExistAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        );
    }
}

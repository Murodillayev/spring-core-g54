package uz.pdp.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.repository.AuthUserRepository;

@Component
@RequiredArgsConstructor
public class AuthUserValidator {

    private final AuthUserRepository repository;


    public AuthUser existsAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("User with id " + id + " not found")
        );
    }


}

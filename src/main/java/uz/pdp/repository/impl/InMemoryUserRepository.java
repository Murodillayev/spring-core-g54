package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import org.springframework.validation.Validator;
import uz.pdp.model.dto.UserDTO;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.model.enums.AuthRole;
import uz.pdp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryUserRepository implements UserRepository {




    public static final List<AuthUser> users =new ArrayList<>(List.of(
            new AuthUser("Admin","Admin","Admin","991112233", AuthRole.ADMIN),
            new AuthUser("Muhammadali2007","root123","Yoqubjov Muhammadali","991709035",AuthRole.MANAGER),
            new AuthUser("negga","root123","Ali Aliev","112223344",AuthRole.SELLER)

    ));

    @Override
    public AuthUser save(AuthUser authUser) {
        users.add(authUser);
        return authUser;
    }

    @Override
    public Optional<AuthUser> findById(String id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<AuthUser> findAll() {
        return users;
    }

    @Override
    public void delete(AuthUser authUser) {
        users.removeIf(u -> u.getId().equals(authUser.getId()));
    }
}

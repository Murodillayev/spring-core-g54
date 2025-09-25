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




    public static final List<UserDTO> users =new ArrayList<>(List.of(
            new UserDTO(UUID.randomUUID().toString(),"Admin","Admin","Admin","991112233", AuthRole.ADMIN),
            new UserDTO(UUID.randomUUID().toString(),"Muhammadali2007","root123","Yoqubjov Muhammadali","991709035",AuthRole.MANAGER),
            new UserDTO(UUID.randomUUID().toString(),"negga","root123","Ali Aliev","112223344",AuthRole.SELLER)

    ));

    @Override
    public AuthUser save(AuthUser authUser) {
        users.add(new UserDTO(
                UUID.randomUUID().toString(),
                authUser.getUsername(),
                authUser.getPassword(),
                authUser.getFullName(),
                authUser.getPhone(),
                authUser.getRole()
        ));
        return authUser;
    }

    @Override
    public Optional<AuthUser> findById(String id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .map(u -> new AuthUser(
                        u.getUsername(),
                        u.getPassword(),
                        u.getFullName(),
                        u.getPhone(),
                        u.getRole()
                ));
    }

    @Override
    public List<UserDTO> findAll() {
        return users;
    }

    @Override
    public void delete(AuthUser authUser) {
        users.removeIf(u -> u.getId().equals(authUser.getId()));
    }
}

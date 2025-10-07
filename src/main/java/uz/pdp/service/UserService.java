package uz.pdp.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.mapper.UserMapper;
import uz.pdp.model.dto.UserDTO;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.model.enums.AuthRole;
import uz.pdp.repository.AuthUserRepository;
import uz.pdp.validator.UserValidator;

import java.util.List;
import java.util.UUID;

@Service
public class UserService extends AbstractService<
        AuthUserRepository,
        UserMapper,
        UserValidator> implements CrudService<UserDTO,UserDTO,UserDTO,String>{
    private PasswordEncoder passwordEncoder;
    protected UserService(@Qualifier("authUserRepositoryImpl") AuthUserRepository repository, UserMapper mapper, UserValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public UserDTO create(UserDTO dto) {
        validator.validateOnCreate(dto);
        AuthUser authUser = mapper.fromDto(dto);
        authUser.setId(UUID.randomUUID().toString());
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        return mapper.toDto(repository.save(authUser));
    }

    @Override
    public UserDTO get(String id) {
        return mapper.toDto(validator.ExistAndGet(id));
    }

    @Override
    public List<UserDTO> getAll(String search) {
        return mapper.toDtoList(repository.findAll().stream()
                .filter(
                        u->u.getUsername().toLowerCase().contains(search.toLowerCase())
                ).toList());
    }

    @Override
    public UserDTO update(UserDTO dto, String id) {
        AuthUser authUser = validator.ExistAndGet(id);
        authUser.setFullName(dto.getFullName());
        authUser.setPassword(dto.getPassword());
        authUser.setPhone(dto.getPhone());
        authUser.setUsername(dto.getUsername());
        authUser.setRole(dto.getRole());
        return mapper.toDto(repository.save(authUser));
    }

    @Override
    public void delete(String id) {
        AuthUser authUser = validator.ExistAndGet(id);
        repository.delete(authUser);
    }

    public List<AuthUser> getAllTheCashiers() {
        return repository.findAll().stream()
                .filter(u -> u.getRole().equals(AuthRole.SELLER))
                .toList();
    }
}

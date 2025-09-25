package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.mapper.UserMapper;
import uz.pdp.model.dto.UserDTO;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.repository.UserRepository;
import uz.pdp.validator.UserValidator;

import java.util.List;

import static uz.pdp.repository.impl.InMemoryUserRepository.users;

@Service
public class UserService extends AbstractService<
        UserRepository,
        UserMapper,
        UserValidator> implements CrudService<UserDTO,UserDTO,UserDTO,String>{

    protected UserService(UserRepository repository, UserMapper mapper, UserValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public UserDTO create(UserDTO dto) {
        validator.validateOnCreate(dto);
        AuthUser authUser = mapper.fromDto(dto);
        return mapper.toDto(repository.save(authUser));
    }

    @Override
    public UserDTO get(String id) {
        return mapper.toDto(validator.ExistAndGet(id));
    }

    @Override
    public List<UserDTO> getAll() {
        return users;
    }

    @Override
    public UserDTO update(UserDTO dto, String id) {
        AuthUser authUser = validator.ExistAndGet(id);
        authUser.setFullName(dto.getFullName());
        authUser.setPassword(dto.getPassword());
        authUser.setPhone(dto.getPhone());
        authUser.setId(dto.getId());
        authUser.setUsername(dto.getUsername());
        authUser.setRole(dto.getRole());
        return mapper.toDto(repository.save(authUser));
    }

    @Override
    public void delete(String id) {
        AuthUser authUser = validator.ExistAndGet(id);
        repository.delete(authUser);
    }
}

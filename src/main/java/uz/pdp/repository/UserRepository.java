package uz.pdp.repository;

import uz.pdp.model.dto.UserDTO;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.model.entity.SaleItem;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    AuthUser save(AuthUser authUser);

    Optional<AuthUser> findById(String id);

    List<AuthUser> findAll();

    void  delete(AuthUser authUser);
}

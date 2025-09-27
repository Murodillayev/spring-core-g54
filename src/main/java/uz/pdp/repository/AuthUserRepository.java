package uz.pdp.repository;

import uz.pdp.model.entity.AuthUser;

import java.util.List;
import java.util.Optional;

public interface AuthUserRepository {
    AuthUser save(AuthUser authUser);

    Optional<AuthUser> findById(String id);

    List<AuthUser> findAll();

    void  delete(AuthUser authUser);
}

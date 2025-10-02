package uz.pdp;

import java.util.Optional;

public interface AuthUserRepository {
    Optional<AuthUser> findByUsername(String username);

    void save(AuthUser authUser);
}

package uz.pdp;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryRepository implements AuthUserRepository {
    private final PasswordEncoder passwordEncoder;

    public InMemoryRepository(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private final List<AuthUser> users = new ArrayList<>(List.of(
            new AuthUser("Muhammadali", "muhammad", "$2a$10$O75lZjrCi4GL.7PXOLLiWO2V79V74v7Hnjt92.Lp.P35LYhWbBM.2", "ADMIN"),
            new AuthUser("Akbar", "akbar", "$2a$10$O75lZjrCi4GL.7PXOLLiWO2V79V74v7Hnjt92.Lp.P35LYhWbBM.2", "USER"),
            new AuthUser("Akmal", "akmal", "$2a$10$O75lZjrCi4GL.7PXOLLiWO2V79V74v7Hnjt92.Lp.P35LYhWbBM.2", "USER", List.of(
                    "show:profile",
                    "show:statistic",
                    "create:user",
                    "update:user"
            ))
    ));


    @Override
    public Optional<AuthUser> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    @Override
    public void save(AuthUser authUser) {
        authUser.setPassword(passwordEncoder.encode(authUser.getPassword()));
        users.removeIf(u -> u.getId().equals(authUser.getId()));
        users.add(authUser);
    }
}

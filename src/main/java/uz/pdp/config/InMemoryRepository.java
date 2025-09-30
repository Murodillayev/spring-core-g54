package uz.pdp.config;

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
            new AuthUser("Muhammadali", "muhammad", "muhammad123", "ADMIN"),
            new AuthUser("Akmal", "akmal", "akmal123", "USER")
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

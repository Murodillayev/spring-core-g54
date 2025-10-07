package uz.pdp.repository.impl.db;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.model.enums.AuthRole;
import uz.pdp.repository.AuthUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AuthUserRepositoryImpl implements AuthUserRepository {
    private final JdbcTemplate jdbcTemplate;


    public static final List<AuthUser> users =new ArrayList<>(List.of(
            new AuthUser("Admin","Admin","Admin","991112233", AuthRole.ADMIN),
            new AuthUser("Muhammadali2007","root123","Yoqubjov Muhammadali","991709035",AuthRole.MANAGER),
            new AuthUser("negga","root123","Ali Aliev","112223344",AuthRole.SELLER)

    ));
    @Override
    public  AuthUser save(AuthUser authUser) {
        Optional<AuthUser> byId = findById(authUser.getId());

        String sql = (byId.isPresent())
                ? "UPDATE users SET username = ?, password = ?, full_name = ?, phone = ?,role = ? WHERE id = ?"
                : "INSERT INTO users ( username, password, full_name, phone, role,id) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,

                authUser.getUsername(),
                authUser.getPassword(),
                authUser.getFullName(),
                authUser.getPhone(),
                authUser.getRole().name(),
                authUser.getId()
        );
        return authUser;
    }

    @Override
    public Optional<AuthUser> findById(String id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            AuthUser authUser = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                AuthUser user = new AuthUser();
                user.setId(rs.getString("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setFullName(rs.getString("full_name")); // snake_case fixed
                user.setPhone(rs.getString("phone"));
                user.setRole(AuthRole.valueOf(rs.getString("role"))); // string -> enum
                return user;
            }, id);

            return Optional.ofNullable(authUser);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<AuthUser> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            AuthUser user = new AuthUser();
            user.setId(rs.getString("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setFullName(rs.getString("full_name")); // snake_case handled
            user.setPhone(rs.getString("phone"));
            user.setRole(AuthRole.valueOf(rs.getString("role"))); // enum conversion
            return user;
        });
    }

    @Override
    public void delete(AuthUser authUser) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, authUser.getId());
    }

    public Optional<AuthUser> findByUsername(String username) {
        return findAll().stream().filter(user -> user.getUsername().equals(username)).findFirst();
    }
}

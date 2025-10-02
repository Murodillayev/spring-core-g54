package uz.pdp.repository.impl.db;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.Category;
import uz.pdp.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public static final List<Category> CATEGORIES = new ArrayList<>(List.of(
            new Category("Sirop"),
            new Category("Tabletka"),
            new Category("Svecha")
    ));
    @Override
    public Category save(Category category) {
        Optional<Category> byId = findById(category.getId());
        String sql = (byId.isPresent()) ? "UPDATE category SEt name = ? WHERE id = ?" : "INSERT INTO category (name, id) VALUES (?, ?)";

        jdbcTemplate.update(sql, category.getName(), category.getId());
        return category;
    }

    @Override
    public Optional<Category> findById(String id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        try {
            Category category = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Category.class), id);
            return Optional.ofNullable(category);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Category> findAll() {
        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Category.class));
    }

    @Override
    public void delete(Category category) {
        String sql = "DELETE FROM category WHERE id = ?";
        jdbcTemplate.update(sql, category.getId());
    }
}

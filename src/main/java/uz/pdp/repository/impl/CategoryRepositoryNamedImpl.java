package uz.pdp.repository.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.Category;
import uz.pdp.repository.CategoryRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository("named-jdbc")
@RequiredArgsConstructor
public class CategoryRepositoryNamedImpl implements CategoryRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Category save(Category category) {
        Optional<Category> byId = findById(category.getId());
        String sql = (byId.isPresent())
                ? "UPDATE category SEt name = :name WHERE id = :id"
                : "INSERT INTO category (id, name) VALUES (:id, :name)";

        jdbcTemplate.update(sql, Map.of(
                "name", category.getName(),
                "id", category.getId())
        );
        return category;
    }

    @Override
    public Optional<Category> findById(String id) {
        String sql = "SELECT * FROM category WHERE id = :id";
        try {
            Category category = jdbcTemplate.queryForObject(sql, Map.of("id", id), BeanPropertyRowMapper.newInstance(Category.class));
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
        String sql = "DELETE FROM category WHERE id = :id";
        jdbcTemplate.update(sql, Map.of("id", category.getId()));
    }
}

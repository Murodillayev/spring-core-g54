package uz.pdp.repository;

import uz.pdp.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);

    Optional<Category> findById(String id);

    List<Category> findAll();

    void delete(Category category);
}

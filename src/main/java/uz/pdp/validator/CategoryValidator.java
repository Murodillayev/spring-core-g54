package uz.pdp.validator;

import org.springframework.stereotype.Component;
import uz.pdp.model.entity.Category;
import uz.pdp.repository.CategoryRepository;

@Component
public class CategoryValidator {

    private final CategoryRepository repository;

    public CategoryValidator(CategoryRepository repository) {
        this.repository = repository;
    }

    public void validateOnCreate(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
    }

    public Category existAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Category with id " + id + " not found")
        );
    }
}

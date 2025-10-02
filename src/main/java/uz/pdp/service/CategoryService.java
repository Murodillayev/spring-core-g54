package uz.pdp.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.pdp.mapper.CategoryMapper;
import uz.pdp.model.entity.Category;
import uz.pdp.repository.CategoryRepository;
import uz.pdp.validator.CategoryValidator;

import java.util.List;

@Service
public class CategoryService
        extends AbstractService<
        CategoryRepository,
        CategoryMapper,
        CategoryValidator>
        implements CrudService<Category, String, String, String> {

    protected CategoryService(@Qualifier("categoryRepositoryImpl") CategoryRepository repository, CategoryMapper mapper, CategoryValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public Category create(String name) {
        validator.validateOnCreate(name);
        Category category = mapper.fromName(name);
        return repository.save(category);
    }

    @Override
    public Category get(String id) {
        return validator.existAndGet(id);
    }

    @Override
    public List<Category> getAll(String search) {

        return repository.findAll().stream().filter(
                c -> c.getName().toLowerCase().contains(search.toLowerCase())
        ).toList();
    }


    @Override
    public Category update(String name, String id) {
        Category category = validator.existAndGet(id);
        category.setName(name);
        return repository.save(category);
    }

    @Override
    public void delete(String id) {
        Category category = validator.existAndGet(id);
        repository.delete(category);

    }
}

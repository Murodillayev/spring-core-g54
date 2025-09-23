package uz.pdp.mapper;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import uz.pdp.model.entity.Category;

@Component
public class CategoryMapper {
    public Category fromName(String name) {
        Category category = new Category();
        category.setName(name);
        return category;
    }
}

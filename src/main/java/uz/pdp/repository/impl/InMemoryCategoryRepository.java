package uz.pdp.repository.impl;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.Category;
import uz.pdp.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//public class InMemoryCategoryRepository implements CategoryRepository {


//    @Override
//    public Category save(Category category) {
//        delete(category);
//        CATEGORIES.add(category);
//        return category;
//    }
//
//    @Override
//    public Optional<Category> findById(String id) {
//        return CATEGORIES.stream()
//                .filter(e -> e.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public List<Category> findAll() {
//        return CATEGORIES;
//    }
//
//    @Override
//    public void delete(Category category) {
//        CATEGORIES.removeIf(c -> c.getId().equals(category.getId()));
//    }
//}

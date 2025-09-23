package uz.pdp.controller;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.pdp.model.entity.Category;
import uz.pdp.service.CategoryService;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    //    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @GetMapping
    public String categories(Model model) {
        List<Category> categories = service.getAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

}

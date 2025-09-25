package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.model.entity.Category;
import uz.pdp.service.CategoryService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public String categories(Model model, @RequestParam(name = "search", defaultValue = "") String search) {

        List<Category> categories = service.getAll(search);
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/add")
    public String addPage() {

        return "category/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Dto dto) {
        service.create(dto.getName());
        return "redirect:/category";
    }

    // @ModelAttribute MedicineCreateDto dto

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable(name = "id") String id) {
        ModelAndView mav = new ModelAndView("category/edit");
        Category category = service.get(id);
        mav.addObject("category", category);
        return mav;
    }

    @PostMapping("/edit")
    public String edit(@RequestParam(name = "name") String name, @RequestParam(name = "id") String id) {
        service.update(name,id);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") String id) {
        service.delete(id);
        return "redirect:/category?success=Muvoffaqqiyatli";
    }


}

package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.mapper.MedicineMapper;
import uz.pdp.model.dto.MedicineDto;
import uz.pdp.model.entity.Category;
import uz.pdp.model.entity.Medicine;
import uz.pdp.repository.impl.db.MedicineRepositoryImpl;
import uz.pdp.service.CategoryService;
import uz.pdp.service.MedicineService;

import java.util.List;

//import static uz.pdp.repository.impl.db.MedicineRepositoryImpl.medicines;

@Controller
@RequestMapping("/medicine")
public class MedicineController {
    private final MedicineService service;
    private final MedicineService medicineService;
    private final CategoryService categoryService;
    private final MedicineMapper medicineMapper;
    private final MedicineRepositoryImpl medicineRepositoryImpl;

    public MedicineController(MedicineService service, MedicineService medicineService, CategoryService categoryService, MedicineMapper medicineMapper, MedicineRepositoryImpl medicineRepositoryImpl) {
        this.service = service;
        this.medicineService = medicineService;
        this.categoryService = categoryService;
        this.medicineMapper = medicineMapper;
        this.medicineRepositoryImpl = medicineRepositoryImpl;
    }

    @GetMapping
    public String medicines(@RequestParam(name =  "search", defaultValue = "") String search , Model model) {
        List<MedicineDto> all = service.getAll(search);
        model.addAttribute("medicines", all);
        return "medicine/medicines";
    }

    @GetMapping("/add")
    public String addPage(Model model){
        model.addAttribute("medicine", new Medicine());
        return "medicine/add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute MedicineDto dto){
        medicineService.create(dto);
        return "redirect:/medicine?success=Muvoffaqqiyatli";
    }

    @GetMapping("edit/{id}")
    public ModelAndView editPage(@PathVariable(name =  "id") String id){
        ModelAndView mav = new ModelAndView("medicine/edit");
        MedicineDto dto = medicineService.get(id);
        mav.addObject("medicine",dto);
        List<Category> all = categoryService.getAll("");
        mav.addObject("medCat",dto.getCategory());
        mav.addObject("categories",all);
        return mav;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute MedicineDto dto, @RequestParam(name = "id") String id){
        medicineService.update(dto,id);
        return "redirect:/medicine?success=O'zgartirildi";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable(name = "id") String id){
        medicineService.delete(id);
        return "redirect:/medicine?success=O'chirildi";
    }
}

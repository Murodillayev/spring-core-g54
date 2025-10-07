package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.mapper.MedicineMapper;
import uz.pdp.model.dto.IdNameDto;
import uz.pdp.model.dto.MedicineDto;
import uz.pdp.model.entity.Category;
import uz.pdp.model.entity.Medicine;
import uz.pdp.repository.impl.db.CategoryRepositoryImpl;
import uz.pdp.repository.impl.db.MedicineRepositoryImpl;
import uz.pdp.service.CategoryService;
import uz.pdp.service.MedicineService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//import static uz.pdp.repository.impl.db.MedicineRepositoryImpl.medicines;

@Controller
@RequestMapping("/medicine")
public class MedicineController {
    private final MedicineService service;
    private final MedicineService medicineService;
    private final CategoryService categoryService;
    private final CategoryRepositoryImpl categoryRepository;

    public MedicineController(MedicineService service, MedicineService medicineService, CategoryService categoryService, CategoryRepositoryImpl categoryRepository) {
        this.service = service;
        this.medicineService = medicineService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String medicines(@RequestParam(name =  "search", defaultValue = "") String search , Model model) {
        List<MedicineDto> all = service.getAll(search);
        model.addAttribute("medicines", all);
        return "medicine/medicines";
    }

    @GetMapping("/add")
    public String addPage(Model model){
        List<Category> all = categoryService.getAll("");
        model.addAttribute("categories", all);
        model.addAttribute("medicine", new Medicine());
        return "medicine/add";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute MedicineDto dto, @RequestParam(name = "categoryId") String categoryId, Model model){
        Optional<Category> byId = categoryRepository.findById(categoryId);
        if (byId.isEmpty()) {
            throw new RuntimeException("category id is null");
        }
        IdNameDto idNameDto = new IdNameDto();
        idNameDto.setId(byId.get().getId());
        idNameDto.setName(dto.getName());
        dto.setCategory(idNameDto);
        dto.setId(UUID.randomUUID().toString());
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
    public String edit(@ModelAttribute MedicineDto dto, @RequestParam(name = "id") String id,@RequestParam(name = "expiryDate") String expireDate,@RequestParam(name = "issueDate") String issueDate){

        System.out.println(dto+"  \n");
        LocalDate isDate = LocalDate.parse(issueDate);
        LocalDate exDate = LocalDate.parse(expireDate);
        dto.setExpiryDate(exDate);
        dto.setIssueDate(isDate);
        System.out.println(dto);
        medicineService.update(dto,id);
        return "redirect:/medicine?success=O'zgartirildi";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable(name = "id") String id){
        medicineService.delete(id);
        return "redirect:/medicine?success=O'chirildi";
    }
}

package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.model.dto.MedicineDTO;
import uz.pdp.service.MedicineService;

import java.util.List;

@Controller
@RequestMapping("/medicine")
public class MedicineController {
    private final MedicineService service;

    public MedicineController(MedicineService service) {
        this.service = service;
    }

    @GetMapping
    public String medicines(Model model) {
        List<MedicineDTO> all = service.getAll("");
        model.addAttribute("medicines", all);
        return "medicine/medicines";
    }
}

package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.dto.MedicineDto;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.service.MedicineService;
import uz.pdp.service.SaleItemService;
import uz.pdp.service.SaleService;
import uz.pdp.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService service;
    private final SaleItemService saleItemService;
    private final MedicineService medicineService;
    private final UserService userService;

    @GetMapping
    public String salesPage(@RequestParam(name =  "search", defaultValue = "") String search , Model model) {
//        model.addAttribute("saleItems",all1);
        List<AuthUser> cashiers = userService.getAllTheCashiers();
        model.addAttribute("medicines", medicineService.getAll(search));
        model.addAttribute("cashiers", cashiers);
        return "sale/sales";
    }

    @GetMapping("/buy")
    public String buy(Model model) {
        List<MedicineDto> medicines = medicineService.getAll("");
        model.addAttribute("medicines", medicines);
        return "sale/sale";
    }


}

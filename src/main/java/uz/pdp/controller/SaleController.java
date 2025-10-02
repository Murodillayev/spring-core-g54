package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.dto.MedicineDto;
import uz.pdp.model.dto.SaleCreateDto;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.service.MedicineService;
import uz.pdp.service.SaleItemService;
import uz.pdp.service.SaleService;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/sale")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService service;
    private final SaleItemService saleItemService;
    private final MedicineService medicineService;

    @GetMapping
    public String salesPage(Model model) {
        List<SaleDTO> all = service.getAll("");
//        model.addAttribute("saleItems",all1);
        model.addAttribute("sales", all);
        return "sale/sales";
    }


    @PostMapping
    public String add(@ModelAttribute SaleCreateDto dto) {
        System.out.println(dto.toString());
        // sava sale logic
        return "redirect:/sale";

    }

    @GetMapping("/buy")
    public String buy(Model model) {
        List<MedicineDto> medicines = medicineService.getAll("");
        model.addAttribute("dto", new SaleCreateDto());
        model.addAttribute("medicines", medicines);
        return "sale/sale";
    }
}

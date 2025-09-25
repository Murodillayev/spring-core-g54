package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.model.dto.SaleItemDTO;
import uz.pdp.service.SaleItemService;

import java.util.List;

@Controller
@RequestMapping("/saleItem")
public class SaleItemController {
    private final SaleItemService service;

    public SaleItemController(SaleItemService service) {
        this.service = service;
    }
    @GetMapping
    public String saleItemPage(Model model) {
        List<SaleItemDTO> all = service.getAll("");
        model.addAttribute("saleItems", all);
        return "saleItems";
    }
}

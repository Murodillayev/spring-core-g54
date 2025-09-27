package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.dto.SaleItemDTO;
import uz.pdp.service.SaleItemService;
import uz.pdp.service.SaleService;

import java.util.List;

@Controller
@RequestMapping("/sale")
public class SaleController {
    private final SaleService service;
    private final SaleItemService saleItemService;

    public SaleController(SaleService service, SaleItemService saleItemService) {
        this.service = service;
        this.saleItemService = saleItemService;
    }
    @GetMapping
    public String salesPage(Model model){
        List<SaleItemDTO> all1 = saleItemService.getAll("");
        List<SaleDTO> all = service.getAll("");
        System.out.println(all1.get(0).getSale());
        System.out.println(all.size());
        model.addAttribute("saleItems",all1);
        model.addAttribute("sales",all);
        return "sale/sales";
    }
}

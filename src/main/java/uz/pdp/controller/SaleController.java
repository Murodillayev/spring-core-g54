package uz.pdp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.service.SaleService;

@Controller
@RequestMapping("/sale")
public class SaleController {
    private  final SaleService service;

    public SaleController(SaleService service) {
        this.service = service;
    }

    @GetMapping
    public String salePage(Model model) {
        model.addAttribute("sales", service.getAll());
        return "sales";
    }
}

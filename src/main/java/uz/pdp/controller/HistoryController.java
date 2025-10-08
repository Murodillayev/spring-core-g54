package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.config.SecurityConfig;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.repository.impl.db.SaleRepositoryImpl;

import java.util.List;

@Controller
@PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {

    private final SecurityConfig securityConfig;
    private final SaleRepositoryImpl saleRepository;
    @GetMapping
    public String history(Model model){
        AuthUser currentUser = securityConfig.getCurrentUser();
        List<SaleDTO> sales = saleRepository.getUsersSale(currentUser);
        model.addAttribute("sales", sales);
        return "history/historySavdo";
    }
}

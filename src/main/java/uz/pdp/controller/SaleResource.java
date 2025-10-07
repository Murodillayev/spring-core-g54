package uz.pdp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.dto.*;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.model.entity.Medicine;
import uz.pdp.model.entity.Sale;
import uz.pdp.model.entity.SaleItem;
import uz.pdp.repository.impl.db.AuthUserRepositoryImpl;
import uz.pdp.repository.impl.db.MedicineRepositoryImpl;
import uz.pdp.repository.impl.db.SaleItemRepositoryImpl;
import uz.pdp.repository.impl.db.SaleRepositoryImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleResource {
    private  final AuthUserRepositoryImpl authUserRepository;
    private final SaleItemRepositoryImpl saleItemRepository;
    private final SaleRepositoryImpl saleRepository;
    private final MedicineRepositoryImpl medicineRepository;


    @PostMapping(value = "/checkout", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> checkout(@RequestBody SaleCreateDto saleCreateDto) {
        double totalAmount = 0;
        try {
            List<Medicine> medicines = medicineRepository.findAll();
            AuthUser cashier = authUserRepository.findById(saleCreateDto.getCashierId())
                    .orElseThrow(() -> new RuntimeException("❌ Cashier not found: " + saleCreateDto.getCashierId()));

            Sale sale = new Sale();
            sale.setCashier(cashier);
            sale.setCreatedAt(LocalDateTime.now());
            saleRepository.save(sale);
            for (SaleItemDto itemDto : saleCreateDto.getItems()) {
                totalAmount+=itemDto.getUnitPrice()*itemDto.getQuantity();
                Medicine medicine = medicineRepository.findById(String.valueOf(itemDto.getMedicineId()))
                        .orElseThrow(() -> new RuntimeException("❌ Medicine not found: " + itemDto.getMedicineId()));

                SaleItem saleItem = saleItemRepository.findBySaleAndMedicine(sale, medicine)
                        .orElse(new SaleItem());

                saleItem.setSale(sale);
                saleItem.setMedicine(medicine);
                saleItem.setQuantity(
                        saleItem.getQuantity() != null
                                ? saleItem.getQuantity() + itemDto.getQuantity()
                                : itemDto.getQuantity()
                );
                saleItem.setPrice(itemDto.getUnitPrice());
                for (Medicine medicine1 : medicines) {
//                    if (medicine.getQuantity()<itemDto.getQuantity()){
//                        return ResponseEntity
//                                .badRequest()
//                                .body("We don't have that much medicine in stock!");
//                    }
                    if (medicine1.getId().equals(medicine.getId())) {
                        medicine1.setQuantity(medicine1.getQuantity() - itemDto.getQuantity());
                        medicineRepository.save(medicine1);
                        break;
                    }
                }
                saleItemRepository.save(saleItem);
            }
            sale.setTotalPrice(totalAmount);
            saleRepository.save(sale);
            return ResponseEntity.ok(Map.of(
                    "message", "✅ Sale saved successfully",
                    "saleId", sale.getId()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error: " + e.getMessage());
        }
    }

    @GetMapping("/receipt/{id}")
    public String receipt(@PathVariable("id") String id, Model model) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale id is not correct"));

        List<SaleItemDTOForWeb> saleItems = saleItemRepository.findBySale(sale);

        SaleDTO saleDTO = new SaleDTO();
        saleDTO.setItems(saleItems);
        saleDTO.setTotalPrice(sale.getTotalPrice());
        saleDTO.setId(sale.getId());
        saleDTO.setCreatedAt(LocalDateTime.now());
        IdNameDto cashier = new IdNameDto();
        cashier.setId(sale.getCashier().getId());
        cashier.setName(sale.getCashier().getFullName());
        saleDTO.setCashier(cashier);

        model.addAttribute("sale", saleDTO);
        return "sale/receipt";  // Thymeleaf template
    }


}

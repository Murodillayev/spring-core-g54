package uz.pdp.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.dto.SaleCreateDto;
import uz.pdp.model.dto.SaleItemDto;
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

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleResource {
    private  final AuthUserRepositoryImpl authUserRepository;
    private final SaleItemRepositoryImpl saleItemRepository;
    private final SaleRepositoryImpl saleRepository;
    private final MedicineRepositoryImpl medicineRepository;


    @PostMapping(value = "/checkout", consumes = "application/json")
    public ResponseEntity<?> checkout(@RequestBody SaleCreateDto saleCreateDto) {
        double totalAmount = 0;
        try {
            System.out.println("==== Checkout payload ====");
            System.out.println("CashierId: " + saleCreateDto.getCashierId());
            System.out.println("Items: " + saleCreateDto.getItems());
            List<Medicine> medicines = medicineRepository.findAll();
            AuthUser cashier = authUserRepository.findById(saleCreateDto.getCashierId())
                    .orElseThrow(() -> new RuntimeException("❌ Cashier not found: " + saleCreateDto.getCashierId()));

            Sale sale = new Sale();
            sale.setCashier(cashier);
            sale.setCreatedAt(LocalDateTime.now());
            saleRepository.save(sale);
            for (SaleItemDto itemDto : saleCreateDto.getItems()) {
                System.out.println("Processing item: " + itemDto.getMedicineId());
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
                    if (medicine1.getId().equals(medicine.getId())) {
                        System.out.println("Updating stock for medicine: " + medicine1.getName() +
                                " | Current Quantity: " + medicine1.getQuantity() +
                                " | Sold Quantity: " + itemDto.getQuantity());
                        medicine1.setQuantity(medicine1.getQuantity() - itemDto.getQuantity());
                        medicineRepository.save(medicine1);
                        break;
                    }
                }
                saleItemRepository.save(saleItem);
            }
            sale.setTotalPrice(totalAmount);
            saleRepository.save(sale);
            return ResponseEntity.ok("✅ Sale saved successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error: " + e.getMessage());
        }
    }

}

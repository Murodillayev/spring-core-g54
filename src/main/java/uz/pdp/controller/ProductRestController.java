package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.model.dto.MedicineDto;
import uz.pdp.repository.MedicineRepository;
import uz.pdp.service.MedicineService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/medicine")
@RequiredArgsConstructor
public class ProductRestController {

    private final MedicineService medicineService;
    private final MedicineRepository repository;

    @GetMapping
    public ResponseEntity<?> searchProducts(@RequestParam(name = "search", defaultValue = "") String search) {
        List<MedicineDto> medicines = medicineService.getAll(search);
        return ResponseEntity.ok(Map.of("data", medicines));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

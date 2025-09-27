package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.Medicine;
import uz.pdp.repository.MedicineRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static uz.pdp.repository.impl.InMemoryCategoryRepository.CATEGORIES;

@Repository
public class InMemoryMedicineRepository implements MedicineRepository {

    public static final List<Medicine> medicines = new ArrayList<>(List.of(
            new Medicine(
                    "Paracetamol",
                    "1111111111111",
                    LocalDate.of(2025, 1, 1),
                    LocalDate.of(2027, 1, 1),
                    "Pain reliever and fever reducer",
                    CATEGORIES.get(1),   // Tabletka
                    100,
                    1.50
            ),
            new Medicine(
                    "Ambroxol",
                    "2222222222222",
                    LocalDate.of(2024, 12, 10),
                    LocalDate.of(2026, 12, 10),
                    "Cough syrup",
                    CATEGORIES.get(0),   // Sirop
                    50,
                    3.20
            ),
            new Medicine(
                    "Nurofen",
                    "3333333333333",
                    LocalDate.of(2025, 2, 15),
                    LocalDate.of(2027, 2, 15),
                    "Anti-inflammatory and pain relief",
                    CATEGORIES.get(1),   // Tabletka
                    80,
                    2.75
            )));
    @Override
    public Medicine save(Medicine medicine) {
        delete(medicine);
        medicines.add(new Medicine(
                medicine.getName(),
                medicine.getBarCode(),
                medicine.getIssueDate(),
                medicine.getExpiryDate(),
                medicine.getDescription(),
                medicine.getCategory(),
                medicine.getQuantity(),
                medicine.getPrice()
        ));
        return medicine;
    }

    @Override
    public Optional<Medicine> findById(String id) {
        return medicines.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst()
                .map(m -> {
                    Medicine medicine = new Medicine();
                    medicine.setId(m.getId());
                    medicine.setName(m.getName());
                    medicine.setBarCode(m.getBarCode());
                    medicine.setIssueDate(m.getIssueDate());
                    medicine.setExpiryDate(m.getExpiryDate());
                    medicine.setDescription(m.getDescription());
                    medicine.setCategory(m.getCategory());
                    medicine.setQuantity(m.getQuantity());
                    medicine.setPrice(m.getPrice());
                    return medicine;
                });
    }

    @Override
    public List<Medicine> findAll() {
        return medicines;
    }

    @Override
    public void delete(Medicine medicine) {
        medicines.removeIf(m -> m.getId().equals(medicine.getId()));
    }
}

package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.Medicine;
import uz.pdp.repository.MedicineRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static uz.pdp.repository.impl.db.CategoryRepositoryImpl.CATEGORIES;

//public class InMemoryMedicineRepository implements MedicineRepository {
//
//
//    @Override
//    public Medicine save(Medicine medicine) {
//        delete(medicine);
//        medicines.add(new Medicine(
//                medicine.getName(),
//                medicine.getBarCode(),
//                medicine.getIssueDate(),
//                medicine.getExpiryDate(),
//                medicine.getDescription(),
//                medicine.getCategory(),
//                medicine.getQuantity(),
//                medicine.getPrice()
//        ));
//        return medicine;
//    }
//
//    @Override
//    public Optional<Medicine> findById(String id) {
//        return medicines.stream()
//                .filter(m -> m.getId().equals(id))
//                .findFirst()
//                .map(m -> {
//                    Medicine medicine = new Medicine();
//                    medicine.setId(m.getId());
//                    medicine.setName(m.getName());
//                    medicine.setBarCode(m.getBarCode());
//                    medicine.setIssueDate(m.getIssueDate());
//                    medicine.setExpiryDate(m.getExpiryDate());
//                    medicine.setDescription(m.getDescription());
//                    medicine.setCategory(m.getCategory());
//                    medicine.setQuantity(m.getQuantity());
//                    medicine.setPrice(m.getPrice());
//                    return medicine;
//                });
//    }
//
//    @Override
//    public List<Medicine> findAll() {
//        return medicines;
//    }
//
//    @Override
//    public void delete(Medicine medicine) {
//        medicines.removeIf(m -> m.getId().equals(medicine.getId()));
//    }
//}

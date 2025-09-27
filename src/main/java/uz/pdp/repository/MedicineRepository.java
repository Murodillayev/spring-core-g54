package uz.pdp.repository;

import uz.pdp.model.entity.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository {
    Medicine save(Medicine medicine);

    Optional<Medicine> findById(String id);

    List<Medicine> findAll();

    void  delete(Medicine medicine);
}

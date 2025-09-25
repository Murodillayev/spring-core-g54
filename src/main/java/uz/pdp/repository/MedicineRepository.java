package uz.pdp.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.model.dto.MedicineDTO;
import uz.pdp.model.entity.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository {
    Medicine save(Medicine medicine);

    Optional<Medicine> findById(String id);

    List<MedicineDTO> findAll();

    void  delete(Medicine medicine);
}

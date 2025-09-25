package uz.pdp.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.model.dto.MedicineDTO;
import uz.pdp.model.entity.Medicine;
import uz.pdp.repository.MedicineRepository;

@Component
public class MedicineValidator {

    private  final MedicineRepository repository;

    public MedicineValidator(MedicineRepository repository) {
        this.repository = repository;
    }

    public void validateOnCreate(MedicineDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new IllegalArgumentException("Medicine name cannot be null or blank");
        }
        if (dto.getBarCode() == null || dto.getBarCode().isBlank()) {
            throw new IllegalArgumentException("Bar code cannot be null or blank");
        }
    }

    public Medicine ExistAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Medicine with id " + id + " does not exist")
        );
    }
}

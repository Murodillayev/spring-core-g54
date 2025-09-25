package uz.pdp.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.model.dto.MedicineDTO;
import uz.pdp.model.entity.Medicine;

@Component
public class MedicineMapper {
    public Medicine fromDTO(MedicineDTO dto) {
        Medicine medicine = new Medicine();
        medicine.setId(dto.getId());
        medicine.setName(dto.getName());
        medicine.setDescription(dto.getDescription());
        medicine.setCategory(dto.getCategory());
        medicine.setPrice(dto.getPrice());
        medicine.setQuantity(dto.getQuantity());
        medicine.setBarCode(dto.getBarCode());
        medicine.setIssueDate(dto.getIssueDate());
        medicine.setExpiryDate(dto.getExpiryDate());
        return medicine;
    }

    public MedicineDTO toDTO(Medicine save) {
        MedicineDTO dto =  new MedicineDTO();
        dto.setId(save.getId());
        dto.setName(save.getName());
        dto.setDescription(save.getDescription());
        dto.setCategory(save.getCategory());
        dto.setPrice(save.getPrice());
        dto.setQuantity(save.getQuantity());
        dto.setBarCode(save.getBarCode());
        dto.setIssueDate(save.getIssueDate());
        dto.setExpiryDate(save.getExpiryDate());
        return dto;
    }
}

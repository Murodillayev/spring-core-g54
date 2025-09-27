package uz.pdp.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.model.dto.IdNameDto;
import uz.pdp.model.dto.MedicineDto;
import uz.pdp.model.entity.Medicine;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicineMapper {
    public Medicine fromDTO(MedicineDto dto) {
        Medicine medicine = new Medicine();
        medicine.setId(dto.getId());
        medicine.setName(dto.getName());
        medicine.setDescription(dto.getDescription());
//        medicine.setCategory(dto.getCategory());
        medicine.setPrice(dto.getPrice());
        medicine.setQuantity(dto.getQuantity());
        medicine.setBarCode(dto.getBarCode());
        medicine.setIssueDate(dto.getIssueDate());
        medicine.setExpiryDate(dto.getExpiryDate());
        return medicine;
    }

    public MedicineDto toDTO(Medicine save) {
        MedicineDto dto = new MedicineDto();
        dto.setId(save.getId());
        dto.setName(save.getName());
        dto.setDescription(save.getDescription());
        dto.setCategory(IdNameDto.builder()
                .id(save.getCategory().getId())
                .name(save.getCategory().getName())
                .build());
        dto.setPrice(save.getPrice());
        dto.setQuantity(save.getQuantity());
        dto.setBarCode(save.getBarCode());
        dto.setIssueDate(save.getIssueDate());
        dto.setExpiryDate(save.getExpiryDate());
        return dto;
    }

    public List<MedicineDto> toDtoList(List<Medicine> all) {
        return all.stream().map(this::toDTO).collect(Collectors.toList());
    }
}

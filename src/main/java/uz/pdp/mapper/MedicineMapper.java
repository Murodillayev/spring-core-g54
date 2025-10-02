package uz.pdp.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.model.dto.IdNameDto;
import uz.pdp.model.dto.MedicineDto;
import uz.pdp.model.entity.Category;
import uz.pdp.model.entity.Medicine;
import uz.pdp.repository.impl.db.CategoryRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MedicineMapper {
    private final CategoryRepositoryImpl categoryRepository ;

    public MedicineMapper(CategoryRepositoryImpl categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Medicine fromDTO(MedicineDto dto) {
        Medicine medicine = new Medicine();
        medicine.setId(dto.getId());
        medicine.setName(dto.getName());
        medicine.setDescription(dto.getDescription());
        medicine.setCategoryId(dto.getCategory().getId());
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
        Category category = categoryRepository.findById(save.getCategoryId()).get();
        dto.setCategory(new IdNameDto(category.getId(),category.getName()));
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

    public List<Medicine> fromDtoList(List<MedicineDto> all) {
        return all.stream().map(this::fromDTO).collect(Collectors.toList());
    }
}

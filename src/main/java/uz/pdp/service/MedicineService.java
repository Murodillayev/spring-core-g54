package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.mapper.MedicineMapper;
import uz.pdp.model.dto.MedicineDTO;
import uz.pdp.model.entity.Medicine;
import uz.pdp.repository.MedicineRepository;
import uz.pdp.validator.MedicineValidator;

import java.util.List;

@Service
public class MedicineService
    extends AbstractService<
        MedicineRepository,
        MedicineMapper,
        MedicineValidator> implements CrudService<MedicineDTO, MedicineDTO, MedicineDTO,String>{


    protected MedicineService(MedicineRepository repository, MedicineMapper mapper, MedicineValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public MedicineDTO create(MedicineDTO dto) {
        validator.validateOnCreate(dto);
        Medicine medicine = mapper.fromDTO(dto);
        return mapper.toDTO(repository.save(medicine));
    }

    @Override
    public MedicineDTO get(String id) {
        return mapper.toDTO(validator.ExistAndGet(id));
    }

    @Override
    public List<MedicineDTO> getAll() {
        return repository.findAll();
    }

    @Override
    public MedicineDTO update(MedicineDTO dto, String id) {
        Medicine medicine = validator.ExistAndGet(id);
        medicine.setId(dto.getId());
        medicine.setName(dto.getName());
        medicine.setDescription(dto.getDescription());
        medicine.setCategory(dto.getCategory());
        medicine.setPrice(dto.getPrice());
        medicine.setQuantity(dto.getQuantity());
        medicine.setBarCode(dto.getBarCode());
        medicine.setIssueDate(dto.getIssueDate());
        medicine.setExpiryDate(dto.getExpiryDate());
        return mapper.toDTO(repository.save(medicine));
    }

    @Override
    public void delete(String id) {
        Medicine medicine = validator.ExistAndGet(id);
        repository.delete(medicine);
    }
}

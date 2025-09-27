package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.mapper.MedicineMapper;
import uz.pdp.model.dto.MedicineDto;
import uz.pdp.model.entity.Medicine;
import uz.pdp.repository.MedicineRepository;
import uz.pdp.validator.MedicineValidator;

import java.util.List;

@Service
public class MedicineService
    extends AbstractService<
        MedicineRepository,
        MedicineMapper,
        MedicineValidator> implements CrudService<MedicineDto, MedicineDto, MedicineDto,String>{


    protected MedicineService(MedicineRepository repository, MedicineMapper mapper, MedicineValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public MedicineDto create(MedicineDto dto) {
        validator.validateOnCreate(dto);
        Medicine medicine = mapper.fromDTO(dto);
        return mapper.toDTO(repository.save(medicine));
    }

    @Override
    public MedicineDto get(String id) {
        return mapper.toDTO(validator.ExistAndGet(id));
    }

    @Override
    public List<MedicineDto> getAll(String search) {
        return mapper.toDtoList(repository.findAll().
                stream().filter(m -> m.getName().toLowerCase().contains(search.toLowerCase())
                ).toList());
    }

    @Override
    public MedicineDto update(MedicineDto dto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}

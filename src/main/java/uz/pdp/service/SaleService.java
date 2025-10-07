package uz.pdp.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.pdp.mapper.SaleMapper;
import uz.pdp.model.dto.SaleCreateDto;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.entity.Sale;
import uz.pdp.repository.SaleRepository;
import uz.pdp.validator.SaleValidator;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService extends AbstractService<
        SaleRepository,
        SaleMapper,
        SaleValidator> implements CrudService<SaleDTO, SaleCreateDto, SaleDTO, String> {
    protected SaleService(@Qualifier("saleRepositoryImpl") SaleRepository repository, SaleMapper mapper, SaleValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public SaleDTO create(SaleCreateDto dto) {
        validator.existOnCreate(dto);
        Sale sale = mapper.fromDTO(dto);
        return mapper.toDTO(repository.save(sale));
    }

    @Override
    public SaleDTO get(String id) {
        return mapper.toDTO(validator.ExistAndGet(id));
    }

    @Override
    public List<SaleDTO> getAll(String search) {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public SaleDTO update(SaleDTO dto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {
        Sale sale = validator.ExistAndGet(id);
        repository.delete(sale);
    }
}

package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.mapper.SaleMapper;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.entity.Sale;
import uz.pdp.repository.SaleRepository;
import uz.pdp.validator.SaleValidator;

import java.util.List;

@Service
public class SaleService extends AbstractService<
        SaleRepository,
        SaleMapper,
        SaleValidator> implements CrudService<SaleDTO,SaleDTO,SaleDTO,String>{

    protected SaleService(SaleRepository repository, SaleMapper mapper, SaleValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public SaleDTO create(SaleDTO dto) {
        validator.ExistOnCreate(dto);
        return mapper.toDTO(repository.save(mapper.fromDTO(dto)));
    }

    @Override
    public SaleDTO get(String id) {
        return mapper.toDTO(validator.existAndGet(id));
    }

    @Override
    public List<SaleDTO> getAll() {
        return repository.findAll();
    }

    @Override
    public SaleDTO update(SaleDTO dto, String id) {
        Sale sale = validator.existAndGet(id);
        sale.setCashier(dto.getCashier());
        sale.setTotalPrice(dto.getTotalPrice());
        return mapper.toDTO(repository.save(sale));
    }

    @Override
    public void delete(String id) {
        Sale sale = validator.existAndGet(id);
        repository.delete(sale);
    }
}

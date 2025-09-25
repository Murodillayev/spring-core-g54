package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.mapper.SaleItemMapper;
import uz.pdp.model.dto.SaleItemDTO;
import uz.pdp.model.entity.SaleItem;
import uz.pdp.repository.SaleItemRepository;
import uz.pdp.validator.SaleItemValidator;

import java.util.List;

@Service
public class SaleItemService extends AbstractService<
        SaleItemRepository,
        SaleItemMapper,
        SaleItemValidator> implements CrudService<SaleItemDTO,SaleItemDTO,SaleItemDTO,String> {
    {

    }

    protected SaleItemService(SaleItemRepository repository, SaleItemMapper mapper, SaleItemValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public SaleItemDTO create(SaleItemDTO dto) {
        validator.validateOnCreate(dto);
        SaleItem saleItem = mapper.fromDto(dto);
        return mapper.toDto(repository.save(saleItem));
    }

    @Override
    public SaleItemDTO get(String id) {
        return mapper.toDto(repository.findById(id).orElseThrow(
                () -> new RuntimeException("Sale item not found")
        ));
    }

    @Override
    public List<SaleItemDTO> getAll(String search) {
        return repository.findAll();
    }

    @Override
    public SaleItemDTO update(SaleItemDTO dto, String id) {
        SaleItem saleItem = validator.ExistAndGet(id);
        saleItem.setMedicine(dto.getMedicine());
        saleItem.setPrice(dto.getPrice());
        saleItem.setQuantity(dto.getQuantity());
        return mapper.toDto(repository.save(saleItem));
    }

    @Override
    public void delete(String id) {
        SaleItem saleItem = validator.ExistAndGet(id);
        repository.delete(saleItem);
    }
}

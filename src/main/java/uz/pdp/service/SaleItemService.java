package uz.pdp.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import uz.pdp.mapper.SaleItemMapper;
import uz.pdp.model.dto.SaleItemDto;
import uz.pdp.model.entity.SaleItem;
import uz.pdp.repository.SaleItemRepository;
import uz.pdp.validator.SaleItemValidator;

import java.util.List;

@Service
public class SaleItemService extends AbstractService<
        SaleItemRepository,
        SaleItemMapper,
        SaleItemValidator> implements CrudService<SaleItemDto, SaleItemDto, SaleItemDto,String> {




    protected SaleItemService(@Qualifier("saleItemRepositoryImpl") SaleItemRepository repository, SaleItemMapper mapper, SaleItemValidator validator) {
        super(repository, mapper, validator);
    }

    @Override
    public SaleItemDto create(SaleItemDto dto) {
        return null;
    }

    @Override
    public SaleItemDto get(String id) {
       return null;
    }

    @Override
    public List<SaleItemDto> getAll(String search) {
        return null;
    }

    @Override
    public SaleItemDto update(SaleItemDto dto, String id) {
        return null;
    }

    @Override
    public void delete(String id) {
        SaleItem saleItem = validator.ExistAndGet(id);
        repository.delete(saleItem);
    }
}

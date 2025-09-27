package uz.pdp.validator;

import org.springframework.stereotype.Component;
import uz.pdp.model.dto.SaleItemDto;
import uz.pdp.model.entity.SaleItem;
import uz.pdp.repository.SaleItemRepository;

@Component
public class SaleItemValidator {
    private final SaleItemRepository repository;

    public SaleItemValidator(SaleItemRepository repository) {
        this.repository = repository;
    }

    public void validateOnCreate(SaleItemDto dto) {

    }

    public SaleItem ExistAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("SaleItem with id " + id + " not found")
        );
    }
}

package uz.pdp.validator;

import org.springframework.stereotype.Component;
import uz.pdp.model.dto.SaleItemDTO;
import uz.pdp.model.entity.SaleItem;
import uz.pdp.repository.SaleItemRepository;

@Component
public class SaleItemValidator {
    private final SaleItemRepository repository;

    public SaleItemValidator(SaleItemRepository repository) {
        this.repository = repository;
    }

    public void validateOnCreate(SaleItemDTO dto) {
        if (dto.getSale() == null)
            throw new RuntimeException("Sale id is required");
        if (dto.getMedicine() == null)
            throw new RuntimeException("Medicine id is required");
        if (dto.getQuantity() == null || dto.getQuantity() <= 0)
            throw new RuntimeException("Quantity must be greater than zero");
    }

    public SaleItem ExistAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("SaleItem with id " + id + " not found")
        );
    }
}

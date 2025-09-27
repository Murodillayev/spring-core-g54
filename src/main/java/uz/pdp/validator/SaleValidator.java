package uz.pdp.validator;

import org.springframework.stereotype.Component;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.entity.Sale;
import uz.pdp.repository.SaleItemRepository;
import uz.pdp.repository.SaleRepository;

@Component
public class SaleValidator {
    public final SaleRepository repository;

    public SaleValidator(SaleRepository repository) {
        this.repository = repository;
    }

    public void ExistOnCreate(SaleDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Sale id cannot be null");
        }
    }

    public Sale ExistAndGet(String id) {
        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Sale with id " + id + " does not exist")
        );
    }
}

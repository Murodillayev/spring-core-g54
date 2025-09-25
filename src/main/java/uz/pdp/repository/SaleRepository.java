package uz.pdp.repository;

import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    Sale save(Sale sale);

    Optional<SaleDTO> findById(String id);

    List<SaleDTO> findAll();

    void  delete(Sale sale);
}

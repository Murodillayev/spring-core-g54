package uz.pdp.repository;

import uz.pdp.model.entity.SaleItem;

import java.util.List;
import java.util.Optional;

public interface SaleItemRepository {

    SaleItem save(SaleItem saleItem);

    Optional<SaleItem> findById(String id);

    List<SaleItem> findAll();

    void  delete(SaleItem saleItem);
}

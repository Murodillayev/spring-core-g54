package uz.pdp.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.model.entity.Sale;

import java.util.List;
import java.util.Optional;

public interface SaleRepository {
    Sale save(Sale sale);

    Optional<Sale> findById(String id);

    List<Sale> findAll();

    void  delete(Sale sale);

}

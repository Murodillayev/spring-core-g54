package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.Sale;
import uz.pdp.repository.SaleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


//public class InMemorySaleRepository implements SaleRepository {
//
//
//
//    @Override
//    public Sale save(Sale sale) {
//        sales.add(sale);
//        return sale;
//    }
//
//    @Override
//    public Optional<Sale> findById(String id) {
//        return sales.stream()
//                .filter(sale -> sale.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public List<Sale> findAll() {
//        return sales;
//    }
//
//    @Override
//    public void delete(Sale sale) {
//        sales.removeIf(sale1 -> sale1.getId().equals(sale.getId()));
//    }
//}

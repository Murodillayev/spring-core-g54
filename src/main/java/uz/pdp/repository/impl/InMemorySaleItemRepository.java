package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.Sale;
import uz.pdp.model.entity.SaleItem;
import uz.pdp.repository.SaleItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static uz.pdp.repository.impl.db.MedicineRepositoryImpl.medicines;


//public class InMemorySaleItemRepository implements SaleItemRepository {
//
//    @Override
//    public SaleItem save(SaleItem saleItem) {
//        delete(saleItem);
//        saleItems.add(saleItem);
//        return saleItem;
//    }
//
//    @Override
//    public Optional<SaleItem> findById(String id) {
//        return saleItems.stream()
//                .filter(s -> s.getId().equals(id))
//                .findFirst();
//    }
//
//    @Override
//    public List<SaleItem> findAll() {
//        return saleItems;
//    }
//
//    @Override
//    public void delete(SaleItem saleItem) {
//        saleItems.removeIf(s -> s.getId().equals(saleItem.getId()));
//    }
//}

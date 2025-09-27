package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.Sale;
import uz.pdp.model.entity.SaleItem;
import uz.pdp.repository.SaleItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static uz.pdp.repository.impl.InMemoryMedicineRepository.medicines;

@Repository
public class InMemorySaleItemRepository implements SaleItemRepository {


    public static final List<Sale> sales = new ArrayList<>(List.of(
            new Sale(),
            new Sale(),
            new Sale()
    ));
    public static final List<SaleItem> saleItems = new ArrayList<>(
            List.of(
            new SaleItem(sales.get(0),medicines.get(0),2*medicines.get(0).getPrice(),2,sales.get(0).getCreatedAt()),
            new SaleItem(sales.get(0),medicines.get(1),3*medicines.get(1).getPrice(),3,sales.get(0).getCreatedAt()),
            new SaleItem(sales.get(1),medicines.get(2),5*medicines.get(2).getPrice(),5,sales.get(1).getCreatedAt()),
            new SaleItem(sales.get(1),medicines.get(0),4*medicines.get(0).getPrice(),4,sales.get(1).getCreatedAt()),
            new SaleItem(sales.get(2),medicines.get(1),7*medicines.get(1).getPrice(),7,sales.get(2).getCreatedAt()),
            new SaleItem(sales.get(2),medicines.get(2),6*medicines.get(2).getPrice(),6,sales.get(2).getCreatedAt()))
    );
    static {
        InMemorySaleRepository.putToSales();
    }
    @Override
    public SaleItem save(SaleItem saleItem) {
        delete(saleItem);
        saleItems.add(saleItem);
        return saleItem;
    }

    @Override
    public Optional<SaleItem> findById(String id) {
        return saleItems.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<SaleItem> findAll() {
        return saleItems;
    }

    @Override
    public void delete(SaleItem saleItem) {
        saleItems.removeIf(s -> s.getId().equals(saleItem.getId()));
    }
}

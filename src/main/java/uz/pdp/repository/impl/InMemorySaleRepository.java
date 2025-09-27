package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.Sale;
import uz.pdp.repository.SaleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static uz.pdp.repository.impl.InMemorySaleItemRepository.saleItems;
import static uz.pdp.repository.impl.InMemorySaleItemRepository.sales;
import static uz.pdp.repository.impl.InMemoryAuthUserRepository.users;

@Repository
public class InMemorySaleRepository implements SaleRepository {
    public static void putToSales(){
        sales.get(0).setTotalPrice(saleItems.get(0).getPrice()+saleItems.get(1).getPrice());
        sales.get(0).setCashier(users.get(2));
        sales.get(0).setCreatedAt(LocalDateTime.now());
        sales.get(1).setTotalPrice(saleItems.get(2).getPrice()+saleItems.get(3).getPrice());
        sales.get(1).setCashier(users.get(2));
        sales.get(1).setCreatedAt(LocalDateTime.now());
        sales.get(2).setTotalPrice(saleItems.get(4).getPrice()+saleItems.get(5).getPrice());
        sales.get(2).setCashier(users.get(2));
        sales.get(2).setCreatedAt(LocalDateTime.now());
    }


    @Override
    public Sale save(Sale sale) {
        sales.add(sale);
        return sale;
    }

    @Override
    public Optional<Sale> findById(String id) {
        return sales.stream()
                .filter(sale -> sale.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Sale> findAll() {
        return sales;
    }

    @Override
    public void delete(Sale sale) {
        sales.removeIf(sale1 -> sale1.getId().equals(sale.getId()));
    }
}

package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.model.dto.SaleItemDTO;
import uz.pdp.model.entity.SaleItem;
import uz.pdp.repository.SaleItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemorySaleItemRepository implements SaleItemRepository {

    private final List<SaleItemDTO> saleItems = new ArrayList<>(
            List.of(new SaleItemDTO())
    );
    @Override
    public SaleItem save(SaleItem saleItem) {
        delete(saleItem);
        saleItems.add(new SaleItemDTO(
                saleItem.getId(),
                saleItem.getSale(),
                saleItem.getMedicine(),
                saleItem.getPrice(),
                saleItem.getQuantity()
        ));
        return saleItem;
    }

    @Override
    public Optional<SaleItem> findById(String id) {
        return saleItems.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .map(s -> {
                    SaleItem item = new SaleItem();
                    item.setId(s.getId());
                    item.setMedicine(s.getMedicine());
                    item.setQuantity(s.getQuantity());
                    item.setPrice(s.getPrice());
                    return item;
                });
    }

    @Override
    public List<SaleItemDTO> findAll() {
        return saleItems;
    }

    @Override
    public void delete(SaleItem saleItem) {
        saleItems.removeIf(s -> s.getId().equals(saleItem.getId()));
    }
}

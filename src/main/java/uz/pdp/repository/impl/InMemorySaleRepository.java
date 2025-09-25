package uz.pdp.repository.impl;

import org.springframework.stereotype.Repository;
import uz.pdp.mapper.UserMapper;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.entity.Sale;
import uz.pdp.repository.SaleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static uz.pdp.repository.impl.InMemoryUserRepository.users;

@Repository
public class InMemorySaleRepository implements SaleRepository {
    public static final UserMapper usermapper = new UserMapper();
    public static final List<SaleDTO> sales = new ArrayList<>(List.of(
            new SaleDTO(UUID.randomUUID().toString(),0.0, usermapper.fromDto(users.get(2))),
            new SaleDTO(UUID.randomUUID().toString(),0.0, usermapper.fromDto(users.get(2))),
            new SaleDTO(UUID.randomUUID().toString(),0.0, usermapper.fromDto(users.get(2)))
    ));
    @Override
    public Sale save(Sale entity) {
        delete(entity);
        sales.add(new SaleDTO(
                entity.getId(),
                entity.getTotalPrice(),
                entity.getCashier()
        ));
        return entity;
    }

    @Override
    public Optional<SaleDTO> findById(String id) {
        return sales.stream().filter(saleDTO -> saleDTO.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<SaleDTO> findAll() {
        return sales;
    }

    @Override
    public void delete(Sale entity) {
            sales.removeIf(saleDTO -> saleDTO.getId().equals(entity.getId()));
    }
}

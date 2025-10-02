package uz.pdp.repository.impl.db;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.Sale;
import uz.pdp.repository.SaleRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static uz.pdp.repository.impl.db.AuthUserRepositoryImpl.users;
//import static uz.pdp.repository.impl.db.SaleItemRepositoryImpl.saleItems;
//import static uz.pdp.repository.impl.db.SaleItemRepositoryImpl.sales;

@Repository
@AllArgsConstructor
public class SaleRepositoryImpl implements SaleRepository {
    private JdbcTemplate jdbcTemplate;
//    public static void putToSales(){
//        sales.get(0).setTotalPrice(saleItems.get(0).getPrice()+saleItems.get(1).getPrice());
//        sales.get(0).setCashier(users.get(2));
//        sales.get(0).setCreatedAt(LocalDateTime.now());
//        sales.get(1).setTotalPrice(saleItems.get(2).getPrice()+saleItems.get(3).getPrice());
//        sales.get(1).setCashier(users.get(2));
//        sales.get(1).setCreatedAt(LocalDateTime.now());
//        sales.get(2).setTotalPrice(saleItems.get(4).getPrice()+saleItems.get(5).getPrice());
//        sales.get(2).setCashier(users.get(2));
//        sales.get(2).setCreatedAt(LocalDateTime.now());
//    }
    @Override
    public Sale save(Sale sale) {
        Optional<Sale> byId = findById(sale.getId());
        String sql = (byId.isPresent())
                ? "UPDATE sale SET total_price = ?, cashier_id = ? WHERE id = ?"
                : "INSERT INTO sale (total_price, cashier_id,id) VALUES (?, ?,  ?)";
        jdbcTemplate.update(sql,
                sale.getTotalPrice(),
                sale.getCashier().getId(),
                sale.getId());
        return sale;
    }

    @Override
    public Optional<Sale> findById(String id) {
        String sql = "SELECT * FROM sale WHERE id = ?";
        try {
            Sale sale = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Sale.class), id);
            return Optional.ofNullable(sale);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Sale> findAll() {
        String sql = "SELECT * FROM sale";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Sale.class));
    }

    @Override
    public void delete(Sale sale) {
        String sql = "DELETE FROM sale WHERE id = ?";
        jdbcTemplate.update(sql, sale.getId());
    }
}

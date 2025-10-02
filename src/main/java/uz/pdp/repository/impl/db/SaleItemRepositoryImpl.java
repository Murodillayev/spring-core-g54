package uz.pdp.repository.impl.db;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.pdp.model.entity.Medicine;
import uz.pdp.model.entity.Sale;
import uz.pdp.model.entity.SaleItem;
import uz.pdp.repository.SaleItemRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static uz.pdp.repository.impl.db.SaleRepositoryImpl.putToSales;

@Repository
@AllArgsConstructor
public class SaleItemRepositoryImpl implements SaleItemRepository {
    private final JdbcTemplate jdbcTemplate;



//    public static final List<Sale> sales = new ArrayList<>(List.of(
//            new Sale(),
//            new Sale(),
//            new Sale()
//    ));
//    public static final List<SaleItem> saleItems = new ArrayList<>(
//            List.of(
//                    new SaleItem(sales.get(0),medicines.get(0),2*medicines.get(0).getPrice(),2),
//                    new SaleItem(sales.get(0),medicines.get(1),3*medicines.get(1).getPrice(),3),
//                    new SaleItem(sales.get(1),medicines.get(2),5*medicines.get(2).getPrice(),5),
//                    new SaleItem(sales.get(1),medicines.get(0),4*medicines.get(0).getPrice(),4),
//                    new SaleItem(sales.get(2),medicines.get(1),7*medicines.get(1).getPrice(),7),
//                    new SaleItem(sales.get(2),medicines.get(2),6*medicines.get(2).getPrice(),6))
//    );
//    static {
//        putToSales();
//    }
    @Override
    public SaleItem save(SaleItem saleItem) {
        Optional<SaleItem> byId = findById(saleItem.getId());
        String sql = (byId.isPresent() ? "UPDATE sale_item SET sale_id = ?, medicine_id = ?, price = ?, quantity = ? WHERE id = ?"
                : "INSERT INTO sale_item (sale_id, medicine_id, price, quantity, id) VALUES (?, ?, ?, ?, ?)");
        jdbcTemplate.update(sql, saleItem.getSale().getId(), saleItem.getMedicine().getId(), saleItem.getPrice(), saleItem.getQuantity(), saleItem.getId());
        return saleItem;
    }

    @Override
    public Optional<SaleItem> findById(String id) {
        String sql = "select * from sale_item where id = ?";
        try {
            SaleItem saleItem = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(SaleItem.class), id);
            return Optional.ofNullable(saleItem);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<SaleItem> findAll() {
        String sql = "select * from sale_item";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(SaleItem.class));
    }

    @Override
    public void delete(SaleItem saleItem) {
        String sql = "delete from sale_item where id = ?";
        try {
            jdbcTemplate.update(sql, saleItem.getId());
        } catch (Exception e) {
            // Log the exception or handle it as needed
            System.err.println("Error deleting SaleItem: " + e.getMessage());
        }
    }

    public Optional<SaleItem> findBySaleAndMedicine(Sale sale, Medicine medicine) {
        String sql = "SELECT * FROM sale_item WHERE sale_id = ? AND medicine_id = ?";
        try {
            SaleItem saleItem = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(SaleItem.class), sale.getId(), medicine.getId());
            return Optional.ofNullable(saleItem);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }
}

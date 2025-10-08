package uz.pdp.repository.impl.db;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import uz.pdp.model.dto.IdNameDto;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.dto.SaleItemDTOForWeb;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.model.entity.Sale;
import uz.pdp.repository.AuthUserRepository;
import uz.pdp.repository.SaleRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class SaleRepositoryImpl implements SaleRepository {
    private JdbcTemplate jdbcTemplate;
    private AuthUserRepository authUserRepository;
    private SaleItemRepositoryImpl saleItemRepository;
    @Override
    public Sale save(Sale sale) {
        Optional<Sale> byId = findById(sale.getId());
        String sql = (byId.isPresent())
                ? "UPDATE sale SET total_price = ?, cashier_id = ?, created_at = ?, buyer_id = ? WHERE id = ?"
                : "INSERT INTO sale (total_price, cashier_id, created_at, buyer_id, id) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                sale.getTotalPrice(),
                sale.getCashier().getId(),
                sale.getCreatedAt(),
                sale.getBuyer().getId(),
                sale.getId());
        return sale;
    }

    @Override
    public Optional<Sale> findById(String id) {
        String sql = "SELECT * FROM sale WHERE id = ?";
        try {
            Sale sale = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                Sale sale1 = new Sale();
                sale1.setId(rs.getString("id"));
                sale1.setTotalPrice(rs.getDouble("total_price"));
                sale1.setCashier(authUserRepository.findById(rs.getString("cashier_id")).get());
                return sale1;
            }, id);
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

    public List<SaleDTO> getUsersSale(AuthUser currentUser) {
        String sql = "select * from sale where buyer_id = ?";
        return jdbcTemplate.query(sql, new RowMapper<SaleDTO>() {
            @Override
            public SaleDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                SaleDTO saleDTO = new SaleDTO();
                saleDTO.setId(rs.getString("id"));
                Sale sale = findById(rs.getString("id"))
                        .orElseThrow(() -> new RuntimeException("sale not found"));
                saleDTO.setTotalPrice(rs.getDouble("total_price"));
                IdNameDto buyer = new IdNameDto();
                AuthUser cashier1 = authUserRepository.findById(rs.getString("cashier_id"))
                        .orElseThrow(() -> new RuntimeException("user not  found"));
                AuthUser buyer1 = authUserRepository.findById(rs.getString("buyer_id"))
                        .orElseThrow(() -> new RuntimeException("user not  found"));
                buyer.setId(buyer1.getId());
                buyer.setName(buyer1.getFullName());
                saleDTO.setBuyer(buyer);
                IdNameDto cashier = new IdNameDto();
                cashier.setId(cashier1.getId());
                cashier.setName(cashier1.getFullName());
                saleDTO.setCashier(cashier);
                saleDTO.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                List<SaleItemDTOForWeb> saleItems = saleItemRepository.findBySale(sale);
                saleDTO.setItems(saleItems);
                return saleDTO;
            }
        }, currentUser.getId());
    }
}

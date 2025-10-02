package uz.pdp.repository.impl.db;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import uz.pdp.model.entity.Medicine;
import uz.pdp.repository.MedicineRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static uz.pdp.repository.impl.db.CategoryRepositoryImpl.CATEGORIES;

@Repository
@AllArgsConstructor
public class MedicineRepositoryImpl implements MedicineRepository {
    private  JdbcTemplate jdbcTemplate;
//    public static final List<Medicine> medicines = new ArrayList<>(List.of(
//            new Medicine(
//                    "Paracetamol",
//                    "1111111111111",
//                    LocalDate.of(2025, 1, 1),
//                    LocalDate.of(2027, 1, 1),
//                    "Pain reliever and fever reducer",
//                    CATEGORIES.get(1).getId(),   // Tabletka
//                    100,
//                    1.50
//            ),
//            new Medicine(
//                    "Ambroxol",
//                    "2222222222222",
//                    LocalDate.of(2024, 12, 10),
//                    LocalDate.of(2026, 12, 10),
//                    "Cough syrup",
//                    CATEGORIES.get(0).getId(),   // Sirop
//                    50,
//                    3.20
//            ),
//            new Medicine(
//                    "Nurofen",
//                    "3333333333333",
//                    LocalDate.of(2025, 2, 15),
//                    LocalDate.of(2027, 2, 15),
//                    "Anti-inflammatory and pain relief",
//                    CATEGORIES.get(1).getId(),   // Tabletka
//                    80,
//                    2.75
//            )));

    @Override
    public  Medicine save(Medicine medicine) {
        Optional<Medicine> byId = findById(medicine.getId());
        String sql = (byId.isPresent())
                ? "UPDATE medicine SET name = ?, bar_code = ?, issue_date = ?, expiry_date = ?, description = ?, category_id = ?, quantity = ?, price = ? WHERE id = ?"
                :
                "INSERT INTO medicine ( name, bar_code, issue_date, expiry_date, description, category_id,quantity, price,id)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, medicine.getName(), medicine.getBarCode(), medicine.getIssueDate(), medicine.getExpiryDate(), medicine.getDescription(), medicine.getCategoryId(), medicine.getQuantity(), medicine.getPrice(),medicine.getId());
        return medicine;
    }

    @Override
    public  Optional<Medicine> findById(String id) {
        String sql = "SELECT * FROM medicine WHERE id = ?";
        try{
            Medicine medicine = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Medicine.class), id);
            return Optional.ofNullable(medicine);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Medicine> findAll() {
        String sql = "SELECT * FROM medicine order by bar_code ASC";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Medicine.class));
    }

    @Override
    public void delete(Medicine medicine) {
        String sql = "DELETE FROM medicine WHERE id = ?";
        jdbcTemplate.update(sql, medicine.getId());
    }
}

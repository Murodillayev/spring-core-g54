package uz.pdp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.model.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medicine extends BaseEntity {
    private String name;
    private String barCode;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String description;
    private Category category;
    private String quantity;
    private Double price;
}

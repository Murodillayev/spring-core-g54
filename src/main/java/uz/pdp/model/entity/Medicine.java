package uz.pdp.model.entity;

import lombok.*;
import uz.pdp.model.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Medicine extends BaseEntity {
    private String name;
    private String barCode;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String description;
    private String categoryId;
    private Integer quantity;
    private Double price;
}

package uz.pdp.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.model.entity.base.BaseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem extends BaseEntity {
    private Sale sale;
    private Medicine medicine;
    private Double price;
    private Integer quantity;
    private LocalDateTime createdAt;
}

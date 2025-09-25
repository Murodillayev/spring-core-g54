package uz.pdp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.model.entity.Medicine;
import uz.pdp.model.entity.Sale;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class SaleItemDTO {
    private String id;
    private Sale sale;
    private Medicine medicine;
    private Double price;
    private Integer quantity;
}

package uz.pdp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.model.entity.Medicine;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemDTOForWeb {
    private Medicine medicine;
    private Double unitPrice;
    private Integer quantity;
}

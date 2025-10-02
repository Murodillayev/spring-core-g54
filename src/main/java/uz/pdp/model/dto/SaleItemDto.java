package uz.pdp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.model.entity.Medicine;
import uz.pdp.model.entity.Sale;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class SaleItemDto {
    private UUID medicineId;
    private Double unitPrice;
    private Integer quantity;
}

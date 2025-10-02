package uz.pdp.model.dto;

import lombok.*;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
public class SaleItemDto {
    private String medicineId;
    private Double unitPrice;
    private Integer quantity;
}

package uz.pdp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class SaleDTO {
    private String id;
    private Double totalPrice;
    private IdNameDto cashier;
    private List<SaleItemDTOForWeb> items;
    private LocalDateTime createdAt;
}

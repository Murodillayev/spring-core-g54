package uz.pdp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.pdp.model.entity.AuthUser;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class SaleDTO {
    private String id;
    private Double totalPrice;
    private AuthUser cashier;
    private LocalDateTime createdAt;
}

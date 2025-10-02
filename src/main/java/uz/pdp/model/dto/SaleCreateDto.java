package uz.pdp.model.dto;

import lombok.*;
import uz.pdp.model.entity.AuthUser;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SaleCreateDto {
    private List<SaleItemDto> items;
    private String cashierId;
}

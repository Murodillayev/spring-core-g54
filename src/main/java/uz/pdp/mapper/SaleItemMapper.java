package uz.pdp.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.model.dto.SaleItemDTO;
import uz.pdp.model.entity.SaleItem;

@Component
public class SaleItemMapper {
    public SaleItem fromDto(SaleItemDTO dto) {
        SaleItem saleItem = new SaleItem();
        saleItem.setId(dto.getId());
        saleItem.setMedicine(dto.getMedicine());
        saleItem.setQuantity(dto.getQuantity());
        saleItem.setPrice(dto.getPrice());
        return saleItem;
    }

    public SaleItemDTO toDto(SaleItem save) {
        SaleItemDTO dto = new SaleItemDTO();
        dto.setId(save.getId());
        dto.setMedicine(save.getMedicine());
        dto.setQuantity(save.getQuantity());
        dto.setPrice(save.getPrice());
        return dto;
    }
}

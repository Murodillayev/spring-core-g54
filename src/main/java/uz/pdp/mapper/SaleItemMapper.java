package uz.pdp.mapper;

import org.springframework.stereotype.Component;
import uz.pdp.model.dto.SaleItemDTO;
import uz.pdp.model.entity.SaleItem;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleItemMapper {
    public SaleItem fromDto(SaleItemDTO dto) {
        SaleItem saleItem = new SaleItem();
        saleItem.setSale(dto.getSale());
        saleItem.setMedicine(dto.getMedicine());
        saleItem.setQuantity(dto.getQuantity());
        saleItem.setPrice(dto.getPrice());
        saleItem.setCreatedAt(dto.getCreatedAt());
        return saleItem;
    }

    public SaleItemDTO toDto(SaleItem save) {
        SaleItemDTO dto = new SaleItemDTO();
        dto.setSale(save.getSale());
        dto.setMedicine(save.getMedicine());
        dto.setQuantity(save.getQuantity());
        dto.setPrice(save.getPrice());
        dto.setCreatedAt(save.getCreatedAt());
        return dto;
    }

    public List<SaleItemDTO> toDtoList(List<SaleItem> saleItems) {
        return saleItems.stream().map(this::toDto).collect(Collectors.toList());
    }
}

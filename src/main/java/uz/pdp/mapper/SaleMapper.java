package uz.pdp.mapper;

import org.springframework.context.annotation.Configuration;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.entity.Sale;

import java.util.List;

@Configuration
public class SaleMapper {
    public SaleDTO toDTO(Sale save) {
        SaleDTO dto = new SaleDTO();
        dto.setId(save.getId());
        dto.setCashier(save.getCashier());
        dto.setTotalPrice(save.getTotalPrice());
        dto.setCreatedAt(save.getCreatedAt());
        return dto;
    }

    public Sale fromDTO(SaleDTO dto) {
        Sale sale = new Sale();
        sale.setId(dto.getId());
        sale.setCashier(dto.getCashier());
        sale.setTotalPrice(dto.getTotalPrice());
        return sale;
    }

    public List<SaleDTO> toDtoList(List<Sale> all) {
        return all.stream().map(this::toDTO).toList();
    }
}

package uz.pdp.mapper;

import org.springframework.context.annotation.Configuration;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.entity.Sale;

@Configuration
public class SaleMapper {
    public SaleDTO toDTO(Sale save) {
        SaleDTO dto = new SaleDTO();
        dto.setId(save.getId());
        dto.setCashier(save.getCashier());
        dto.setTotalPrice(save.getTotalPrice());
        return dto;
    }

    public Sale fromDTO(SaleDTO dto) {
        Sale sale = new Sale();
        sale.setId(dto.getId());
        sale.setCashier(dto.getCashier());
        sale.setTotalPrice(dto.getTotalPrice());
        return sale;
    }
}

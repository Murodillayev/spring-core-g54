package uz.pdp.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import uz.pdp.model.dto.IdNameDto;
import uz.pdp.model.dto.SaleCreateDto;
import uz.pdp.model.dto.SaleDTO;
import uz.pdp.model.dto.SaleItemDto;
import uz.pdp.model.entity.AuthUser;
import uz.pdp.model.entity.Medicine;
import uz.pdp.model.entity.Sale;
import uz.pdp.model.entity.SaleItem;
import uz.pdp.repository.SaleItemRepository;
import uz.pdp.validator.AuthUserValidator;
import uz.pdp.validator.MedicineValidator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SaleMapper {

    private final AuthUserValidator authUserValidator;
    private final SaleItemRepository saleItemRepository;
    private final MedicineValidator medicineValidator;

    public SaleDTO toDTO(Sale save) {
        SaleDTO dto = new SaleDTO();
        dto.setId(save.getId());
        dto.setCreatedAt(save.getCreatedAt());
        AuthUser cashier = save.getCashier();
        dto.setCashier(IdNameDto.builder()
                .id(cashier.getId())
                .name(cashier.getFullName())
                .build());
        dto.setTotalPrice(save.getTotalPrice());
        return dto;
    }

    public Sale fromDTO(SaleCreateDto dto) {
        Sale sale = new Sale();
        AuthUser cashier = authUserValidator.existsAndGet(dto.getCashierId());
        List<SaleItemDto> items = dto.getItems();
        double totalPrice = 0d;
        for (SaleItemDto item : items) {
            Medicine medicine = medicineValidator.existAndGet(String.valueOf(item.getMedicineId()));
            SaleItem saleItem = new SaleItem();
            saleItem.setQuantity(item.getQuantity());
            saleItem.setPrice(item.getUnitPrice());
            saleItem.setMedicine(medicine);
            saleItem.setSale(sale);
            totalPrice += item.getUnitPrice() * item.getQuantity();
            saleItemRepository.save(saleItem);
        }
        sale.setCreatedAt(LocalDateTime.now());
        sale.setCashier(cashier);
        sale.setTotalPrice(totalPrice);
        return sale;
    }

    public List<SaleDTO> toDtoList(List<Sale> all) {
        return all.stream().map(this::toDTO).toList();
    }
}

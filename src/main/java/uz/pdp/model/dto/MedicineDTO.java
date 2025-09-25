package uz.pdp.model.dto;

import lombok.*;
import uz.pdp.model.entity.Category;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDTO {
    private String id;
    private String name;
    private String barCode;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String description;
    private Category category;
    private Integer quantity;
    private Double price;
}

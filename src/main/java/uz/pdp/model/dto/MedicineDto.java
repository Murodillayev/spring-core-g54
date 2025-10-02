package uz.pdp.model.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MedicineDto {
    private String id;
    private String name;
    private String barCode;
    private LocalDate issueDate;
    private LocalDate expiryDate;
    private String description;
    private IdNameDto category;
    private Integer quantity;
    private Double price;
}

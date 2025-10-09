package uz.pdp;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book {


    private String id = UUID.randomUUID().toString();

    @NotBlank(message = "Name bosh bolmasligi kerak")
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    private String author;

    @NotNull
    @Positive
    private Integer publishYear;

    @Min(0)
    @NotNull
    private Integer pages;

    public Book(String name, String author, Integer publishYear, Integer pages) {
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
        this.pages = pages;
    }

}

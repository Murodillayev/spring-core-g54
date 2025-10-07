package uz.pdp;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String author;
    private Integer publishYear;
    private Integer pages;

    public Book(String name, String author, Integer publishYear, Integer pages) {
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
        this.pages = pages;
    }

    public Book validate() {
        if (name == null || name.isEmpty()) {
            throw new BadRequestException("Name is null");
        }
        if (author == null || author.isEmpty()) {
            throw new BadRequestException("Author is null");
        }
        if (publishYear == null || publishYear < 0) {
            throw new BadRequestException("Publish Year is null");
        }
        if (pages == null || pages < 0) {
            throw new BadRequestException("Pages is null");
        }
        return this;
    }
}

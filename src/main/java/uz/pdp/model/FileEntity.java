package uz.pdp.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileEntity {
    private String name;
    private String originalName;
    private String path;
    private String contentType;
    private Long size;
}

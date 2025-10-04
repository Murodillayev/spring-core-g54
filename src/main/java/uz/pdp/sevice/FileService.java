package uz.pdp.sevice;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.model.FileEntity;
import uz.pdp.FileRepository;
import uz.pdp.props.AppProps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileService {

    private final FileRepository repository;
    private final AppProps appProps;

    public FileService(FileRepository repository, AppProps appProps) {
        this.repository = repository;
        this.appProps = appProps;
    }

    public void upload(MultipartFile file) {
        String fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        Path path = Path.of(appProps.getRootPath(), fileName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(fileName);
        fileEntity.setPath(path.toString());
        fileEntity.setSize(file.getSize());
        fileEntity.setContentType(file.getContentType());
        fileEntity.setOriginalName(file.getOriginalFilename());
        repository.save(fileEntity);
    }

    public ResponseEntity<Resource> download(String filename) {
        FileEntity fileEntity = repository.findByName(filename);
        Path path = Path.of(fileEntity.getPath());

        FileSystemResource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=" + fileEntity.getOriginalName())
                .contentType(MediaType.parseMediaType(fileEntity.getContentType()))
                .body(resource);


    }

    public String uploadImage(MultipartFile file) {

        String fileName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        Path path = Path.of(appProps.getRootPath(), fileName);

        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(fileName);
        fileEntity.setPath(path.toString());
        fileEntity.setSize(file.getSize());
        fileEntity.setContentType(file.getContentType());
        fileEntity.setOriginalName(file.getOriginalFilename());
        repository.save(fileEntity);

        return appProps.getDomen() + "/download/" + fileName;
    }
}

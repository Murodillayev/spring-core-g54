package uz.pdp.config;

import org.springframework.stereotype.Repository;
import uz.pdp.model.FileEntity;
import uz.pdp.FileRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryFileRepository implements FileRepository {

    private final List<FileEntity> FILES = new ArrayList<>();

    @Override
    public void save(FileEntity file) {
        FileEntity byName = findByName(file.getName());
        if (byName != null) {
            FILES.remove(byName);
        }
        FILES.add(file);
    }

    @Override
    public FileEntity findByName(String name) {
        return FILES
                .stream()
                .filter(f -> f.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}

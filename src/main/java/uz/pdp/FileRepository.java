package uz.pdp;

import uz.pdp.model.FileEntity;

public interface FileRepository {

    void save(FileEntity file);

    FileEntity findByName(String name);
}

package uz.pdp.sevice;

import org.springframework.stereotype.Service;
import uz.pdp.controller.UserDto;
import uz.pdp.model.AuthUser;

import java.util.UUID;

@Service
public class UserService {

    private final FileService fileService;

    public UserService(FileService fileService) {
        this.fileService = fileService;
    }

    public void create(UserDto dto) {
        // validate file format (.img,imeg,png), validate file size
        // generate image url and save to url with users

        String imgUrl = fileService.uploadImage(dto.getImage()); // http://localhost:8080/file/download/987123gjhadsjkghasduiyt.img
        System.out.println(imgUrl);
        AuthUser authUser = new AuthUser();
        authUser.setUsername(dto.getUsername());
        authUser.setPassword(dto.getPassword());
        authUser.setId(UUID.randomUUID().toString());
        authUser.setImageUrl(imgUrl);
        authUser.setEmail(dto.getEmail());

        //save user

    }
}

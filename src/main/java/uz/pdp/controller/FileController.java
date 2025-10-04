package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.sevice.FileService;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileService service;

    //    @RequestMapping
    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        service.upload(file);
        return "index";
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> download(@PathVariable("filename") String filename) {

        return service.download(filename);
    }




}

package com.amigoscode.s3;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/s3")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @GetMapping
    public List<String> listBuckets() {
        return s3Service.listBuckets();
    }

    @GetMapping("files")
    public List<String> listFilesInBucket() {
        return s3Service.listFilesInBucket();
    }

    @PostMapping("{customerId}")
    public void saveImage(@RequestParam("file") MultipartFile file,
                          @PathVariable("customerId") String customerId) {
        //s3Service.putObject(file, customerId);
    }
}

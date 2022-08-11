package com.sparta.week3pjt.controller;

import com.sparta.week3pjt.S3.S3GetUrl;
import com.sparta.week3pjt.S3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class S3Controller {

    private final S3Uploader s3Uploader;
    private final S3GetUrl s3GetUrl;

    @PostMapping("/images")
    public String upload( @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {


        return  s3Uploader.upload(multipartFile, "static");
    }

    @GetMapping("/images")
    public String getImages(String key) {

        return s3GetUrl.getUrl(key);
    }

}
package com.uit.se.gogo.service.impl;

import com.cloudinary.Cloudinary;
import com.uit.se.gogo.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryServiceImpl implements CloudinaryService {
    private final Cloudinary cloudinary;

    @Override
    public Map uploadFile(MultipartFile file, String directory, String name) {
        try {
            Map<String, String> options = new HashMap<>();
            options.put("folder", directory);
            options.put("public_id", name);
            return this.cloudinary.uploader().upload(file.getBytes(), options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

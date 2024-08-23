package com.example.qLyDatBan.quanLyDatBan.service.impl;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;


@Service
public class FirebaseService {
    private String bucketName = "quanlybooking-c78b6.appspot.com"; // Thay bằng tên bucket của bạn
    private Storage storage;

    @Autowired
    public FirebaseService(Storage storage) {
        this.storage = storage;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        fileName = UUID.randomUUID() + fileName;
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
        storage.create(blobInfo, file.getBytes());
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }

    public String uploadFile(byte[] fileBytes, String fileName, String contentType) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(fileBytes);

        // Tạo thông tin blob và tải lên Firebase Storage
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(contentType).build();
        storage.create(blobInfo, inputStream);

        // Trả về URL của file đã tải lên
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }
}

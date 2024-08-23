package com.example.qLyDatBan.quanLyDatBan.controller;


import com.example.qLyDatBan.quanLyDatBan.DTO.FileUploadRequest;
import com.example.qLyDatBan.quanLyDatBan.DTO.Response;
import com.example.qLyDatBan.quanLyDatBan.service.impl.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*")
public class FileController {
    @Autowired
    private FirebaseService firebaseService;

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestPart("image") MultipartFile file) {
        try {
            String fileUrl = firebaseService.uploadFile(file);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response<>(HttpStatus.OK.value(), "Update thành công.", fileUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new Response<>(HttpStatus.NOT_FOUND.value(), "File upload failed: " + e.getMessage()));
        }
    }
}

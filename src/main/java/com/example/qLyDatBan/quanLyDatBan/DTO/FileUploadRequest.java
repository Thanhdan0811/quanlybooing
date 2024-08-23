package com.example.qLyDatBan.quanLyDatBan.DTO;

public class FileUploadRequest {
    private String file;
    private String fileName;
    private String contentType; // Thêm trường này

    // Getters and Setters
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}

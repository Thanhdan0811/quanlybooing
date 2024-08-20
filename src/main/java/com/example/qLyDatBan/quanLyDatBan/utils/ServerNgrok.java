package com.example.qLyDatBan.quanLyDatBan.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerNgrok {
    public static void connectNgrok() {
        String publicUrl = null;
        try {
            // Khởi chạy ngrok với cổng 8080
            String ngrokPath = "C:\\Users\\ngthd\\Downloads\\ngrok-v3-stable-windows-amd64\\ngrok.exe";
            ProcessBuilder builder = new ProcessBuilder(ngrokPath, "http", "8080"); // Cổng 8080 là cổng mà Spring Boot thường sử dụng
            builder.redirectErrorStream(true);
            Process process = builder.start();

            // Đọc đầu ra từ ngrok
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            Pattern pattern = Pattern.compile("Forwarding\\s+(https?://[\\w.-]+)");

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    publicUrl = matcher.group(1);
                    System.out.println("Public URL: " + publicUrl);
                    break;
                }
            }

            if (publicUrl == null) {
                System.err.println("Không tìm thấy URL công khai.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

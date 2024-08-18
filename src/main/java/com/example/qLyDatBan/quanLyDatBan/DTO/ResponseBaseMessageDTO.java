package com.example.qLyDatBan.quanLyDatBan.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBaseMessageDTO {
    boolean status;
    String message;
    String error;
}

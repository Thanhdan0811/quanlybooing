package com.example.qLyDatBan.quanLyDatBan.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingStatusDTO {
    int booking_id;
    int status;
}

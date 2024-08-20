package com.example.qLyDatBan.quanLyDatBan.DTO;

import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO extends BaseDTO{

    String name;
    String phone;
    String email;
    ViewsEntity view;
    int booking_status;
    String addition_note;
    Date booking_date;

}

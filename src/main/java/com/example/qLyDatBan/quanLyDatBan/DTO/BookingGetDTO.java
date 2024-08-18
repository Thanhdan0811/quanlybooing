package com.example.qLyDatBan.quanLyDatBan.DTO;


import com.example.qLyDatBan.quanLyDatBan.entity.CustomerDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingGetDTO {
    int id;
    int booking_status;
    Date booking_date;
    String addition_note;
    java.util.Date arrived_time;
    java.util.Date expected_time;
    ViewDTO views;
    CustomerDTO customerDetail;
}

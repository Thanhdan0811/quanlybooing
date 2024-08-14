package com.example.qLyDatBan.quanLyDatBan.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingAddRequestDTO {

    String name;
    String phone;
    String email;
    int view_id;
    int booking_status;
    String addition_note;
    String arrived_time;
    String expected_time;
    String booking_date;

}

package com.example.qLyDatBan.quanLyDatBan.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder
public class BookingTimeQuery {
    private Date arrived_time;
    private Date expected_time;
}

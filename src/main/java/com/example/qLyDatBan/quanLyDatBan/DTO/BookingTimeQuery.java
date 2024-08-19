package com.example.qLyDatBan.quanLyDatBan.DTO;


import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingTimeQuery {
	private Date arrived_time;
	private Date expected_time;
}

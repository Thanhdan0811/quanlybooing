package com.example.qLyDatBan.quanLyDatBan.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO  {

	String name;
	String phone;
	String email;
	int view_id;
	int booking_status;
	String addition_note;
	Date booking_date;

}

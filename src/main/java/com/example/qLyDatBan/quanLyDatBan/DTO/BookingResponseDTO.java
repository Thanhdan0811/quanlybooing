package com.example.qLyDatBan.quanLyDatBan.DTO;

import java.sql.Date;

import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;

public class BookingResponseDTO extends BaseDTO {

	String name;
	String phone;
	String email;
	ViewsEntity view;
	int booking_status;
	String addition_note;
	Date booking_date;
}

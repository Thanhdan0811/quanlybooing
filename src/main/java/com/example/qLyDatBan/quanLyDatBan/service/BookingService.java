package com.example.qLyDatBan.quanLyDatBan.service;

import java.sql.Date;
import java.util.List;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;

public interface BookingService extends BaseService<BookingEntity> {
	boolean changeStatus(int bookingStatus);

	List<BookingEntity> findBookingByDateCategory(String categoryName, Date date);
}

package com.example.qLyDatBan.quanLyDatBan.service;

import java.sql.Date;
import java.util.List;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;

public interface BookingService extends BaseService<BookingEntity> {
	BookingEntity changeStatus(int id, int bookingStatus);

	List<ViewsEntity> getViewsByDateCategory(int category_id, Date date);
}

package com.example.qLyDatBan.quanLyDatBan.service;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface BookingService extends BaseService<BookingEntity> {
	BookingEntity changeStatus(int id, int bookingStatus);
	List<ViewsEntity> findAllViewByDate(LocalDate dateSearch);
	List<ViewsEntity> getViewsByDateCategory(int category_id, Date date);
}

package com.example.qLyDatBan.quanLyDatBan.service;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;

import java.time.LocalDate;
import java.util.List;

public interface BookingService extends BaseService<BookingEntity> {
	boolean changeStatus(int bookingStatus);
	List<ViewsEntity> findAllViewByDate(LocalDate dateSearch);
}

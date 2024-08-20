package com.example.qLyDatBan.quanLyDatBan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.CustomerDetailEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.repository.BookingRepository;
import com.example.qLyDatBan.quanLyDatBan.service.BookingService;
import com.example.qLyDatBan.quanLyDatBan.service.CustomerDetailService;
import com.example.qLyDatBan.quanLyDatBan.service.ViewsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {
	@Autowired
	private ViewsService viewsService;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private CustomerDetailService cusDetailService;

	@Override
	public BookingEntity save(BookingEntity bookingEntity, String mode) {
		// check view existed
		Optional<ViewsEntity> viewEntity = this.viewsService.findById(bookingEntity.getView().getId());
		System.out.println("View Entity: " + bookingEntity.getView().getId());
		if (viewEntity.isEmpty())
			return null;

		bookingEntity.setView(viewEntity.get());

		// Check arrived time and expected time is available.

		// create customer entity
		CustomerDetailEntity customerEntity = this.cusDetailService.save(bookingEntity.getCustomerDetail(), "add");
		if (customerEntity == null)
			return null;

		bookingEntity.setCustomerDetail(customerEntity);

		return this.bookingRepository.save(bookingEntity);
	}

	@Override
	public boolean delete(BookingEntity bookingEntity) {
		return true;
	}

	@Override
	public boolean deleteById(int id) {
		Optional<BookingEntity> existed = findById(id);
		if (existed.isPresent()) {
			bookingRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<BookingEntity> findAll() {
		return this.bookingRepository.findAll();
	}

	@Override
	public Optional<BookingEntity> findById(int id) {
		Optional<BookingEntity> booking = bookingRepository.findById(id);
		return booking;
	}

	@Override
	public boolean changeStatus(int bookingStatus) {
		List<Integer> statusList = new ArrayList<>();
		statusList.add(0); // từ chối
		statusList.add(1); // chấp nhận
		statusList.add(2); // chờ xử lý
		statusList.add(3); // hoàn thành
		statusList.add(4); // hủy

		Optional<BookingEntity> bookingEntity = this.bookingRepository.findById(bookingStatus);
		if (bookingEntity.isEmpty()) {
			System.out.println("không tìm tháy booking");
			return false;
		}

		if (!statusList.contains(bookingStatus)) {
			System.out.println("không có status");
			return false;
		}

		int viewStatus = bookingStatus == 1 ? 1 : 0;

		// update status view

		bookingEntity.get().setBooking_status(bookingStatus);

		this.bookingRepository.save(bookingEntity.get());

		return true;
	}
}

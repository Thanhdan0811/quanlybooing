package com.example.qLyDatBan.quanLyDatBan.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.repository.BookingRepository;
import com.example.qLyDatBan.quanLyDatBan.service.BookingService;
import com.example.qLyDatBan.quanLyDatBan.service.CustomerDetailService;
import com.example.qLyDatBan.quanLyDatBan.service.ViewsService;
import com.example.qLyDatBan.quanLyDatBan.utils.DateUtil;

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

	@Autowired
	private DateUtil dateUtil;

	@Override
	public BookingEntity save(BookingEntity bookingEntity, String mode) {
		// check view existed
//		Optional<ViewsEntity> viewEntity = this.viewsService.findById(bookingEntity.getViews().getId());
//		if (viewEntity.isEmpty())
//			return null;

//		bookingEntity.setViews(viewEntity.get());

		// Check arrived time and expected time is available.

//		System.out.println("viewEntity.get() " + viewEntity + "  " + bookingEntity.getBooking_date());

//		List<BookingTimeQuery> listBook = this.bookingRepository.findFilterBy(viewEntity.get(),
//				bookingEntity.getBooking_date());
//
//		System.out.println("listBook" + listBook);

//		if (bookingEntity.getArrived_time().getTime() >= bookingEntity.getExpected_time().getTime()) {
//			System.out.println("thời gian arrived không được sau thời gian expected.");
//			return null;
//		}

//		for (BookingTimeQuery bookingTime : listBook) {
//			long arrivedTime = bookingTime.getArrived_time().getTime();
//			long expectedTime = bookingTime.getExpected_time().getTime();
//			long newArrivedTime = bookingEntity.getArrived_time().getTime();
//			long newExpectedTime = bookingEntity.getExpected_time().getTime();
//			System.out.println("chạy vào đây");
//
//			System.out.println("check 1" + "  " + newArrivedTime + "  " + arrivedTime);
//			System.out.println("check 2" + "  " + newArrivedTime + "  " + arrivedTime);
//			System.out.println("check 3" + "  " + newArrivedTime + "  " + arrivedTime);
//			System.out.println("check 4" + "  " + newArrivedTime + "  " + arrivedTime);
//
//			if ((newArrivedTime >= arrivedTime && newArrivedTime <= expectedTime)
//					|| (newExpectedTime >= arrivedTime && newExpectedTime <= expectedTime)) {
//				System.out.println("thời gian booking đã được đặt trước đó.");
//				return null;
//			}

//		}

		// create customer entity
//		CustomerDetailEntity customerEntity = this.cusDetailService.save(bookingEntity.getCustomerDetail(), "add");
//		if (customerEntity == null)
		return null;
//
//		bookingEntity.setCustomerDetail(customerEntity);
//
//		return this.bookingRepository.save(bookingEntity);
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

		// update status booking

		bookingEntity.get().setBooking_status(bookingStatus);

		this.bookingRepository.save(bookingEntity.get());

		return true;
	}

	// filter booking theo ngày
	@Override
	public List<BookingEntity> findBookingByDateCategory(String categoryName, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}

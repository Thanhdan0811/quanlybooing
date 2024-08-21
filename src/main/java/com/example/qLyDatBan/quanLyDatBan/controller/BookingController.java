package com.example.qLyDatBan.quanLyDatBan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.qLyDatBan.quanLyDatBan.DTO.BookingDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.Response;
import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.mapper.Mapper;
import com.example.qLyDatBan.quanLyDatBan.service.BookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private Mapper mapper;

	@GetMapping("/all")
	public List<BookingDTO> getAllBooking() {
		List<BookingEntity> bookings = this.bookingService.findAll();
		List<BookingDTO> bookingsDTO = new ArrayList<>();

		for (BookingEntity book : bookings) {
			bookingsDTO.add(mapper.map(book, BookingDTO.class));
		}
		return bookingsDTO;
	}

	// Tạo đơn từ phía khách hàng
	@PostMapping("/add-booking")
	public ResponseEntity<?> addBooking(@RequestBody BookingDTO bookingBody) {
		BookingEntity saveBooking = this.bookingService.save(mapper.map(bookingBody, BookingEntity.class), "add");
		if (saveBooking == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Response<>(HttpStatus.NOT_FOUND.value(), "Booking không thành công"));
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response<>(HttpStatus.CREATED.value(), "Booking đã được tạo"));
	}

	// Thay đổi trạng thái
	@PutMapping("/update-status")
	public boolean updateBookingStatus(@RequestBody int bookingStatus) {

		boolean isUpdated = this.bookingService.changeStatus(bookingStatus);

		return isUpdated;
	}

}

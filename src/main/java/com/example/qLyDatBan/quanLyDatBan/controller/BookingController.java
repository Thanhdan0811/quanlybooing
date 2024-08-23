package com.example.qLyDatBan.quanLyDatBan.controller;

import java.sql.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.qLyDatBan.quanLyDatBan.DTO.BookingDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.BookingResponseDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.Response;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewResponseNoCat;
import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
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
	public ResponseEntity<?> getAllBooking() {
		List<BookingEntity> bookings = this.bookingService.findAll();
		List<BookingResponseDTO> bookingsDTO = new ArrayList<>();

		for (BookingEntity book : bookings) {
			bookingsDTO.add(mapper.map(book, BookingResponseDTO.class));
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response<>(HttpStatus.CREATED.value(), "Danh sách booking.", bookingsDTO));
//		return bookingsDTO;
	}

	// Tạo đơn từ phía khách hàng
	@PostMapping("/add-booking")
	public ResponseEntity<?> addBooking(@RequestBody BookingDTO bookingBody) {
		try {
			BookingEntity saveBooking = this.bookingService
					.save(mapper.mapBookingCreate(bookingBody, BookingEntity.class), "add");
			if (saveBooking == null) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(new Response<>(HttpStatus.NOT_FOUND.value(), "Booking không thành công"));
			}
			BookingResponseDTO newBooking = mapper.map(saveBooking, BookingResponseDTO.class);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Response<>(HttpStatus.CREATED.value(), "Booking đã được tạo", newBooking));
		} catch (RuntimeException e) {

			String message = e.getMessage().replace("java.lang.RuntimeException:", "").trim();

			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Response<>(HttpStatus.NOT_FOUND.value(), message));
		}
	}

	// Thay đổi trạng thái
	@PutMapping("/update-status")
	public ResponseEntity<?> updateBookingStatus(@RequestParam int id, int status) {

		BookingEntity isUpdated = this.bookingService.changeStatus(id, status);

		if (isUpdated == null) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
					.body(new Response<>(HttpStatus.NOT_MODIFIED.value(), "Update không thành công."));
		}
		BookingResponseDTO booking = mapper.map(isUpdated, BookingResponseDTO.class);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(HttpStatus.OK.value(), "Update thành công.", booking));
	}

	// Lọc bàn còn trống bằng date và id_category
	@GetMapping("/getFilteredViews")
	public ResponseEntity<?> getViews(@RequestParam int category_id, Date date) {
		System.out.println(date);
		List<ViewsEntity> views = bookingService.getViewsByDateCategory(category_id, date);
		if (views == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Response<>(HttpStatus.NOT_FOUND.value(), "Không còn bàn trống trong ngày " + date));
		}
		List<ViewResponseNoCat> filteredViews = new ArrayList<>();
		for (ViewsEntity view : views) {
			filteredViews.add(mapper.map(view, ViewResponseNoCat.class));
		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(HttpStatus.OK.value(), "Danh sách bàn còn trống ngày " + date, filteredViews));
	}

}

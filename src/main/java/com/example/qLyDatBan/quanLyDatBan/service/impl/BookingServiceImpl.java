package com.example.qLyDatBan.quanLyDatBan.service.impl;

import java.sql.Date;
import java.time.LocalDate;
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

	@Autowired
	MailService mailService;

	@Override
	public BookingEntity save(BookingEntity bookingEntity, String mode) {
		try {
			// check view existed
			Optional<ViewsEntity> viewEntity = this.viewsService.findById(bookingEntity.getViews().getId());
			System.out.println("View Entity: " + bookingEntity.getViews().getId());
			if (viewEntity.isEmpty())
				return null;

			bookingEntity.setViews(viewEntity.get());
			System.out.println("Trước khi đến bước này");
			// Check arrived time and expected time is available.
			LocalDate dateNow = LocalDate.now();

			System.out.println("CheckViewHasBookAtDate: " + bookingEntity.getBooking_date() + "  " + dateNow + "  "
					+ bookingEntity.getBooking_date().toLocalDate().isBefore(dateNow));

			if (bookingEntity.getBooking_date().toLocalDate().isBefore(dateNow)) {
				throw new RuntimeException("Thời gian đặt phải từ thời điểm hiện tại.");
//				return null;
			}
			int checkExists = this.bookingRepository.CheckViewHasBookAtDate(bookingEntity.getViews().getId(),
					bookingEntity.getBooking_date());

			if (checkExists > 0) {
				throw new RuntimeException("Ngày đặt và view đã được booking trước đó.");
			}

			// create customer entity
			CustomerDetailEntity customerEntity = this.cusDetailService.save(bookingEntity.getCustomerDetail(), "add");
			if (customerEntity == null)
				return null;

			bookingEntity.setCustomerDetail(customerEntity);

			bookingEntity.setBooking_status(2);

			return this.bookingRepository.save(bookingEntity);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

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
	public BookingEntity changeStatus(int id, int bookingStatus) {
		List<Integer> statusList = new ArrayList<>();
		statusList.add(0); // từ chối
		statusList.add(1); // chấp nhận
		statusList.add(2); // chờ xử lý
		statusList.add(3); // hoàn thành
		statusList.add(4); // hủy

		Optional<BookingEntity> bookingEntity = this.bookingRepository.findById(id);

		if (bookingEntity.isEmpty()) {
			System.out.println("không tìm tháy booking");
			return null;
		}

		if (!statusList.contains(bookingStatus)) {
			System.out.println("không có status");
			return null;
		}

		// update status booking

		BookingEntity booking = bookingEntity.get();

		booking.setBooking_status(bookingStatus);

		this.bookingRepository.save(booking);
		if (booking.getBooking_status() == 0 || booking.getBooking_status() == 1) {
			mailService.sendMail(booking);
		}

		return booking;
	}

	@Override
	public List<ViewsEntity> findAllViewByDate(LocalDate dateSearch) {
		List<BookingEntity> listBooking = this.bookingRepository.findAllByDate(dateSearch);
		List<ViewsEntity> viewsEntities = new ArrayList<>();

		for (BookingEntity book : listBooking) {
			viewsEntities.add(book.getViews());
		}

		return viewsEntities;
	}

	// filter booking theo ngày và id_category
	@Override
	public List<ViewsEntity> getViewsByDateCategory(int category_id, Date date) {
		System.out.println("category_id " + category_id + "  " + date);
		List<ViewsEntity> filteredViews = new ArrayList<>();
		try {
			List<ViewsEntity> results = bookingRepository.filterByDateCategory(category_id, date);

			for (ViewsEntity result : results) {
//				ViewsEntity view = new ViewsEntity();
				System.out.println("id : " + result.getId());
//				System.out.println("id : " + result);
//				System.out.println("id : " + result);
//				System.out.println("id : " + result);
//				System.out.println("id : " + result);
//
//				view.setId((Integer) result[0]);
//				view.setName((String) result[1]);
//				view.setDesk_img((String) result[2]);
//				view.setDescription((String) result[3]);
//				view.setIsDeleted((Integer) result[4]);
//				view.setCategory((Integer) result[5]);

				filteredViews.add(result);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return filteredViews;
	}
}

package com.example.qLyDatBan.quanLyDatBan.service.impl;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.repository.BookingRepository;
import com.example.qLyDatBan.quanLyDatBan.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepo;

	@Override
	public boolean save(BookingEntity bookingEntity, String mode) {
		return false;
	}

	@Override
	public boolean delete(BookingEntity bookingEntity) {
		return true;
	}

	@Override
	public boolean deleteById(int id) {
		Optional<BookingEntity> existed = findById(id);
		if (existed.isPresent()) {
			bookingRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<BookingEntity> findAll() {
		return List.of();
	}

	@Override
	public Optional<BookingEntity> findById(int Id) {
		return Optional.empty();
	}
}

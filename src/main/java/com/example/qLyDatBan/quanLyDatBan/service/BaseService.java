package com.example.qLyDatBan.quanLyDatBan.service;

import java.util.List;
import java.util.Optional;

import com.example.qLyDatBan.quanLyDatBan.DTO.CategoryDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;

public interface BaseService<T> {
	T save(T t, String mode);

	boolean deleteById(int id);

	List<T> findAll();

	Optional<T> findById(int Id);
}

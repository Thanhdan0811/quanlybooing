package com.example.qLyDatBan.quanLyDatBan.service;

import java.util.List;
import java.util.Optional;

import com.example.qLyDatBan.quanLyDatBan.DTO.CategoryDTO;

public interface BaseService<T> {

	// trả ra object để kiểm tra cho cả add và update
	T save(T t, String mode);

	boolean deleteById(int id);

	List<T> findAll();

	Optional<T> findById(int Id);
}

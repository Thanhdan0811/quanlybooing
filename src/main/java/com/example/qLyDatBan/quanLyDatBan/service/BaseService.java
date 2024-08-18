package com.example.qLyDatBan.quanLyDatBan.service;

import java.util.List;
import java.util.Optional;

import com.example.qLyDatBan.quanLyDatBan.DTO.CategoryDTO;

public interface BaseService<T> {
	boolean save(T t, String mode);

	boolean delete(T t);

	boolean deleteById(int id);

	List<T> findAll();

	Optional<T> findById(int Id);
}

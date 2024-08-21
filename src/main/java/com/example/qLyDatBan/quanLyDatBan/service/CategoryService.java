package com.example.qLyDatBan.quanLyDatBan.service;

import java.util.List;

import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;

public interface CategoryService extends BaseService<CategoryEntity> {
	boolean findByIdAndUpload(int id, CategoryEntity categoryEntity);

	List<CategoryEntity> findAllByIsDeleted(int number);
}

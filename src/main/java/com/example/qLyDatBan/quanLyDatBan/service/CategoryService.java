package com.example.qLyDatBan.quanLyDatBan.service;

import java.util.List;

import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;

public interface CategoryService extends BaseService<CategoryEntity> {
	public List<ViewsEntity> getViewsById(int id);

	List<CategoryEntity> findAllByIsDeleted(int number);
}

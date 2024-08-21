package com.example.qLyDatBan.quanLyDatBan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.repository.CategoryRepository;
import com.example.qLyDatBan.quanLyDatBan.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	// add và update
	// dùng mode để định nghĩa là add hay update
	@Override
	public CategoryEntity save(CategoryEntity category, String mode) {
		try {
			if (mode.equals("update")) {
				Optional<CategoryEntity> existing = categoryRepository.findById(category.getId());
				if (!existing.isPresent()) {
					return null;
				}

				CategoryEntity oldCategory = existing.get();
				if (category.getName().isEmpty()) {
					category.setName(oldCategory.getName());
				}
				if (category.getDescription().isEmpty()) {
					category.setDescription(oldCategory.getDescription());
				}

				List<ViewsEntity> views = new ArrayList<ViewsEntity>();
				views = oldCategory.getListViews();
				category.setListViews(views);
			}
			this.categoryRepository.save(category);
			return category;
		} catch (Exception err) {
			err.printStackTrace();
			return null;
		}
	}

	// delete mềm
	// 0 là đang sử dụng
	// 1 là xoá
	@Override
	public boolean deleteById(int id) {
		Optional<CategoryEntity> existing = findById(id);
		if (existing.isPresent()) {
			CategoryEntity category = existing.get();
			category.setIsDeleted(1);
			List<ViewsEntity> views = this.getViewsById(id);
			for (ViewsEntity viewsEntity : views) {
				viewsEntity.setIsDeleted(1);
			}
			return true;
		}
		return false;
	}

	@Override
	public List<CategoryEntity> findAll() {
		List<CategoryEntity> entity = categoryRepository.findAll();
		return entity;
	}

	@Override
	public Optional<CategoryEntity> findById(int id) {
		return categoryRepository.findById(id);
	}

	// Cái này là update hay upload???
	public boolean findByIdAndUpload(int id, CategoryEntity categoryEntity) {
		Optional<CategoryEntity> categoryEn = this.findById(id);
		if (categoryEn.isEmpty())
			return false;

		System.out.println("get id: " + categoryEn.get().getId());

		if (!categoryEntity.getName().isEmpty()) {
			categoryEn.get().setName(categoryEntity.getName());
		}

		if (!categoryEntity.getDescription().isEmpty()) {
			categoryEn.get().setDescription(categoryEntity.getDescription());
		}

		this.categoryRepository.save(categoryEn.get());

		return true;
	}

	public List<ViewsEntity> getViewsById(int id) {
		Optional<CategoryEntity> category = this.findById(id);
		if (!category.isPresent()) {
			return null;
		}

		CategoryEntity existing = category.get();
		List<ViewsEntity> views = existing.getListViews();
		return views;
	}

	@Override
	public List<CategoryEntity> findAllByIsDeleted(int number) {
		List<CategoryEntity> deletedList = categoryRepository.findAllByIsDeleted(number);
		return deletedList;
	}
}

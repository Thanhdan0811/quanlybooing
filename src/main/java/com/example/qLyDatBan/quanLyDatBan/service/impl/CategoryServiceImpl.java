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
				if (existing.isEmpty()) {
					return null;
				}

				System.out.println("Chạy vaò đây." + category.getName());

				CategoryEntity oldCategory = existing.get();
				if (category.getName() == null || category.getName().isEmpty()) {
					category.setName(oldCategory.getName());
				}
				if (category.getDescription() == null || category.getDescription().isEmpty()) {
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

	@Override
	public boolean delete(CategoryEntity categoryEntity) {
		Optional<CategoryEntity> existed = findById(categoryEntity.getId());
		if (existed.isPresent()) {
			categoryRepository.delete(categoryEntity);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteById(int id) {
		Optional<CategoryEntity> existed = findById(id);
		if (existed.isPresent()) {
			categoryRepository.deleteById(id);
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
}

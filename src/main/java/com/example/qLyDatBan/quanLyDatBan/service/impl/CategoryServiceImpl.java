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

	@Override
	public boolean save(CategoryEntity category, String mode) {
		try {
			if (mode.equals("update")) {
				Optional<CategoryEntity> exist = categoryRepository.findById(category.getId());
				System.out.println(exist.get().getId());
				if (!exist.isPresent()) {
					throw new RuntimeException("Category not found");
				}
				if (category.getName().isEmpty()) {
					category.setName(exist.get().getName());
				}
				if (category.getDescription().isEmpty()) {
					category.setDescription(exist.get().getDescription());
				}

				List<ViewsEntity> views = new ArrayList<ViewsEntity>();
				views = exist.get().getListViews();
				category.setListViews(views);
			}
			this.categoryRepository.save(category);
			return true;
		} catch (Exception err) {
			err.printStackTrace();
			return false;
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

}

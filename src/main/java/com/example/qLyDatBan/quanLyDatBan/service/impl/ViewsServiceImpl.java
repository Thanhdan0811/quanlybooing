package com.example.qLyDatBan.quanLyDatBan.service.impl;

import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.repository.CategoryRepository;
import com.example.qLyDatBan.quanLyDatBan.repository.ViewsRepository;
import com.example.qLyDatBan.quanLyDatBan.service.ViewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViewsServiceImpl implements ViewsService {

	@Autowired
	private ViewsRepository viewsRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public ViewsEntity save(ViewsEntity viewsEntity, String mode) {
		try {
			int id = viewsEntity.getCategory().getId();
			System.out.println(id);
			Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
			viewsEntity.setCategory(null);
			if (categoryEntity.isEmpty()) {
				return null;
			}
			categoryEntity.ifPresent(viewsEntity::setCategory);
			this.viewsRepository.save(viewsEntity);
			return viewsEntity;
		} catch (Exception err) {
			err.printStackTrace();
			return null;
		}
	}

	// delete mềm
	// 0 là đang sử dụng
	// 1 là xóa
	@Override
	public boolean deleteById(int id) {
		Optional<ViewsEntity> existing = findById(id);
		if (existing.isPresent()) {
			ViewsEntity view = existing.get();
			view.setIsDeleted(1);
			return true;
		}
		return false;
	}

	@Override
	public List<ViewsEntity> findAll() {
		List<ViewsEntity> viewsEntities = viewsRepository.findAll();
		if (viewsEntities.isEmpty()) {
			return null;
		}

		return viewsEntities;
	}

	@Override
	public Optional<ViewsEntity> findById(int Id) {
		return this.viewsRepository.findById(Id);
	}

	@Override
	public List<ViewsEntity> findAllByIsDeleted(int number) {
		List<ViewsEntity> views = viewsRepository.findAllByIsDeleted(number);
		return views;

	}
}

package com.example.qLyDatBan.quanLyDatBan.service.impl;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.repository.CategoryRepository;
import com.example.qLyDatBan.quanLyDatBan.repository.ViewsRepository;
import com.example.qLyDatBan.quanLyDatBan.service.BookingService;
import com.example.qLyDatBan.quanLyDatBan.service.ViewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ViewsServiceImpl implements ViewsService {

	@Autowired
	private ViewsRepository viewsRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BookingServiceImpl bookingService;

	@Override
	public ViewsEntity save(ViewsEntity viewsEntity, String mode) {
		try {
			int id = viewsEntity.getCategory().getId();
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

	@Override
	public boolean delete(ViewsEntity viewsEntity) {
		return true;
	}

	@Override
	public boolean deleteById(int id) {
		Optional<ViewsEntity> existed = findById(id);
		if (existed.isPresent()) {
			viewsRepository.deleteById(id);
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
	public List<ViewsEntity> findViewsInDate(LocalDate dateSearch) {
		List<ViewsEntity> listAllView = this.findAll();
		List<ViewsEntity> listBookingDate = this.bookingService.findAllViewByDate(dateSearch);
		List<ViewsEntity> resultView = new ArrayList<>();

		LocalDate dateNow = LocalDate.now();

		if(dateSearch.isBefore(dateNow)) {
			return resultView;
		}

		for(ViewsEntity view: listAllView) {
			boolean hasBook = false;
			for(ViewsEntity viewDate: listBookingDate) {
				if(view.getId() == viewDate.getId()) {
					hasBook = true;
				}
			}
			if(!hasBook) {
				resultView.add(view);
			}
		}

		return resultView;
	}
}

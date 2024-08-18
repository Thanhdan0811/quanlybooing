package com.example.qLyDatBan.quanLyDatBan.service.impl;

import com.example.qLyDatBan.quanLyDatBan.entity.CustomerDetailEntity;
import com.example.qLyDatBan.quanLyDatBan.repository.CustomerDetailRepository;
import com.example.qLyDatBan.quanLyDatBan.service.CustomerDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {
	@Autowired
	private CustomerDetailRepository customerRepo;

	@Override
	public boolean save(CustomerDetailEntity customerDetailEntity, String mode) {
		return false;
	}

	@Override
	public boolean delete(CustomerDetailEntity customerDetailEntity) {
		return true;
	}

	@Override
	public boolean deleteById(int id) {
		Optional<CustomerDetailEntity> existed = findById(id);
		if (existed.isPresent()) {
			customerRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<CustomerDetailEntity> findAll() {
		return List.of();
	}

	@Override
	public Optional<CustomerDetailEntity> findById(int Id) {
		return Optional.empty();
	}
}

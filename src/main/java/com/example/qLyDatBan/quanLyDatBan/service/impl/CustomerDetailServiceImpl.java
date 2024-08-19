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
	private CustomerDetailRepository cusDetailRepository;

	@Override
	public CustomerDetailEntity save(CustomerDetailEntity customerDetailEntity, String mode) {
		return this.cusDetailRepository.save(customerDetailEntity);
	}

	@Override
	public boolean delete(CustomerDetailEntity customerDetailEntity) {
		return true;
	}

	@Override
	public boolean deleteById(int id) {
		Optional<CustomerDetailEntity> existed = findById(id);
		if (existed.isPresent()) {
			cusDetailRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<CustomerDetailEntity> findAll() {
		return cusDetailRepository.findAll();
	}

	@Override
	public Optional<CustomerDetailEntity> findById(int id) {
		Optional<CustomerDetailEntity> existing = cusDetailRepository.findById(id);
		return existing;
	}
}

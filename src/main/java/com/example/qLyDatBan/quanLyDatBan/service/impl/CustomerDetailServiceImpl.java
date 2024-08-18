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
    public CustomerDetailEntity save(CustomerDetailEntity customerDetailEntity) {
        return this.cusDetailRepository.save(customerDetailEntity);
    }

    @Override
    public void delete(CustomerDetailEntity customerDetailEntity) {

    }

    @Override
    public void deleteById(int id) {

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

package com.example.qLyDatBan.quanLyDatBan.service.impl;


import com.example.qLyDatBan.quanLyDatBan.entity.CustomerDetailEntity;
import com.example.qLyDatBan.quanLyDatBan.service.CustomerDetailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerDetailServiceImpl implements CustomerDetailService {
    @Override
    public boolean save(CustomerDetailEntity customerDetailEntity) {
        return false;
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

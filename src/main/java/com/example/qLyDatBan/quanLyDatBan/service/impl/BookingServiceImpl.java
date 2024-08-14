package com.example.qLyDatBan.quanLyDatBan.service.impl;


import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    @Override
    public boolean save(BookingEntity bookingEntity) {
        return false;
    }

    @Override
    public void delete(BookingEntity bookingEntity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<BookingEntity> findAll() {
        return List.of();
    }

    @Override
    public Optional<BookingEntity> findById(int Id) {
        return Optional.empty();
    }
}

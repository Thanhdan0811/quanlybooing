package com.example.qLyDatBan.quanLyDatBan.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    T save(T t);
    void delete(T t);
    void deleteById(int id);
    List<T> findAll();
    Optional<T> findById(int Id);
}

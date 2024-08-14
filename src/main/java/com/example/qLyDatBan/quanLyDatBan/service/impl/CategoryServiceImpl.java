package com.example.qLyDatBan.quanLyDatBan.service.impl;


import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.repository.CategoryRepository;
import com.example.qLyDatBan.quanLyDatBan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public boolean save(CategoryEntity categoryEntity) {
        try {
            this.categoryRepository.save(categoryEntity);
            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }


    @Override
    public void delete(CategoryEntity categoryEntity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<CategoryEntity> findAll() {
        return List.of();
    }

    @Override
    public Optional<CategoryEntity> findById(int Id) {
        return categoryRepository.findById(Id);
    }


}

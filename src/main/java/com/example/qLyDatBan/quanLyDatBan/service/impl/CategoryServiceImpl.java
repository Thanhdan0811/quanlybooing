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
    public CategoryEntity save(CategoryEntity categoryEntity) {
        try {
            System.out.println("cấldfj");
            return this.categoryRepository.save(categoryEntity);
        } catch (Exception err) {
            err.printStackTrace();
            return null;
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
        return this.categoryRepository.findAll();
    }

    public boolean findByIdAndUpload(int id, CategoryEntity categoryEntity) {
        Optional<CategoryEntity> categoryEn = this.findById(id);
        if(categoryEn.isEmpty()) return false;

        System.out.println("get id: " + categoryEn.get().getId());

        if(!categoryEntity.getName().isEmpty()) {
            categoryEn.get().setName(categoryEntity.getName());
        }

        if(!categoryEntity.getDescription().isEmpty()) {
            categoryEn.get().setDescription(categoryEntity.getDescription());
        }

        this.categoryRepository.save(categoryEn.get());

        return true;
    }

    @Override
    public Optional<CategoryEntity> findById(int Id) {
        return this.categoryRepository.findById(Id);
    }


}

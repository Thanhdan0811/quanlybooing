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
    public boolean save(ViewsEntity viewsEntity) {
        try {
            int id = viewsEntity.getCategory().getId();
            Optional<CategoryEntity> categoryEntity = categoryRepository.findById(id);
            viewsEntity.setCategory(null);
            if(categoryEntity.isEmpty()) {
                return false;
            }
            categoryEntity.ifPresent(viewsEntity::setCategory);
            this.viewsRepository.save(viewsEntity);
            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(ViewsEntity viewsEntity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<ViewsEntity> findAll() {
        List<ViewsEntity> viewsEntities = viewsRepository.findAll();
        if(!viewsEntities.isEmpty()) {
            for(ViewsEntity view: viewsEntities) {
                System.out.println(view.getCategory());
            }
        }

        return viewsEntities;
    }

    @Override
    public Optional<ViewsEntity> findById(int Id) {
        return Optional.empty();
    }
}

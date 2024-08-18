package com.example.qLyDatBan.quanLyDatBan.service;

import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;

public interface CategoryService extends BaseService<CategoryEntity>{

    boolean findByIdAndUpload(int id, CategoryEntity categoryEntity);

}

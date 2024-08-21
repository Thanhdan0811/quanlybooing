package com.example.qLyDatBan.quanLyDatBan.service;

import java.util.List;

import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;

public interface ViewsService extends BaseService<ViewsEntity> {
	List<ViewsEntity> findAllByIsDeleted(int number);
}

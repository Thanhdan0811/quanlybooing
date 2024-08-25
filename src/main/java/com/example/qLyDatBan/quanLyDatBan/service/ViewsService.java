package com.example.qLyDatBan.quanLyDatBan.service;

import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;

import java.time.LocalDate;
import java.util.List;

public interface ViewsService extends BaseService<ViewsEntity> {

	List<ViewsEntity> findViewsInDate(LocalDate dateSearch);

	List<ViewsEntity> findAllByIsDeleted(int number);
}

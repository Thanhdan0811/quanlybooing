package com.example.qLyDatBan.quanLyDatBan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;

@Repository
public interface ViewsRepository extends JpaRepository<ViewsEntity, Integer> {
	List<ViewsEntity> findAllByIsDeleted(int number);

}

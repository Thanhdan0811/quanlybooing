package com.example.qLyDatBan.quanLyDatBan.repository;

import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
	List<CategoryEntity> findAllByIsDeleted(int number);
}

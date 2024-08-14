package com.example.qLyDatBan.quanLyDatBan.repository;

import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ViewsRepository  extends JpaRepository<ViewsEntity, Integer> {
}

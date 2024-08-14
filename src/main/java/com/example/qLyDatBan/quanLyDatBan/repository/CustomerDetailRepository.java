package com.example.qLyDatBan.quanLyDatBan.repository;

import com.example.qLyDatBan.quanLyDatBan.entity.CustomerDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerDetailRepository  extends JpaRepository<CustomerDetailEntity, Integer> {
}

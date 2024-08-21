package com.example.qLyDatBan.quanLyDatBan.repository;

import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;

import java.sql.Date;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    @Query(value = "SELECT COUNT(*) " +
            "FROM booking " +
            "WHERE views_id = :viewsId " +
            "AND TRUNC(booking_date) = TRUNC(:bookingDate)",
            nativeQuery = true)
    int CheckViewHasBookAtDate(@Param("viewsId") int view_id,
			@Param("bookingDate") Date bookingDate);
}

package com.example.qLyDatBan.quanLyDatBan.repository;

import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    @Query(value = "SELECT COUNT(*) " +
            "FROM booking " +
            "WHERE views_id = :viewsId " +
            "AND TRUNC(booking_date) = TRUNC(:bookingDate)",
            nativeQuery = true)
    int CheckViewHasBookAtDate(@Param("viewsId") int view_id,
			@Param("bookingDate") Date bookingDate);

    @Query(value = "SELECT * " +
            "FROM booking " +
            "WHERE TRUNC(booking_date) = TRUNC(:dateSearch)",
            nativeQuery = true)
    List<BookingEntity> findAllByDate(@Param("dateSearch") LocalDate dateSearch);
}

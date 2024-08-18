package com.example.qLyDatBan.quanLyDatBan.repository;

import com.example.qLyDatBan.quanLyDatBan.DTO.BookingTimeQuery;
import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {

    @Query("SELECT new com.example.qLyDatBan.quanLyDatBan.DTO.BookingTimeQuery(b.arrived_time, b.expected_time) from BookingEntity b where " +
            "b.views = :viewsId and b.booking_date = :bookingDate")
    List<BookingTimeQuery> findFilterBy(@Param("viewsId") ViewsEntity viewsEntity, @Param("bookingDate") Date bookingDate);

}

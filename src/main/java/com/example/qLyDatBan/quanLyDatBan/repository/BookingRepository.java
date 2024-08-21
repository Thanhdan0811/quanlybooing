package com.example.qLyDatBan.quanLyDatBan.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.qLyDatBan.quanLyDatBan.DTO.BookingTimeQuery;
import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
//	@Query("SELECT new com.example.qLyDatBan.quanLyDatBan.DTO.BookingTimeQuery(b.arrived_time, b.expected_time) from BookingEntity b where "
//			+ "b.views = :viewsId and b.booking_date = :bookingDate")
//	List<BookingTimeQuery> findFilterBy(@Param("viewsId") ViewsEntity viewsEntity,
//			@Param("bookingDate") Date bookingDate);

//	@Query(value = "Select * from booking where booking_date :date and category :to", nativeQuery = true)
//	Iterable<BookingEntity> filterByDate(@Param("categoryId") String category, @Param("date") Date date);

}

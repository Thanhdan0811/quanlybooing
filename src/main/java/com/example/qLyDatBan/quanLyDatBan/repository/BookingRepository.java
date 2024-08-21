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
//	@Query("SELECT new com.example.qLyDatBan.quanLyDatBan.DTO.BookingTimeQuery(b.arrived_time, b.expected_time) from BookingEntity b where "
//			+ "b.views = :viewsId and b.booking_date = :bookingDate")
//	List<BookingTimeQuery> findFilterBy(@Param("viewsId") ViewsEntity viewsEntity,
//			@Param("bookingDate") Date bookingDate);

//	@Query(value = "Select * from booking where booking_date :date and category :to", nativeQuery = true)
//	Iterable<BookingEntity> filterByDate(@Param("categoryId") String category, @Param("date") Date date);

    @Query(value = "SELECT COUNT(*) " +
            "FROM booking " +
            "WHERE views_id = :viewsId " +
            "AND TRUNC(booking_date) = TRUNC(:bookingDate)",
            nativeQuery = true)
    int CheckViewHasBookAtDate(@Param("viewsId") int view_id,
			@Param("bookingDate") Date bookingDate);
}

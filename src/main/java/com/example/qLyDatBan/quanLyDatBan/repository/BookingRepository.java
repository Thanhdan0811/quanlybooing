package com.example.qLyDatBan.quanLyDatBan.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
//	@Query("SELECT new com.example.qLyDatBan.quanLyDatBan.DTO.BookingTimeQuery(b.arrived_time, b.expected_time) from BookingEntity b where "
//			+ "b.views = :viewsId and b.booking_date = :bookingDate")
//	List<BookingTimeQuery> findFilterBy(@Param("viewsId") ViewsEntity viewsEntity,
//			@Param("bookingDate") Date bookingDate);

	@Query(value = "SELECT COUNT(*) " + "FROM booking " + "WHERE views_id = :viewsId "
			+ "AND TRUNC(booking_date) = TRUNC(:bookingDate)", nativeQuery = true)
	int CheckViewHasBookAtDate(@Param("viewsId") int view_id, @Param("bookingDate") Date bookingDate);

//	@Query(value = "select v.* from views v "
//			+ "join booking b on b.views_id = v.id "
//			+ "where booking_date = :date "
//			+ "and v.category_id = :category_id "
//			+ "and b.booking_status != 2 "
//			+ "order by v.id asc", nativeQuery = true)
//	List<ViewsEntity> filterByDateCategory(@Param("category_id") int category_id, @Param("date") Date date);

	@Query(value = "SELECT v.id, v.name, v.desk_img, v.description, v.is_Deleted, v.category_id " + "FROM views v "
			+ "JOIN booking b ON b.views_id = v.id " + "WHERE b.booking_date = :date "
			+ "AND v.category_id = :category_id " + "AND b.booking_status != 2 "
			+ "ORDER BY v.id ASC", nativeQuery = true)
	List<Object[]> filterByDateCategory(@Param("category_id") int categoryId, @Param("date") Date date);

}

package com.example.qLyDatBan.quanLyDatBan.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name = "booking")
@Data
@Entity
public class BookingEntity extends BaseEntity {
	@Column(nullable = false)
	private int booking_status;

	@Column(nullable = false)
	private java.sql.Date booking_date;

	@Column(nullable = true)
	private String addition_note;

	@Column(nullable = false)
	private Date arrived_time;

	@Column(nullable = false)
	private Date expected_time;

	@ManyToOne()
	@JoinColumn(name = "customer_id")
	@JsonManagedReference
	private CustomerDetailEntity customerDetail;

	@ManyToOne()
	@JoinColumn(name = "views_id", nullable = false)
	@JsonManagedReference
	private ViewsEntity views;

}

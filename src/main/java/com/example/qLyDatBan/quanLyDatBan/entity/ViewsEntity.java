package com.example.qLyDatBan.quanLyDatBan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name = "views")
@Data
@Entity
public class ViewsEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private int status;

	@Column(nullable = false)
	private String desk_img;

	@Column(nullable = true)
	private String description;

	@ManyToOne()
	@JoinColumn(name = "category_id", nullable = false)
	@JsonBackReference
	private CategoryEntity category;

	@OneToOne(mappedBy = "views")
	private BookingEntity booking;

}

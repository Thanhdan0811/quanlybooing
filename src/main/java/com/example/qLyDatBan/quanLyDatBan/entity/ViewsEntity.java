package com.example.qLyDatBan.quanLyDatBan.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "views")
@Data
@Entity
public class ViewsEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String desk_img;

	@Column(nullable = true)
	private String description;

	@ManyToOne()
	@JoinColumn(name = "category_id", nullable = false)
	@JsonBackReference
	private CategoryEntity category;

	@OneToMany(mappedBy = "views", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private List<BookingEntity> booking;



}

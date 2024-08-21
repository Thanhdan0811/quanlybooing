package com.example.qLyDatBan.quanLyDatBan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
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

	@Column()
	private int isDeleted = 0;

	@ManyToOne()
	@JoinColumn(name = "category_id", nullable = false)
	@JsonBackReference
	private CategoryEntity category;

	@OneToOne(mappedBy = "view")
	@JsonBackReference
	private BookingEntity booking;

	@PrePersist
	protected void onCreate() {
		if (isDeleted == 0) {
			isDeleted = 0; // This ensures that isDeleted is set to 0 before persisting if not already set
		}
	}
}

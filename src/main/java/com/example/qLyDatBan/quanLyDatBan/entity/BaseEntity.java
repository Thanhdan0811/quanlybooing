package com.example.qLyDatBan.quanLyDatBan.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = true, updatable = false)
	private Date created_at;

	@Column(nullable = true, insertable = false)
	private Date updated_at;

	@PrePersist
	protected void onCreate() {
		created_at = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated_at = new Date();
	}

}

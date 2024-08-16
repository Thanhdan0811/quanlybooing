package com.example.qLyDatBan.quanLyDatBan.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Table(name = "category")
@Data
@Entity
public class CategoryEntity extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Column()
	private String description;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ViewsEntity> listViews;
}

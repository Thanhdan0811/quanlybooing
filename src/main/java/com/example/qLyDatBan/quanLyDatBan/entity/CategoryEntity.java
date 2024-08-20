package com.example.qLyDatBan.quanLyDatBan.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Table(name = "category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CategoryEntity extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Column()
	private String description;

	@Column()
	private String img_path;

	@Column()
	private int isDeleted;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ViewsEntity> listViews;
}

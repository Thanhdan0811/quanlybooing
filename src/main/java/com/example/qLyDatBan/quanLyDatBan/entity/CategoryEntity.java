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
	private int isDeleted = 0;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<ViewsEntity> listViews;

	@PrePersist
	protected void onCreate() {
		if (isDeleted == 0) {
			isDeleted = 0; // This ensures that isDeleted is set to 0 before persisting if not already set
		}
	}
}

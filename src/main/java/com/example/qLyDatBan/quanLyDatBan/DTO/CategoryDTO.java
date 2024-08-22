package com.example.qLyDatBan.quanLyDatBan.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
	String name;
	String description;
	String img_path;
	int isDeleted;
}

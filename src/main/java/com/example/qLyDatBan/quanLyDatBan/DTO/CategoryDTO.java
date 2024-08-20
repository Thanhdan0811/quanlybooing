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
public class CategoryDTO extends BaseDTO {
	String name;
	String description;
//	List<ViewDTO> listViews;
}

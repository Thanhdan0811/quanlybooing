package com.example.qLyDatBan.quanLyDatBan.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO extends BaseDTO {
	String name;
	String description;
}

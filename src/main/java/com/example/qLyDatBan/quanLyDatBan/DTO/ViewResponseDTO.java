package com.example.qLyDatBan.quanLyDatBan.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewResponseDTO extends BaseDTO{

	String name;
	String desk_img;
	String description;
	CategoryDTO category;
}

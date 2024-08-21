package com.example.qLyDatBan.quanLyDatBan.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDetailDTO extends BaseDTO{
    String name;
    String description;
	List<ViewDTO> listViews;
}

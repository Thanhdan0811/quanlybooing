package com.example.qLyDatBan.quanLyDatBan.controller;

import com.example.qLyDatBan.quanLyDatBan.DTO.CategoryDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ErrorResponse;
import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.mapper.Mapper;
import com.example.qLyDatBan.quanLyDatBan.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private Mapper mapper;

	@GetMapping("/all")
	public List<CategoryDTO> getAllCategories() {
		List<CategoryEntity> entity = categoryService.findAll();
		List<CategoryDTO> categories = new ArrayList<>();
		for (CategoryEntity categoryEntity : entity) {
			categories.add(mapper.map(categoryEntity, CategoryDTO.class));
		}
		return categories;
	}

	@PostMapping("/add-category")
	public String addCategory(@RequestBody CategoryEntity categoryInfo) {
		String mode = "add";
		System.out.println("add category");
		System.out.println(categoryInfo.getName());
		System.out.println(categoryInfo.getDescription());

		if (categoryInfo.getName().isEmpty() || categoryInfo.getDescription().isEmpty()) {
			return "Thông tin Category chưa hợp lệ.";
		}

		this.categoryService.save(categoryInfo, mode);
		return "Category đã được thêm thành công.";
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ErrorResponse> delete(@PathVariable int id) {
		if (id == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Id must be provided"));
		} else if (categoryService.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ErrorResponse(HttpStatus.OK.value(), "Delete successful!"));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "This category is not found"));
	}

	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody CategoryDTO categoryDTO) {
		String mode = "update";
		CategoryEntity categoryEntity = mapper.map(categoryDTO, CategoryEntity.class);
		if (categoryService.save(categoryEntity, mode)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ErrorResponse(HttpStatus.OK.value(), "Update successful!"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Update unsuccessful!"));
	}
}

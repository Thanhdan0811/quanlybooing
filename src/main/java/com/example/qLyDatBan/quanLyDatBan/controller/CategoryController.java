package com.example.qLyDatBan.quanLyDatBan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.qLyDatBan.quanLyDatBan.DTO.CategoryResponseDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.qLyDatBan.quanLyDatBan.DTO.CategoryDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.CategoryResponseDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.Response;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewResponseDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.mapper.Mapper;
import com.example.qLyDatBan.quanLyDatBan.service.CategoryService;
import com.example.qLyDatBan.quanLyDatBan.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private Mapper mapper;

	@Autowired
	private CategoryServiceImpl serviceImpl;

	@GetMapping("/all")
	public List<CategoryResponseDTO> getAllCategories() {
		List<CategoryEntity> entity = categoryService.findAll();
		List<CategoryResponseDTO> categories = new ArrayList<>();
		for (CategoryEntity categoryEntity : entity) {
			categories.add(mapper.map(categoryEntity, CategoryResponseDTO.class));
		}
		return categories;
	}

	// Lấy ra 1 category cụ thể dựa vào id
	@GetMapping("/detail-{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable int id) {
		Optional<CategoryEntity> cateEntity = this.categoryService.findById(id);
		if (cateEntity.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Response<>(HttpStatus.NOT_FOUND.value(), "Không tìm thấy Category"));
		}
		CategoryResponseDTO categoryDTO = mapper.map(cateEntity, CategoryResponseDTO.class);

		return ResponseEntity.ok(categoryDTO);
	}

	// Trả ra cả object nếu add thành công
	@PostMapping("/add-category")
	public ResponseEntity<?> addCategory(@RequestBody CategoryDTO categoryDTO) {
		System.out.println("add category");
		System.out.println(categoryDTO.getName());
		System.out.println(categoryDTO.getDescription());

		if (categoryDTO.getName().isEmpty() || categoryDTO.getDescription().isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response<>(HttpStatus.BAD_REQUEST.value(), "Thông tin Category chưa hợp lệ."));
		}
		CategoryEntity categoryEntity = mapper.map(categoryDTO, CategoryEntity.class);
		CategoryEntity addedCategory = this.categoryService.save(categoryEntity, "add");
		CategoryDTO responseDTO = mapper.map(addedCategory, CategoryDTO.class);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response<>(HttpStatus.CREATED.value(), "Category đã được thêm thành công.", responseDTO));

	}

	// Lấy các views từ 1 category cụ thể
	@GetMapping("/getViews/{id}")
	public ResponseEntity<?> getViewsByCategory(@PathVariable int id) {
		List<ViewsEntity> viewsEntity = serviceImpl.getViewsById(id);
		if (viewsEntity == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Response<>(HttpStatus.NOT_FOUND.value(), "Không tìm thấy category này."));
		}
		List<ViewResponseDTO> views = new ArrayList<>();
		for (ViewsEntity view : viewsEntity) {
			views.add(mapper.map(view, ViewResponseDTO.class));
		}
		return ResponseEntity.status(HttpStatus.OK).body(new Response<>(HttpStatus.OK.value(), "", views));
	}

	// delete mềm
	@PatchMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		if (id == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response<>(HttpStatus.BAD_REQUEST.value(), "Không tìm thấy ID"));
		} else if (categoryService.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response<>(HttpStatus.OK.value(), "Đã xóa thành công!"));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new Response<>(HttpStatus.NOT_FOUND.value(), "Không tìm thấy category này."));
	}

	// Trả ra Object nếu update thành công
	@PutMapping("/detail-{id}")
	public ResponseEntity<?> update(@PathVariable int id, @RequestBody CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = mapper.map(categoryDTO, CategoryEntity.class);
		categoryEntity.setId(id);
		CategoryEntity updatedCategory = this.categoryService.save(categoryEntity, "update");
		CategoryDTO updatedDTO = mapper.map(categoryEntity, CategoryDTO.class);
		if (updatedCategory != null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response<>(HttpStatus.OK.value(), "Category cập nhật thành công!", updatedDTO));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new Response<>(HttpStatus.BAD_REQUEST.value(), "Category cập nhật thất bại!"));
	}
}

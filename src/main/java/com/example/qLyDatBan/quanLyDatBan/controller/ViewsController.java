package com.example.qLyDatBan.quanLyDatBan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.qLyDatBan.quanLyDatBan.DTO.Response;
//github.com/Thanhdan0811/quanlybooing.git
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewResponseDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.mapper.Mapper;
import com.example.qLyDatBan.quanLyDatBan.service.CategoryService;
import com.example.qLyDatBan.quanLyDatBan.service.ViewsService;

@RestController
@RequestMapping("/views")
@CrossOrigin
public class ViewsController {

	@Autowired
	private ViewsService viewsService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private Mapper mapper;

	@GetMapping("/all")
	public List<ViewResponseDTO> getAllViews() {
		// maper DTO
		List<ViewsEntity> viewEs = this.viewsService.findAllByIsDeleted(0);
		List<ViewResponseDTO> viewDs = new ArrayList<>();
		if (viewEs == null)
			return null;
		System.out.println("viewEs : " + viewEs.size());
		for (ViewsEntity view : viewEs) {
			viewDs.add(mapper.map(view, ViewResponseDTO.class));
		}
		return viewDs;
	}

	@PostMapping("/add-views")
	public ResponseEntity<?> addViews(@RequestBody ViewDTO view) {

		System.out.println("View Body: " + view);
//		ViewsEntity viewEntity = mapper.map(view, ViewsEntity.class);
//		CategoryEntity category = categoryService.findById(view.getCategory_id())
//				.orElseThrow(() -> new RuntimeException("Category not found with ID: " + view.getCategory_id()));
//
//		viewEntity.setCategory(category);

		ViewsEntity viewEntity = mapper.mapViewCreate(view, ViewsEntity.class);
		ViewsEntity isCreated = this.viewsService.save(viewEntity, "add");
		if (isCreated == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response<>(HttpStatus.BAD_REQUEST.value(), "Thông tin lỗi không thể tạo views"));
		}
		ViewResponseDTO newView = mapper.map(isCreated, ViewResponseDTO.class);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(HttpStatus.OK.value(), "Views đã được thêm thành công.", newView));
	}

	// delete mềm
	@PatchMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		if (id == 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response<>(HttpStatus.BAD_REQUEST.value(), "Không tìm thấy ID"));
		} else if (viewsService.deleteById(id)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response<>(HttpStatus.OK.value(), "Đã xóa thành công!"));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new Response<>(HttpStatus.NOT_FOUND.value(), "Không tìm thấy view này"));
	}

}

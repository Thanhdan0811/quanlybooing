package com.example.qLyDatBan.quanLyDatBan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.qLyDatBan.quanLyDatBan.DTO.DateSearchViewDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.Response;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewResponseDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.mapper.Mapper;
import com.example.qLyDatBan.quanLyDatBan.service.CategoryService;
import com.example.qLyDatBan.quanLyDatBan.service.ViewsService;
import com.example.qLyDatBan.quanLyDatBan.utils.DateUtil;

@RestController
@RequestMapping("/views")
@CrossOrigin(origins = "*")
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

	@PostMapping("/views-available")
	public ResponseEntity<?> getAvailableViews(@RequestBody DateSearchViewDTO dateSearch) {
		List<ViewsEntity> viewEs = this.viewsService
				.findViewsInDate(DateUtil.convertISOStrToDateSql(dateSearch.getDate_search()).toLocalDate());
		List<ViewResponseDTO> viewDs = new ArrayList<>();
		if (viewEs == null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response<>(HttpStatus.OK.value(), "Danh sách bàn còn trống.", viewDs));
		}
		;
		for (ViewsEntity view : viewEs) {
			viewDs.add(mapper.map(view, ViewResponseDTO.class));
		}
		List<ViewResponseDTO> listViewCategoryId = new ArrayList<>();
		int categoryIdSearch = dateSearch.getCategory_id();
		for (ViewResponseDTO v : viewDs) {
			if (v.getCategory().getId() == categoryIdSearch) {
				listViewCategoryId.add(v);
			}

		}

		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(HttpStatus.OK.value(), "Danh sách bàn trống.", listViewCategoryId));
	}

	@PostMapping("/add-views")
	public ResponseEntity<?> addViews(@RequestBody ViewDTO view) {
		System.out.println("View Body: " + view);
		ViewsEntity viewEntity = mapper.mapViewCreate(view, ViewsEntity.class);
		ViewsEntity isCreated = this.viewsService.save(viewEntity, "add");
		if (isCreated == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Response<>(HttpStatus.BAD_REQUEST.value(), "Thông tin lỗi không thể tạo views"));
		}
		ViewDTO newView = mapper.map(isCreated, ViewDTO.class);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(HttpStatus.OK.value(), "Views đã được thêm thành công.", newView));
	}

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

	@PutMapping("/updates")
	public ResponseEntity<?> updateView(@RequestBody int id) {
		return null;
	}

}

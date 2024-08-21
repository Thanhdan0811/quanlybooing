package com.example.qLyDatBan.quanLyDatBan.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.qLyDatBan.quanLyDatBan.DTO.DateSearchViewDTO;
import com.example.qLyDatBan.quanLyDatBan.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.qLyDatBan.quanLyDatBan.DTO.Response;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewResponseDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.mapper.Mapper;
import com.example.qLyDatBan.quanLyDatBan.service.ViewsService;

@RestController
@RequestMapping("/views")
@CrossOrigin
public class ViewsController {

	@Autowired
	private ViewsService viewsService;

	@Autowired
	private Mapper mapper;

	@GetMapping("/all")
	public ResponseEntity<?> getAllViews() {
		// maper DTO
		List<ViewsEntity> viewEs = this.viewsService.findAll();
		List<ViewResponseDTO> viewDs = new ArrayList<>();
		if(viewEs == null) return null;
		System.out.println("viewEs : " + viewEs.size());
		for (ViewsEntity view : viewEs) {
			viewDs.add(mapper.map(view, ViewResponseDTO.class));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(HttpStatus.OK.value(), "Danh sách views.", viewDs));
	}

	@PostMapping("/views-available")
	public ResponseEntity<?> getAvailableViews(@RequestBody DateSearchViewDTO dateSearch) {
		List<ViewsEntity> viewEs = this.viewsService
				.findViewsInDate(DateUtil.convertISOStrToDateSql(dateSearch.getDate_search()).toLocalDate());
		List<ViewResponseDTO> viewDs = new ArrayList<>();
		if(viewEs == null) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new Response<>(HttpStatus.OK.value(), "không có danh sách views trống.", viewDs));
		};
		for (ViewsEntity view : viewEs) {
			viewDs.add(mapper.map(view, ViewResponseDTO.class));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response<>(HttpStatus.OK.value(), "Danh sách views trống.", viewDs));
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

}

package com.example.qLyDatBan.quanLyDatBan.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewCreateRequestDTO;
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
	public List<ViewDTO> getAllViews() {
		// maper DTO
		List<ViewsEntity> viewEs = this.viewsService.findAll();
		List<ViewDTO> viewDs = new ArrayList<>();
		for (ViewsEntity view : viewEs) {
			viewDs.add(mapper.map(view, ViewDTO.class));
		}
		return viewDs;
	}

	@PostMapping("/add-views")
	public ResponseEntity<?> addViews(@RequestBody ViewCreateRequestDTO view) {
		System.out.println("View Body: " + view);
		ViewsEntity viewEntity = mapper.map(view, ViewsEntity.class);
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

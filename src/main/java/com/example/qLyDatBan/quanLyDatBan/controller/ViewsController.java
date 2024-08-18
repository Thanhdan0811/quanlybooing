package com.example.qLyDatBan.quanLyDatBan.controller;

import com.example.qLyDatBan.quanLyDatBan.DTO.ViewCreateRequestDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.mapper.ViewsMapper;
import com.example.qLyDatBan.quanLyDatBan.service.ViewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/views")
@CrossOrigin
public class ViewsController {

	@Autowired
	private ViewsService viewsService;

	@GetMapping("/all")
	public List<ViewsEntity> getAllViews() {
		return this.viewsService.findAll();
	}

	@PostMapping("/add-views")
	public String addViews(@RequestBody ViewCreateRequestDTO viewsBody) {
		String mode = "add";
		System.out.println("View Body: " + viewsBody);
		boolean isCreated = this.viewsService.save(ViewsMapper.ViewsReqToViewEntity(viewsBody), mode);
		if (!isCreated) {
			return "thông tin lỗi không thể tạo views";
		}
		return "Views đã được thêm thành công.";
	}

}

package com.example.qLyDatBan.quanLyDatBan.controller;


import com.example.qLyDatBan.quanLyDatBan.DTO.ViewCreateRequestDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewGetAllDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.mapper.Mapper;
import com.example.qLyDatBan.quanLyDatBan.mapper.ViewsMapper;
import com.example.qLyDatBan.quanLyDatBan.service.ViewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/views")
@CrossOrigin
public class ViewsController {

    @Autowired
    private ViewsService viewsService;

    @Autowired
    private Mapper mapper;

    @GetMapping("/all")
    public List<ViewGetAllDTO> getAllViews() {
        // maper DTO
        List<ViewsEntity> viewEs = this.viewsService.findAll();
        List<ViewGetAllDTO> viewDs = new ArrayList<>();
        for(ViewsEntity view: viewEs) {
            viewDs.add(mapper.map(view, ViewGetAllDTO.class));
        }
        return viewDs;
    }

    @PostMapping("/add-views")
    public String addViews(@RequestBody ViewCreateRequestDTO viewsBody) {
        ViewsEntity newViewEntity = this.viewsService.save(ViewsMapper.ViewsReqToViewEntity(viewsBody));
        if(newViewEntity == null) {
            return "thông tin lỗi không thể tạo views";
        }
        return "Views đã được thêm thành công.";
    }

}

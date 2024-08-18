package com.example.qLyDatBan.quanLyDatBan.controller;


import com.example.qLyDatBan.quanLyDatBan.DTO.CategoryAddDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.CategoryGetDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.CategoryGetDetailDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ResponseBaseMessageDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.mapper.Mapper;
import com.example.qLyDatBan.quanLyDatBan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private Mapper mapper;


    @GetMapping("/all")
    public List<CategoryGetDTO> getAllCategories() {
        List<CategoryEntity> caEntity = this.categoryService.findAll();
        List<CategoryGetDTO> caDTO = new ArrayList<>();
        for(CategoryEntity item: caEntity) {
            caDTO.add(mapper.map(item, CategoryGetDTO.class));
        }
        return caDTO;
    }

    @GetMapping("/detail-{id}")
    public Object getCategoryDetail(@PathVariable int id) {
        Optional<CategoryEntity> cateEntity = this.categoryService.findById(id);
        if(cateEntity.isEmpty()) {
            return new ResponseEntity<ResponseBaseMessageDTO>(ResponseBaseMessageDTO.builder()
                    .status(false).error("Category not found").build(), HttpStatus.BAD_REQUEST);
        }
        CategoryGetDetailDTO categoryGetDetailDTO = mapper.map(cateEntity, CategoryGetDetailDTO.class);
        return new ResponseEntity<CategoryGetDetailDTO>(categoryGetDetailDTO, HttpStatus.OK);
    }

    @PutMapping("/detail-{id}")
    public Object updateCategoryDetail(@PathVariable int id, @RequestBody CategoryAddDTO categoryInfo) {
        CategoryEntity caEntity = mapper.map(categoryInfo, CategoryEntity.class);
        boolean updated = this.categoryService.findByIdAndUpload(id, caEntity);

        if(updated) {
            return new ResponseEntity<ResponseBaseMessageDTO>(ResponseBaseMessageDTO.builder()
                    .status(true).error("Category updated!").build(), HttpStatus.OK);
        }

        return new ResponseEntity<ResponseBaseMessageDTO>(ResponseBaseMessageDTO.builder()
                .status(false).error("Category updated failed").build(), HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/add-category")
    public ResponseEntity<ResponseBaseMessageDTO> addCategory(@RequestBody CategoryAddDTO categoryInfo) {
        if(categoryInfo.getName().isEmpty() || categoryInfo.getDescription().isEmpty()) {
            return new ResponseEntity<ResponseBaseMessageDTO>(ResponseBaseMessageDTO.builder()
                    .status(false)
                    .error("Thông tin Category chưa hợp lệ.")
                    .build(), HttpStatus.BAD_REQUEST);
        }
        CategoryEntity caEntity = mapper.map(categoryInfo, CategoryEntity.class);
        this.categoryService.save(caEntity);
        return new ResponseEntity<ResponseBaseMessageDTO>(ResponseBaseMessageDTO.builder()
                .status(true)
                .message("Tạo category thành công.")
                .build(), HttpStatus.OK);
    }

}

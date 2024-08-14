package com.example.qLyDatBan.quanLyDatBan.controller;


import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public CategoryEntity[] getAllCategories() {

        System.out.println("Get all categories");
        return null;
    }

    @PostMapping("/add-category")
    public String addCategory(@RequestBody CategoryEntity categoryInfo) {
        System.out.println("add category");
        System.out.println(categoryInfo.getName());
        System.out.println(categoryInfo.getDescription());

        if(categoryInfo.getName().isEmpty() || categoryInfo.getDescription().isEmpty()) {
            return "Thông tin Category chưa hợp lệ.";
        }

        this.categoryService.save(categoryInfo);
        return "Category đã được thêm thành công.";
    }

}

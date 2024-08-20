package com.example.qLyDatBan.quanLyDatBan.mapper;


import com.example.qLyDatBan.quanLyDatBan.DTO.ViewDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewResponseDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import lombok.Builder;

@Builder
public class ViewsMapper {

    public static ViewsEntity ViewsReqToViewEntity(ViewDTO viewBody) {
        ViewsEntity viewsEntity = new ViewsEntity();
        CategoryEntity categoryEntity = new CategoryEntity();

        viewsEntity.setName(viewBody.getName());
//        viewsEntity.setStatus(viewBody.getStatus());
        viewsEntity.setDesk_img(viewBody.getDesk_img());
        viewsEntity.setDescription(viewBody.getDescription());
        categoryEntity.setId(viewBody.getCategory_id());
        viewsEntity.setCategory(categoryEntity);

        return viewsEntity;
    }

}

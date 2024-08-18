package com.example.qLyDatBan.quanLyDatBan.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewGetAllDTO {
    int id;
    String desk_img;
    String name;
    String description;
    CategoryGetDTO category;
}

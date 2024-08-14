package com.example.qLyDatBan.quanLyDatBan.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ViewCreateRequestDTO {

    String name;
    int status;
    String desk_img;
    String description;
    int category_id;
}

package com.example.qLyDatBan.quanLyDatBan.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    String name;
    String email;
    String phone;
    int id;
}

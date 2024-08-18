package com.example.qLyDatBan.quanLyDatBan.DTO;

import java.sql.Date;

import lombok.Data;

@Data
public abstract class BaseDTO {
	int id;
	Date created_at;
	Date updated_at;
}

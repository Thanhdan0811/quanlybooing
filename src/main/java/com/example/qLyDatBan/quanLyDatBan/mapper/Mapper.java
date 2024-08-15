package com.example.qLyDatBan.quanLyDatBan.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Mapper<T> {
	@Autowired
	private ModelMapper modelMapper;

	public Object convertObject(T t) {
		Object object = modelMapper.map(t, Object.class);
		return object;
	}
}

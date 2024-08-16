package com.example.qLyDatBan.quanLyDatBan.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper<T> {
	@Autowired
	private ModelMapper modelMapper;

	public <O> O mapper(T sourceObject, Class<O> destinationType) {
		return modelMapper.map(sourceObject, destinationType);
	}
}

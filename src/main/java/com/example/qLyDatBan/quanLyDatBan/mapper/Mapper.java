package com.example.qLyDatBan.quanLyDatBan.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    @Autowired
    private ModelMapper modelMapper;

    public <S, D> D map(S sourceObject, Class<D> destinationType) {
        return modelMapper.map(sourceObject, destinationType);
    }
}

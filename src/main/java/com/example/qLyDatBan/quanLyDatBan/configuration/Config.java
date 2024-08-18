package com.example.qLyDatBan.quanLyDatBan.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Config{

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
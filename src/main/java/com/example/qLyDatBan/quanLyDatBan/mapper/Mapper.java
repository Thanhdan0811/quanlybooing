package com.example.qLyDatBan.quanLyDatBan.mapper;

import com.example.qLyDatBan.quanLyDatBan.DTO.BookingDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.BookingResponseDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewResponseDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.CategoryEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.CustomerDetailEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
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

	public BookingEntity mapBookingCreate(BookingDTO sourceObject, Class<BookingEntity> destinationType) {
		BookingEntity bookingEntity = modelMapper.map(sourceObject, destinationType);
		ViewsEntity viewsEntity = new ViewsEntity();
		CustomerDetailEntity customerDetail = new CustomerDetailEntity();
		viewsEntity.setId(sourceObject.getView_id());
		customerDetail.setName(sourceObject.getName());
		customerDetail.setEmail(sourceObject.getEmail());
		customerDetail.setPhone(sourceObject.getPhone());

		bookingEntity.setCustomerDetail(customerDetail);
		bookingEntity.setViews(viewsEntity);


		return bookingEntity;
	}

	public ViewsEntity mapViewCreate(ViewDTO sourceObject, Class<ViewsEntity> destinationType) {
		ViewsEntity viewsEntity = modelMapper.map(sourceObject, destinationType);
		CategoryEntity categoryEntity = new CategoryEntity();

		viewsEntity.setName(sourceObject.getName());
		viewsEntity.setDesk_img(sourceObject.getDesk_img());
		viewsEntity.setDescription(sourceObject.getDescription());
		categoryEntity.setId(sourceObject.getCategory_id());
		viewsEntity.setCategory(categoryEntity);

		return viewsEntity;
	}

	public BookingResponseDTO mapBookingResponseGetAll(BookingEntity sourceObj, Class<BookingResponseDTO> desType) {
		BookingResponseDTO booking = modelMapper.map(sourceObj, desType);
		booking.setName(sourceObj.getCustomerDetail().getName());
		booking.setPhone(sourceObj.getCustomerDetail().getPhone());
		booking.setEmail(sourceObj.getCustomerDetail().getEmail());

//		booking

		return booking;
	}
}

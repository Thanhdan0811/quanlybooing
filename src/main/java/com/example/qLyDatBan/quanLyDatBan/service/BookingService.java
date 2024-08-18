package com.example.qLyDatBan.quanLyDatBan.service;

import com.example.qLyDatBan.quanLyDatBan.DTO.BookingStatusDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;

public interface BookingService extends BaseService<BookingEntity>{

    boolean changeStatus(BookingStatusDTO bookingStatus);

}

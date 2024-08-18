package com.example.qLyDatBan.quanLyDatBan.controller;


import com.example.qLyDatBan.quanLyDatBan.DTO.BookingAddRequestDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.BookingGetDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.BookingStatusDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.mapper.BookingMapper;
import com.example.qLyDatBan.quanLyDatBan.mapper.Mapper;
import com.example.qLyDatBan.quanLyDatBan.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @Autowired
    private Mapper mapper;

    @GetMapping("/all")
    public List<BookingGetDTO> getAllBooking() {
        List<BookingEntity> bookings = this.bookingService.findAll();
        List<BookingGetDTO> bookingsDTO = new ArrayList<>();

        for(BookingEntity book : bookings) {
            bookingsDTO.add(mapper.map(book, BookingGetDTO.class));
        }
        return bookingsDTO;
    }

    @PostMapping("/add-booking")
    public String addBooking(@RequestBody BookingAddRequestDTO bookingBody) {
        BookingEntity saveBooking = this.bookingService.save(BookingMapper.BookingReqToBooingEntity(bookingBody));
        if(saveBooking == null) {
            return "Booing khong thanh cong";
        }
        return "Booking da duoc tao";
    }

    @PostMapping("/update-status")
    public boolean updateBookingStatus(@RequestBody BookingStatusDTO bookingStatus) {

        boolean isUpdated = this.bookingService.changeStatus(bookingStatus);

        return isUpdated;
    }

}

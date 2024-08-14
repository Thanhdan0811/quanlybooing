package com.example.qLyDatBan.quanLyDatBan.controller;


import com.example.qLyDatBan.quanLyDatBan.DTO.BookingAddRequestDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin
public class BookingController {

    @PostMapping("/add-booking")
    public void addBooking(@RequestBody BookingAddRequestDTO bookingBody) {
        System.out.println("booking add requies t; " + bookingBody);
    }

}

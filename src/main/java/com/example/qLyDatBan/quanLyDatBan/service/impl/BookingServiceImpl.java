package com.example.qLyDatBan.quanLyDatBan.service.impl;


import com.example.qLyDatBan.quanLyDatBan.DTO.BookingGetDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.BookingStatusDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.BookingTimeQuery;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewsStatusDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.CustomerDetailEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.mapper.Mapper;
import com.example.qLyDatBan.quanLyDatBan.repository.BookingRepository;
import com.example.qLyDatBan.quanLyDatBan.repository.ViewsRepository;
import com.example.qLyDatBan.quanLyDatBan.service.BookingService;
import com.example.qLyDatBan.quanLyDatBan.service.CustomerDetailService;
import com.example.qLyDatBan.quanLyDatBan.service.ViewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookingServiceImpl implements BookingService {

    @Autowired
    private ViewsService viewsService;

    @Autowired
    private BookingRepository  bookingRepository;

    @Autowired
    private CustomerDetailService cusDetailService;


    @Override
    public BookingEntity save(BookingEntity bookingEntity) {
        // check view existed
        Optional<ViewsEntity> viewEntity = this.viewsService.findById(bookingEntity.getViews().getId());
        if(viewEntity.isEmpty()) return null;
        bookingEntity.setViews(viewEntity.get());

        // Check arrived time and expected time is available.

        System.out.println("viewEntity.get() " + viewEntity + "  " + bookingEntity.getBooking_date());


        List<BookingTimeQuery> listBook = this.bookingRepository.findFilterBy(viewEntity.get(), bookingEntity.getBooking_date());

        System.out.println("listBook" + listBook);

        if(bookingEntity.getArrived_time().getTime() >= bookingEntity.getExpected_time().getTime()) {
            System.out.println("thời gian arrived không được sau thời gian expected.");
            return null;
        }

        for(BookingTimeQuery bookingTime: listBook) {
            long arrivedTime = bookingTime.getArrived_time().getTime();
            long expectedTime = bookingTime.getExpected_time().getTime();
            long newArrivedTime = bookingEntity.getArrived_time().getTime();
            long newExpectedTime = bookingEntity.getExpected_time().getTime();
            System.out.println("chạy vào đây");

            System.out.println("check 1" + "  " +  newArrivedTime + "  " + arrivedTime);
            System.out.println("check 2" + "  " +  newArrivedTime + "  " + arrivedTime);
            System.out.println("check 3" + "  " +  newArrivedTime + "  " + arrivedTime);
            System.out.println("check 4" + "  " +  newArrivedTime + "  " + arrivedTime);

            if((newArrivedTime >= arrivedTime && newArrivedTime <= expectedTime) ||
                    (newExpectedTime >= arrivedTime && newExpectedTime <= expectedTime)) {
                System.out.println("thời gian booking đã được đặt trước đó.");
                return null;
            }

        }

//        return null;

        // create customer entity
        CustomerDetailEntity customerEntity = this.cusDetailService.save(bookingEntity.getCustomerDetail());
        if(customerEntity == null) return null;

        bookingEntity.setCustomerDetail(customerEntity);

        return this.bookingRepository.save(bookingEntity);
    }

    @Override
    public void delete(BookingEntity bookingEntity) {

    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<BookingEntity> findAll() {
        return this.bookingRepository.findAll();
    }

    @Override
    public Optional<BookingEntity> findById(int Id) {
        return Optional.empty();
    }

    @Override
    public boolean changeStatus(BookingStatusDTO bookingStatus) {

        List<Integer> statusList = new ArrayList<>();
        statusList.add(0); // từ chối
        statusList.add(1); // chấp nhận
        statusList.add(2); // chờ xử lý
        statusList.add(3); // hoàn thành
        statusList.add(4); // hủy

        Optional<BookingEntity> bookingEntity = this.bookingRepository.findById(bookingStatus.getBooking_id());
        if(bookingEntity.isEmpty()) {
            System.out.println("không tìm tháy booking");
            return false;
        }

        if(!statusList.contains(bookingStatus.getStatus())) {
            System.out.println("không có status");
            return false;
        }

        int viewStatus = bookingStatus.getStatus() == 1 ? 1 : 0;

        // update status view
        ViewsStatusDTO viewsStatusDTO = ViewsStatusDTO.builder().build();
        viewsStatusDTO.setView_id(bookingEntity.get().getViews().getId());
//        viewsStatusDTO.setStatus(viewStatus);
        System.out.println("trước khi update view");
        ViewsEntity updatedViewEntity = this.viewsService.changeStatus(viewsStatusDTO);

        if(updatedViewEntity == null) return false;

        bookingEntity.get().setBooking_status(bookingStatus.getStatus());
        bookingEntity.get().setViews(updatedViewEntity);

        this.bookingRepository.save(bookingEntity.get());

        return true;
    }


}

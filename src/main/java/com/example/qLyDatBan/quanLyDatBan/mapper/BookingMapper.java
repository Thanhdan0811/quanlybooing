package com.example.qLyDatBan.quanLyDatBan.mapper;

import com.example.qLyDatBan.quanLyDatBan.DTO.BookingAddRequestDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.BookingEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.CustomerDetailEntity;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;
import com.example.qLyDatBan.quanLyDatBan.utils.DateUtil;

import java.util.Date;

public class BookingMapper {

    public static BookingEntity BookingReqToBooingEntity(BookingAddRequestDTO bodyBooking) {
        BookingEntity booking = new BookingEntity();
        CustomerDetailEntity customerDetail = new CustomerDetailEntity();
        ViewsEntity viewsEntity = new ViewsEntity();

        customerDetail.setName(bodyBooking.getName());
        customerDetail.setEmail(bodyBooking.getEmail());
        customerDetail.setPhone(bodyBooking.getPhone());

        viewsEntity.setId(bodyBooking.getView_id());

        java.sql.Date bookingDate = DateUtil.convertISOStrToDateSql(bodyBooking.getBooking_date());
        Date arrivedTime = DateUtil.convertISOStrToDateUtil(bodyBooking.getArrived_time());
        Date expectedTime = DateUtil.convertISOStrToDateUtil(bodyBooking.getExpected_time());

        booking.setBooking_status(bodyBooking.getBooking_status());
        booking.setAddition_note(bodyBooking.getAddition_note());
        booking.setArrived_time(arrivedTime);
        booking.setExpected_time(expectedTime);
        booking.setBooking_date(bookingDate);
        booking.setCustomerDetail(customerDetail);
        booking.setViews(viewsEntity);

        return  booking;

    }

}

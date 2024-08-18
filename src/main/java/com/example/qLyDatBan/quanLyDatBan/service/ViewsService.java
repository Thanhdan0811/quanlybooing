package com.example.qLyDatBan.quanLyDatBan.service;

import com.example.qLyDatBan.quanLyDatBan.DTO.BookingStatusDTO;
import com.example.qLyDatBan.quanLyDatBan.DTO.ViewsStatusDTO;
import com.example.qLyDatBan.quanLyDatBan.entity.ViewsEntity;

public interface ViewsService extends BaseService<ViewsEntity>{

    ViewsEntity changeStatus(ViewsStatusDTO viewStatus);

}

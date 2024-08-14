package com.example.qLyDatBan.quanLyDatBan.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
@Entity
public abstract class DateEntity {
    @Column(nullable = true, updatable = false)
    private Date createTime;

    @Column(nullable = true, insertable = false)
    private Date updateTime;
}

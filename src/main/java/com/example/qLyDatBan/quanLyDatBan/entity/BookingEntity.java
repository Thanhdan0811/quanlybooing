package com.example.qLyDatBan.quanLyDatBan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Table(name = "booking")
@Data
@Entity
public class BookingEntity extends BaseEntity {
    @Column(nullable = false)
    private int booking_status;

    @Column(nullable = false)
    private java.sql.Date booking_date;

    @Column(nullable = true)
    private String addition_note;

    @Column(nullable = false)
    private Date arrived_time;

    @Column(nullable = false)
    private Date expected_time;

    @Column(nullable = true, updatable = false)
    private Date createTime;

    @Column(nullable = true, insertable = false)
    private Date updateTime;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private CustomerDetailEntity customerDetail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "views_id", referencedColumnName = "id")
    private ViewsEntity views;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }

}

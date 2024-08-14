package com.example.qLyDatBan.quanLyDatBan.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Table(name = "customer_detail")
@Data
@Entity
public class CustomerDetailEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @OneToMany(mappedBy = "customerDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BookingEntity> listBooking;
}

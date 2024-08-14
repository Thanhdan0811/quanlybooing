package com.example.qLyDatBan.quanLyDatBan.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;


@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


}

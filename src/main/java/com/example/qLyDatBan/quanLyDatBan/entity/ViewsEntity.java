package com.example.qLyDatBan.quanLyDatBan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Table(name = "views")
@Data
@Entity
public class ViewsEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String desk_img;

    @Column(nullable = true)
    private String description;

    @Column(nullable = true, updatable = false)
    private Date createTime;

    @Column(nullable = true, insertable = false)
    private Date updateTime;

    // được tham chiếu là JsonManage, tham chieu JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "category_id", nullable = false)
    @JsonBackReference
    private CategoryEntity category;

    @OneToMany(mappedBy = "views", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<BookingEntity> booking;

    @PrePersist
    protected void onCreate() {
        createTime = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = new Date();
    }

    @Override
    public String toString() {
        return "t;hí is : " + this.getId() + "  " + this.getName();
    }
}

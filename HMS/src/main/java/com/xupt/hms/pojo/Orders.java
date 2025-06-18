package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Orders {
    private int ordersid;
    private String ordercode;
    private int usersid;
    private int employid;
    private double total;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
    private String status;
    private int tabxid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date orderdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String sectionx;
}
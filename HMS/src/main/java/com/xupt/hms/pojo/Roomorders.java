package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Roomorders {
    private int roomordersid;
    private String ordercode;
    private int usersid;
    private int roomsid;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date thestart;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date theend;
    private String status;
    private String memo;
    private double total;
}
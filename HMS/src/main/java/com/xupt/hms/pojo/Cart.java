package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Cart {
    private int cartid;
    private int usersid;
    private int foodsid;
    private double price;
    private int num;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
}
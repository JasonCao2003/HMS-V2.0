package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Booking {
    private int bookingid;
    private String bno;
    private String cate;
    private String reason;
    private double total;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
}
package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Buy {
    private int buyid;
    private String bno;
    private String goodsname;
    private double price;
    private int num;
    private double total;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
    private String manager;
    private String memo;
}
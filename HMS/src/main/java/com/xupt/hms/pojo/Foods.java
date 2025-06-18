package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Foods {
    private int foodsid;
    private String foodsname;
    private String image;
    private int cateid;
    private double price;
    private String recommend;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
    private int hits;
    private int sellnum;
    private String contents;
}

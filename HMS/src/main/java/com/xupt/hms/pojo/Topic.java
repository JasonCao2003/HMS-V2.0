package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Topic {
    private int topicid;
    private int ordersid;
    private int usersid;
    private int foodsid;
    private double num;
    private String contents;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
}
package com.xupt.hms.pojo;

import lombok.Data;

@Data
public class Details {
    private int detailsid;
    private int ordersid;
    private int foodsid;
    private double price;
    private int num;
    private String memo;
}
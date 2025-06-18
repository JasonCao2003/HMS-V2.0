package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Charge {
    private int chargeid;
    private String cno;
    private int ordersid;
    private int usersid;
    private double total;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;

}
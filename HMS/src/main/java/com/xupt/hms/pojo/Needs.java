package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Needs {
    private int needsid;
    private int roomordersid;
    private int roomsid;
    private int usersid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String goodsname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
    private String status;
    private String reps;
}
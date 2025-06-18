package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Complains {
    private int complainsid;
    private int usersid;
    private String title;
    private String contents;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
    private String status;
    private String reps;
}
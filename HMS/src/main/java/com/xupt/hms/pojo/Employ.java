package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Employ {
    private int employid;
    private String eno;
    private String password;
    private String employname;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String jiguan;
    private String idcard;
    private double salary;
    private String contact;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;

}
package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data

public class Admin {
    private Integer adminid;
    private String username;
    private String password;
    private String realname;
    private String contact;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
}
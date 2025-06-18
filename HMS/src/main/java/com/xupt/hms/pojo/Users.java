package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Users {
    private int usersid;
    private String username;
    private String password;
    private String realname;
    private String sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String contact;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regdate;
    private String imgstr;
}

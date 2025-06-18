package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Cate {
    private int cateid;
    private String catename;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
    private String memo;
}
package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Tabx {
    private int tabxid;
    private String tabxname;
    private String image;
    private int num;
    private String cate;
    private String place;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
    private String contents;
}

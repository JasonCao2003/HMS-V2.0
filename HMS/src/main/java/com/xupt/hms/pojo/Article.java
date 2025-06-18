package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data

public class Article {
    private int articleid;
    private String title;
    private String image;
    private String contents;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addtime;
    private int hits;
}
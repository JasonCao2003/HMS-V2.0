package com.xupt.hms.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Email {
    private List<String> tos;
    private String subject;
    private String content;
}

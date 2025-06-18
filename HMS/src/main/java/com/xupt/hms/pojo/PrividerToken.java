package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data

@Component
public class PrividerToken {
    private Integer id;
    private String client_id;
    private String redirect_uri;
    private String code;
    private String state;
    private String client_Secret;
}


package com.xupt.hms.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data

@Component
public class gitUser {
    private int id;
    private String name;
    private String email;
    private String login;
}

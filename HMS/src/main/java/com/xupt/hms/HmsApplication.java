package com.xupt.hms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.xupt.hms.mapper")

@SpringBootApplication
public class HmsApplication {


    public static void main(String[] args) {
        SpringApplication.run(HmsApplication.class, args);
        System.out.println("项目已启动...");
    }

}

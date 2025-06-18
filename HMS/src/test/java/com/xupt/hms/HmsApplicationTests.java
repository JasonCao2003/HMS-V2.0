package com.xupt.hms;

import com.xupt.hms.mapper.UsersMapper;
import com.xupt.hms.pojo.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class HmsApplicationTests {

    @Autowired
    private UsersMapper usersMapper;

    @Test
    void contextLoads() {
        //Wrapper是条件构造器
        List<Users> users = usersMapper.selectList(null);
        users.forEach(System.out::println);
    }

}

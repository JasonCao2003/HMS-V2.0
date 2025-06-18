package com.xupt.hms.service;

import com.xupt.hms.pojo.Foods;
import com.xupt.hms.pojo.Topic;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FrontServiceImpl implements FrontService {

    @Override
    public Boolean roomOrders() {
        return null;
    }

    @Override
    public Boolean foodOrders(Foods food) {
        return null;
    }

    @Override
    public List<Topic> topicList() {
        return null;
    }

    @Override
    public Boolean topicAdd(Topic topic) {
        return null;
    }

    @Override
    public Boolean topicDelete(Topic topic) {
        return null;
    }
}

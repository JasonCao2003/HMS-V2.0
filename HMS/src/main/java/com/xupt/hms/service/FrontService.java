package com.xupt.hms.service;

import com.xupt.hms.pojo.Foods;
import com.xupt.hms.pojo.Topic;

import java.util.List;

public interface FrontService {


    // 房间预订 RoomOrders
    Boolean roomOrders();

    // 餐品预定 FoodsOrders
    Boolean foodOrders(Foods food);

    // 展示留言 topic
    List<Topic> topicList();

    // 添加留言
    Boolean topicAdd(Topic topic);

    // 删除留言
    Boolean topicDelete(Topic topic);
    // 反馈 complains

}

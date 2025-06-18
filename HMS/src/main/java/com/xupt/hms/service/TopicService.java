package com.xupt.hms.service;

import com.xupt.hms.pojo.Topic;

import java.util.List;

public interface TopicService {
    Boolean topicAdd(Topic topic);

    List<Topic> topicList(String name);

    Boolean topicDelete(Integer topicid);

    Boolean topicUpdate(Topic topic);

    Topic SelectByID(Integer topicid);

    List<Topic> listTopicByUserid(Integer id);
}

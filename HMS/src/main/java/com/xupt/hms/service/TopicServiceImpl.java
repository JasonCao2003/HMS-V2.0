package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.TopicMapper;
import com.xupt.hms.pojo.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {


    private final TopicMapper topicMapper;

    public TopicServiceImpl(TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Boolean topicAdd(Topic topic) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("topicid", topic.getTopicid());
        Topic currenttopic = topicMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(currenttopic)) {
            topicMapper.insert(topic);
            return true;
        }
        return false;
    }

    @Override
    public List<Topic> topicList(String name) {
        // 根据name决定缓存的键
        String cacheKey = ObjectUtils.isEmpty(name) ? "topics" : "topics:" + name;

        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Topic> topics;

        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(name)) {
                int id = Integer.parseInt(name);
                queryWrapper.eq("topicid", id);
            }
            topics = topicMapper.selectList(queryWrapper);

            // 将数据存入缓存
            cachedData = JSON.toJSONString(topics);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            topics = JSON.parseObject(cachedData, new TypeReference<List<Topic>>() {
            });
        }

        return topics;
    }

    @Override
    public Boolean topicDelete(Integer topicid) {
        try {
            QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
            topicMapper.delete(queryWrapper.eq("topicid", topicid));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean topicUpdate(Topic topic) {
        UpdateWrapper<Topic> updatewrapper = new UpdateWrapper<>();
        updatewrapper.eq("topicid", topic.getTopicid());
        int rows = topicMapper.update(topic, updatewrapper);
        return rows > 0;
    }

    @Override
    public Topic SelectByID(Integer topicid) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        return topicMapper.selectOne(queryWrapper.eq("topicid", topicid));
    }

    @Override
    public List<Topic> listTopicByUserid(Integer id) {
        QueryWrapper<Topic> queryWrapper = new QueryWrapper<>();
        List<Topic> topics = topicMapper.selectList(queryWrapper.eq("usersid", id));
        return topics;
    }

}

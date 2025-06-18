package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.FoodsMapper;
import com.xupt.hms.pojo.Foods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class FoodsServiceImpl implements FoodsService {

    //自动注入
    private final FoodsMapper foodsMapper;

    @Autowired
    public FoodsServiceImpl(FoodsMapper foodsMapper) {
        this.foodsMapper = foodsMapper;
    }

    @Autowired
    public StringRedisTemplate redisTemplate;

    @Override
    public Boolean foodsAdd(Foods foods) {
        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("foodsname", foods.getFoodsname());
        Foods foods1 = foodsMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(foods1)) {
            foodsMapper.insert(foods);
            return true;
        }
        return false;
    }

    @Override
    public List<Foods> foodsList(String foodsname) {
        // 根据食品名称决定缓存的键
        String cacheKey = ObjectUtils.isEmpty(foodsname) ? "foods" : "foods:" + foodsname;

        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Foods> foods;

        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(foodsname)) {
                queryWrapper.eq("foodsname", foodsname);
            }
            foods = foodsMapper.selectList(queryWrapper);

            // 将数据存入缓存
            cachedData = JSON.toJSONString(foods);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            foods = JSON.parseObject(cachedData, new TypeReference<List<Foods>>() {
            });
        }

        return foods;
    }


    @Override
    public boolean foodsDelete(Integer id) {
        try {
            QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
            foodsMapper.delete(queryWrapper.eq("foodsid", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean foodsUpdate(Foods foods) {
        // 1、创建更新条件，假设你想根据主键更新
        UpdateWrapper<Foods> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("foodsid", foods.getFoodsid());
        // 2、执行更新操作
        int rows = foodsMapper.update(foods, updateWrapper);
        return rows > 0;
    }

    @Override
    public Foods selectById(Integer id) {
        QueryWrapper<Foods> queryWrapper = new QueryWrapper<>();
        return foodsMapper.selectOne(queryWrapper.eq("foodsid", id));
    }
}

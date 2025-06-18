package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.RoomsMapper;
import com.xupt.hms.pojo.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class RoomsServiceImpl implements RoomsService {


    private final RoomsMapper roomsmapper;

    @Autowired
    public RoomsServiceImpl(RoomsMapper roomsMapper) {
        this.roomsmapper = roomsMapper;
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Boolean roomsAdd(Rooms rooms) {
        QueryWrapper<Rooms> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roomsno", rooms.getRoomsno());
        Rooms rooms1 = roomsmapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(rooms1)) {
            roomsmapper.insert(rooms);
            return true;
        }
        return false;
    }

    @Override
    public List<Rooms> roomsList(String roomsno) {
        // 根据房间编号决定缓存的键
        String cacheKey = ObjectUtils.isEmpty(roomsno) ? "rooms" : "rooms:" + roomsno;

        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Rooms> rooms;

        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Rooms> queryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(roomsno)) {
                queryWrapper.eq("roomsno", roomsno);
            }
            rooms = roomsmapper.selectList(queryWrapper);
            // 将数据存入缓存
            cachedData = JSON.toJSONString(rooms);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            rooms = JSON.parseObject(cachedData, new TypeReference<List<Rooms>>() {
            });
        }
        return rooms;
    }


    @Override
    public boolean roomsDelete(Integer id) {
        try {
            QueryWrapper<Rooms> queryWrapper = new QueryWrapper<>();
            roomsmapper.delete(queryWrapper.eq("roomsid", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean roomsUpdate(Rooms rooms) {
        // 1、创建更新条件，假设你想根据主键更新
        UpdateWrapper<Rooms> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("roomsid", rooms.getRoomsid());
        // 2、执行更新操作
        int rows = roomsmapper.update(rooms, updateWrapper);
        return rows > 0;
    }

    @Override
    public Rooms selectById(Integer id) {
        QueryWrapper<Rooms> queryWrapper = new QueryWrapper<>();
        return roomsmapper.selectOne(queryWrapper.eq("roomsid", id));
    }
}

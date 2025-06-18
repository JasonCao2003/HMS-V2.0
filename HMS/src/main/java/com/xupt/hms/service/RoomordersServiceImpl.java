package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.RoomordersMapper;
import com.xupt.hms.pojo.Roomorders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@Transactional
public class RoomordersServiceImpl implements RoomordersService {

    private final RoomordersMapper roomordersMapper;

    @Autowired
    public RoomordersServiceImpl(RoomordersMapper roomordersMapper) {
        this.roomordersMapper = roomordersMapper;
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<Roomorders> roomordersList(String ordercode) {
        // 根据订单编号决定缓存的键
        String cacheKey = ObjectUtils.isEmpty(ordercode) ? "roomorders" : "roomorders:" + ordercode;

        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Roomorders> roomorders;

        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Roomorders> queryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(ordercode)) {
                queryWrapper.eq("ordercode", ordercode);
            }
            roomorders = roomordersMapper.selectList(queryWrapper);

            // 将数据存入缓存
            cachedData = JSON.toJSONString(roomorders);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            roomorders = JSON.parseObject(cachedData, new TypeReference<List<Roomorders>>() {
            });
        }

        return roomorders;
    }


    @Override
    public Boolean roomordersAdd(Roomorders roomorders) {
        QueryWrapper<Roomorders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roomordersid", roomorders.getRoomordersid());
        Roomorders roomorders1 = roomordersMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(roomorders1)) {
            roomordersMapper.insert(roomorders);
            return true;
        }
        return false;
    }

    @Override
    public boolean roomordersDelete(Integer id) {
        try {
            QueryWrapper<Roomorders> queryWrapper = new QueryWrapper<>();
            roomordersMapper.delete(queryWrapper.eq("roomordersid", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean roomordersUpdate(Roomorders roomorders) {
        UpdateWrapper<Roomorders> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("roomordersid", roomorders.getRoomordersid());
        // 3、执行更新操作
        int rows = roomordersMapper.update(roomorders, updateWrapper);
        return rows > 0;
    }

    @Override
    public Roomorders selectById(Integer id) {
        QueryWrapper<Roomorders> queryWrapper = new QueryWrapper<>();
        return roomordersMapper.selectOne(queryWrapper.eq("roomordersid", id));
    }

    @Override
    public List<Roomorders> listByUserid(int usersid) {
        QueryWrapper<Roomorders> queryWrapper = new QueryWrapper<>();
        return roomordersMapper.selectList(queryWrapper.eq("usersid", usersid));
    }
}

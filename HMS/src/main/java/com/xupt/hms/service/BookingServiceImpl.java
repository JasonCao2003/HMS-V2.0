package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.BookingMapper;
import com.xupt.hms.pojo.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;

    @Autowired
    public BookingServiceImpl(BookingMapper booking) {
        this.bookingMapper = booking;
    }

    @Autowired
    public StringRedisTemplate redisTemplate;

    // 添加
    @Override
    public Boolean bookingAdd(Booking booking) {
        try {
            bookingMapper.insert(booking);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public List<Booking> bookingList(String name) {
        // 根据名称决定缓存的键
        String cacheKey = ObjectUtils.isEmpty(name) ? "bookings" : "bookings:" + name;

        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Booking> bookings;

        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Booking> queryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(name)) {
                queryWrapper.eq("bno", name);
            }
            bookings = bookingMapper.selectList(queryWrapper);

            // 将数据存入缓存
            cachedData = JSON.toJSONString(bookings);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            bookings = JSON.parseObject(cachedData, new TypeReference<List<Booking>>() {
            });
        }

        return bookings;
    }


    @Override
    public boolean bookingDelete(Integer id) {
        try {
            QueryWrapper<Booking> queryWrapper = new QueryWrapper<>();
            bookingMapper.delete(queryWrapper.eq("bookingid", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean bookingUpdate(Booking booking) {
        // 1、创建更新条件，假设你想根据主键更新
        UpdateWrapper<Booking> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("bookingid", booking.getBookingid());
        // 2、执行更新操作
        int rows = bookingMapper.update(booking, updateWrapper);
        return rows > 0;
    }


    @Override
    public Booking selectById(Integer id) {
        QueryWrapper<Booking> queryWrapper = new QueryWrapper<>();
        return bookingMapper.selectOne(queryWrapper.eq("bookingid", id));
    }
}

package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xupt.hms.mapper.BookingMapper;
import com.xupt.hms.mapper.RoomsMapper;
import com.xupt.hms.mapper.TabXMapper;
import com.xupt.hms.pojo.Booking;
import com.xupt.hms.pojo.Rooms;
import com.xupt.hms.pojo.Tabx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
public class StatisticsServiceImpl implements StatisticsService {
    private final BookingMapper bookingMapper;
    private final RoomsMapper roomsMapper;
    private final TabXMapper tabxMapper;

    @Autowired
    public StatisticsServiceImpl(BookingMapper bookingMapper, RoomsMapper roomsMapper, TabXMapper tabxMapper) {
        this.bookingMapper = bookingMapper;
        this.roomsMapper = roomsMapper;
        this.tabxMapper = tabxMapper;
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<Date> bookingAddTimeList() {
        // 定义缓存键
        String cacheKey = "bookingAddTimes";

        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Date> addTimes;

        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Booking> queryWrapper = new QueryWrapper<>();
            // 设置要查询的字段，这里是 addtime
            queryWrapper.select("addtime");
            List<Booking> bookingList = bookingMapper.selectList(queryWrapper);

            // 从查询结果中提取 addtime 值
            addTimes = bookingList.stream()
                    .map(Booking::getAddtime)
                    .collect(Collectors.toList());

            // 将数据存入缓存
            cachedData = JSON.toJSONString(addTimes);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            addTimes = JSON.parseObject(cachedData, new TypeReference<List<Date>>() {
            });
        }
        return addTimes;
    }


    @Override
    public List<Double> bookingTotalList() {
        QueryWrapper<Booking> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("total"); // 设置要查询的字段，这里是 total
        List<Booking> bookingList = bookingMapper.selectList(queryWrapper);

        // 从查询结果中提取 total 值
        List<Double> values = bookingList.stream()
                .map(Booking::getTotal)
                .collect(Collectors.toList());

        return values;
    }


    @Override
    public List<String> roomsCateList() {
        QueryWrapper<Rooms> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("cate");
        List<Rooms> roomsList = roomsMapper.selectList(queryWrapper);
        List<String> values = roomsList.stream()
                .map(Rooms::getCate)
                .collect(Collectors.toList());

        return values;
    }

    @Override
    public List<Integer> roomsHitsList() {
        QueryWrapper<Rooms> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("hits");
        List<Rooms> roomsList = roomsMapper.selectList(queryWrapper);
        List<Integer> values = roomsList.stream()
                .map(Rooms::getHits)
                .collect(Collectors.toList());

        return values;
    }

    @Override
    /*豪华&普通*/
    public List<String> tabxCateList() {
        QueryWrapper<Tabx> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("cate");
        List<Tabx> tabxList = tabxMapper.selectList(queryWrapper);
        List<String> values = tabxList.stream()
                .map(Tabx::getCate)
                .collect(Collectors.toList());
        return values;
    }

    @Override
    /*方位*/
    public List<String> tabxPlaceList() {
        QueryWrapper<Tabx> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("place");
        List<Tabx> tabxList = tabxMapper.selectList(queryWrapper);
        List<String> values = tabxList.stream()
                .map(Tabx::getPlace)
                .collect(Collectors.toList());
        return values;
    }

    @Override
    /*数量*/
    public List<Integer> tabxNumList() {
        QueryWrapper<Tabx> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("num");
        List<Tabx> tabxList = tabxMapper.selectList(queryWrapper);
//        List<Tabx> newtabList = tabxList
        List<Integer> values = tabxList.stream()
                .map(Tabx::getNum)
                .collect(Collectors.toList());

        return values;
    }

    class tabxdemo {
        String place;
        String cate;
        Integer num;
    }

    @Override
    public List<Tabx> tabxList() {
        return null;
    }

}

package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.OrdersMapper;
import com.xupt.hms.pojo.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    private final OrdersMapper ordersMapper;

    @Autowired
    public OrdersServiceImpl(OrdersMapper ordersMapper) {
        this.ordersMapper = ordersMapper;
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<Orders> ordersList(String ordercode) {
        // 根据订单编号决定缓存的键
        String cacheKey = ObjectUtils.isEmpty(ordercode) ? "orders" : "orders:" + ordercode;

        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Orders> orders;

        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(ordercode)) {
                queryWrapper.eq("ordercode", ordercode);
            }
            orders = ordersMapper.selectList(queryWrapper);

            // 将数据存入缓存
            cachedData = JSON.toJSONString(orders);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            orders = JSON.parseObject(cachedData, new TypeReference<List<Orders>>() {
            });
        }
        return orders;
    }


    @Override
    public List<Orders> listByUserid(Integer id) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        return ordersMapper.selectList(queryWrapper.eq("usersid", id));
    }


    @Override
    public Boolean ordersAdd(Orders orders) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ordersid", orders.getOrdersid());
        Orders orders1 = ordersMapper.selectOne(queryWrapper);
        System.out.println(orders);
        if (ObjectUtils.isEmpty(orders1)) {
            ordersMapper.insert(orders);
            return true;
        }
        return false;
    }

    @Override
    public boolean ordersDelete(Integer id) {
        try {
            QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
            ordersMapper.delete(queryWrapper.eq("ordersid", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean ordersUpdate(Orders orders) {
        UpdateWrapper<Orders> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("ordersid", orders.getOrdersid());
        // 3、执行更新操作
        int rows = ordersMapper.update(orders, updateWrapper);
        return rows > 0;
    }

    @Override
    public Orders selectById(Integer id) {
        QueryWrapper<Orders> queryWrapper = new QueryWrapper<>();
        return ordersMapper.selectOne(queryWrapper.eq("ordersid", id));
    }


}

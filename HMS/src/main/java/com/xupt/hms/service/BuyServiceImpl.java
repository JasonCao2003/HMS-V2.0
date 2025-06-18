package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.BuyMapper;
import com.xupt.hms.pojo.Buy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class BuyServiceImpl implements BuyService {
    //自动注入
    private final BuyMapper buyMapper;

    @Autowired
    public BuyServiceImpl(BuyMapper buyMapper) {
        this.buyMapper = buyMapper;
    }

    @Autowired
    public StringRedisTemplate redisTemplate;

    @Override
    public Boolean buyAdd(Buy buy) {
        QueryWrapper<Buy> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("bno", buy.getBno());
        Buy buy1 = buyMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(buy1)) {
            buyMapper.insert(buy);
            return true;
        }
        return false;
    }

    @Override
    public List<Buy> buyList(String goodsname) {
        // 根据商品名称决定缓存的键
        String cacheKey = ObjectUtils.isEmpty(goodsname) ? "buys" : "buys:" + goodsname;
        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Buy> buys;
        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Buy> queryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(goodsname)) {
                queryWrapper.eq("goodsname", goodsname);
            }
            buys = buyMapper.selectList(queryWrapper);
            // 将数据存入缓存
            cachedData = JSON.toJSONString(buys);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            buys = JSON.parseObject(cachedData, new TypeReference<List<Buy>>() {
            });
        }
        return buys;
    }


    @Override
    public boolean buyDelete(Integer id) {
        try {
            QueryWrapper<Buy> queryWrapper = new QueryWrapper<>();
            buyMapper.delete(queryWrapper.eq("buyid", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean buyUpdate(Buy buy) {
        // 1、创建更新条件，假设你想根据主键更新
        UpdateWrapper<Buy> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("buyid", buy.getBuyid());
        // 2、执行更新操作
        int rows = buyMapper.update(buy, updateWrapper);
        return rows > 0;
    }

    @Override
    public Buy selectById(Integer id) {
        QueryWrapper<Buy> queryWrapper = new QueryWrapper<>();
        return buyMapper.selectOne(queryWrapper.eq("buyid", id));
    }
}

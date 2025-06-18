package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.HistMapper;
import com.xupt.hms.pojo.Hist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
@Transactional
public class HistServiceImpl implements HistService {
    private final HistMapper histMapper;

    @Autowired
    public HistServiceImpl(HistMapper histMapper) {
        this.histMapper = histMapper;
    }

    @Autowired
    public StringRedisTemplate redisTemplate;

    @Override
    public Boolean histAdd(Hist hist) {
        QueryWrapper<Hist> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("histid", hist.getHistid());
        Hist hist1 = histMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(hist1)) {
            histMapper.insert(hist);
            return true;
        }
        return false;
    }

    @Override
    public List<Hist> histList(String name) {
        // 根据名称决定缓存的键
        String cacheKey = ObjectUtils.isEmpty(name) ? "hists" : "hists:" + name;
        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Hist> hists;
        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Hist> queryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(name)) {
                queryWrapper.eq("histid", name);
            }
            hists = histMapper.selectList(queryWrapper);
            // 将数据存入缓存
            cachedData = JSON.toJSONString(hists);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            hists = JSON.parseObject(cachedData, new TypeReference<List<Hist>>() {
            });
        }
        return hists;
    }


    @Override
    public boolean histDelete(Integer id) {
        try {
            QueryWrapper<Hist> queryWrapper = new QueryWrapper<>();
            histMapper.delete(queryWrapper.eq("histid", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean histUpdate(Hist hist) {
        UpdateWrapper<Hist> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("histid", hist.getHistid());

        // 3、执行更新操作
        int rows = histMapper.update(hist, updateWrapper);
        return rows > 0;
    }

    @Override
    public Hist selectById(Integer id) {
        QueryWrapper<Hist> queryWrapper = new QueryWrapper<>();
        return histMapper.selectOne(queryWrapper.eq("histid", id));
    }
}

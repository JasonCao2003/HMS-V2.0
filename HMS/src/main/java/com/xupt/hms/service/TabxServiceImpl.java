package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.TabXMapper;
import com.xupt.hms.pojo.Tabx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class TabxServiceImpl implements TabxService {

    //自动注入
    private final TabXMapper tabXMapper;

    @Autowired
    public TabxServiceImpl(TabXMapper tabXMapper) {
        this.tabXMapper = tabXMapper;
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Boolean tabxAdd(Tabx tabx) {
        QueryWrapper<Tabx> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tabxname", tabx.getTabxname());
        Tabx tabx1 = tabXMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(tabx1)) {
            tabXMapper.insert(tabx);
            return true;
        }
        return false;
    }

    @Override
    public List<Tabx> tabxList(String tabxname) {
        // 根据tabxname决定缓存的键
        String cacheKey = ObjectUtils.isEmpty(tabxname) ? "tabxs" : "tabxs:" + tabxname;

        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Tabx> tabxs;

        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Tabx> queryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(tabxname)) {
                queryWrapper.eq("tabxname", tabxname);
            }
            tabxs = tabXMapper.selectList(queryWrapper);

            // 将数据存入缓存
            cachedData = JSON.toJSONString(tabxs);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            tabxs = JSON.parseObject(cachedData, new TypeReference<List<Tabx>>() {
            });
        }

        return tabxs;
    }


    @Override
    public boolean tabxDelete(Integer id) {
        try {
            QueryWrapper<Tabx> queryWrapper = new QueryWrapper<>();
            tabXMapper.delete(queryWrapper.eq("tabxid", id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean tabxUpdate(Tabx tabx) {
        // 1、创建更新条件，假设你想根据主键更新
        UpdateWrapper<Tabx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("tabxid", tabx.getTabxid());
        // 2、执行更新操作
        int rows = tabXMapper.update(tabx, updateWrapper);
        return rows > 0;
    }


    @Override
    public Tabx selectById(Integer id) {
        QueryWrapper<Tabx> queryWrapper = new QueryWrapper<>();
        return tabXMapper.selectOne(queryWrapper.eq("tabxid", id));
    }
}

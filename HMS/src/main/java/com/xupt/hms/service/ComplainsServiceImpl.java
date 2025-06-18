package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xupt.hms.mapper.ComplainsMapper;
import com.xupt.hms.pojo.Complains;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ComplainsServiceImpl implements ComplainsService {

    private final ComplainsMapper complainsMapper;

    @Autowired
    public ComplainsServiceImpl(ComplainsMapper complainsMapper) {
        this.complainsMapper = complainsMapper;
    }

    @Autowired
    public StringRedisTemplate redisTemplate;

    @Override
    public Boolean complainsAdd(Complains complains) {
        QueryWrapper<Complains> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", complains.getTitle());
        Complains currentcomplains = complainsMapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(currentcomplains)) {
            complainsMapper.insert(complains);
            return true;
        }
        return false;
    }

    @Override
    public List<Complains> complainsList(String title) {
        // 根据标题决定缓存的键
        String cacheKey = ObjectUtils.isEmpty(title) ? "complains" : "complains:" + title;

        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Complains> complains;

        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Complains> queryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(title)) {
                queryWrapper.eq("title", title);
            }
            complains = complainsMapper.selectList(queryWrapper);

            // 将数据存入缓存
            cachedData = JSON.toJSONString(complains);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            complains = JSON.parseObject(cachedData, new TypeReference<List<Complains>>() {
            });
        }
        return complains;
    }


    @Override
    public List<Complains> listComplainsByUserid(Integer userid) {
        QueryWrapper<Complains> queryWrapper = new QueryWrapper<>();
        List<Complains> complains = complainsMapper.selectList(queryWrapper.eq("usersid", userid));
        return complains;
    }

    @Override
    public Boolean complainsDelete(Integer complainsid) {
        try {
            QueryWrapper<Complains> queryWrapper = new QueryWrapper<>();
            complainsMapper.delete(queryWrapper.eq("complainsid", complainsid));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean complainsUpdate(Complains complains) {
        int rows = complainsMapper.update(complains.getStatus(), complains.getReps(), complains.getComplainsid());
        return rows > 0;
    }


    @Override
    public Complains SelectById(Integer complainsid) {
        QueryWrapper<Complains> queryWrapper = new QueryWrapper<>();
        return complainsMapper.selectOne(queryWrapper.eq("complainsid", complainsid));
    }
}

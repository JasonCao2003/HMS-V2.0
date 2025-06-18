package com.xupt.hms.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xupt.hms.mapper.ArticleMapper;
import com.xupt.hms.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    private final ArticleMapper articlemapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articlemapper = articleMapper;
    }

    @Autowired
    public StringRedisTemplate redisTemplate;

    @Override
    public Boolean articleAdd(Article article) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", article.getTitle());
        Article currentarticle = articlemapper.selectOne(queryWrapper);
        if (ObjectUtils.isEmpty(currentarticle)) {
            articlemapper.insert(article);
            return true;
        }
        return false;
    }

    @Override
    public List<Article> articleList(String title) {
        // 根据标题决定缓存的键
        String cacheKey = ObjectUtils.isEmpty(title) ? "articles" : "articles:" + title;

        // 从缓存中获取数据
        String cachedData = redisTemplate.opsForValue().get(cacheKey);
        List<Article> articles;

        if (ObjectUtils.isEmpty(cachedData)) {
            // 如果缓存中没有数据，从数据库中获取数据
            QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
            if (!ObjectUtils.isEmpty(title)) {
                queryWrapper.eq("title", title);
            }
            articles = articlemapper.selectList(queryWrapper);
            // 将数据存入缓存
            cachedData = JSON.toJSONString(articles);
            redisTemplate.opsForValue().set(cacheKey, cachedData,30, TimeUnit.SECONDS);
        } else {
            // 从缓存中解析数据
            articles = JSON.parseObject(cachedData, new TypeReference<List<Article>>() {});
        }
        return articles;
    }


    @Override
    public Boolean articleDelete(Integer id) {
        try {
            QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
            articlemapper.delete(queryWrapper.eq("articleid", id));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public Boolean articleUpdate(Article article) {
        UpdateWrapper<Article> updatewrapper = new UpdateWrapper<>();
        updatewrapper.eq("articleid", article.getArticleid());
        int rows = articlemapper.update(article, updatewrapper);
        return rows > 0;
    }

    @Override
    public Article SelectById(Integer articleid) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        return articlemapper.selectOne(queryWrapper.eq("articleid", articleid));
    }
}

package com.xupt.hms.service;

import com.xupt.hms.pojo.Article;

import java.util.List;

public interface ArticleService {

    Boolean articleAdd(Article article);

    List<Article> articleList(String title);

    Boolean articleDelete(Integer articleid);

    Boolean articleUpdate(Article article);

    Article SelectById(Integer articleid);
}

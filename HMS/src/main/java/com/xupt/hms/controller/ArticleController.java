package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Article;
import com.xupt.hms.service.ArticleService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ArticleController {
    @Resource
    private ArticleService articleService;

    //跳转到添加页面
    @RequestMapping("goAddArticle")
    public String goAddArticle() {
        return "/admin/info/addArticle";
    }


    @Value("${photo.file.dir}")
    private String photoPath;
    // 添加新闻

    /**
     * 文件上传
     * 1、表单提交方式必须是post
     * 2、表单enctype必须为multipart/form-data
     */
    @RequestMapping("addArticle")
    public String addArticle(Article article, Model model, MultipartFile photo) throws IOException {
        // 1、修改图片文件名为 -- 时间戳.xxx
        String filenamePrefix = new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date());
        String originalFilename = photo.getOriginalFilename();
        String filenameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = filenamePrefix + filenameSuffix;
        // 2、上传文件
        photo.transferTo(new File(photoPath, newFilename));
        // 3、将图片文件名保存进数据库中
        article.setImage(newFilename);
        model.addAttribute("isAdd", articleService.articleAdd(article));
        return "admin/info/addArticle";
    }

    // 查询 - 用于修改
    @RequestMapping("selectArticle")
    public String articleSelect(Integer id, Model model) {
        model.addAttribute("article", articleService.SelectById(id));
        return "/admin/info/modifyArticle";
    }

    // 修改
    @RequestMapping("modifyArticle")
    public String articleModify(Article article, Model model, MultipartFile photo) throws IOException {
        // 1、修改图片文件名为 -- 时间戳.xxx
        String filenamePrefix = new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date());
        String originalFilename = photo.getOriginalFilename();
        String filenameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = filenamePrefix + filenameSuffix;
        // 2、上传文件
        photo.transferTo(new File(photoPath, newFilename));
        // 3、将图片文件名保存进数据库中
        article.setImage(newFilename);
        model.addAttribute("isModify", articleService.articleUpdate(article));
        model.addAttribute("article", article);
        return "/admin/info/modifyArticle";
    }


    // 删除
    @RequestMapping("deleteArticle")
    public String articleDelete(Integer id, Model model) {
        model.addAttribute("isDelete", articleService.articleDelete(id));
        return "forward:listArticles";
    }

    // 查询 - 页面渲染
    @RequestMapping("listArticles")
    public String articleList(Model model,
                              @RequestParam(value = "name", defaultValue = "") String name,
                              @RequestParam(value = "start", defaultValue = "1") int start,
                              @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "articleid asc");
        // 2、从后端获取数据
        List<Article> article = articleService.articleList(name);
        // 3、对数据分页
        PageInfo<Article> page = new PageInfo<>(article);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("articles", article);
        // 5、对下面视图进行渲染
        return "admin/info/article";
    }


}

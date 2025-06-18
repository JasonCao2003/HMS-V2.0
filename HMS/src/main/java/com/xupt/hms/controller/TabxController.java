package com.xupt.hms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xupt.hms.pojo.Tabx;
import com.xupt.hms.service.TabxService;
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
public class TabxController {

    @Resource
    private TabxService tabxService;

    @Value("${photo.file.dir}")
    private String photoPath;

    // 增加 - 跳转至修改界面
    @RequestMapping("goAddTabx")
    public String goAddTabx() {
        return "admin/goods/addTabx";
    }

    // 增加
    @RequestMapping("addTabx")
    public String addTabx(Tabx tabx, Model model, MultipartFile photo) throws IOException {
        // 1、修改图片文件名为 -- 时间戳.xxx
        if (!photo.isEmpty()) {
            String filenamePrefix = new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date());
            String originalFilename = photo.getOriginalFilename();
            String filenameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = filenamePrefix + filenameSuffix;
            // 2、上传文件
            photo.transferTo(new File(photoPath, newFilename));
            // 3、将图片文件名保存进数据库中
            tabx.setImage(newFilename);
        }
        model.addAttribute("isAdd", tabxService.tabxAdd(tabx));
        return "admin/goods/addTabx";
    }

    // 删除
    @RequestMapping("deleteTabx")
    public String buyDelete(Integer id, Model model) {
        model.addAttribute("isDelete", tabxService.tabxDelete(id));
        return "forward:listTabx";
    }

    // 修改 - 查找待修改信息并跳转至修改界面
    @RequestMapping("selectTabx")
    public String tabxSelect(Integer id, Model model) {
        model.addAttribute("tabx", tabxService.selectById(id));
        return "admin/goods/modifyTabx";
    }

    // 修改
    @RequestMapping("modifyTabx")
    public String tabxModify(Tabx tabx, Model model, MultipartFile photo) throws IOException {
        // 1、修改图片文件名为 -- 时间戳.xxx
        if (!photo.isEmpty()) {
            String filenamePrefix = new SimpleDateFormat("yyyyMMddHHmmsss").format(new Date());
            String originalFilename = photo.getOriginalFilename();
            String filenameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = filenamePrefix + filenameSuffix;
            // 2、上传文件
            photo.transferTo(new File(photoPath, newFilename));
            // 3、将图片文件名保存进数据库中
            tabx.setImage(newFilename);
        }
        model.addAttribute("isModify", tabxService.tabxUpdate(tabx));
        model.addAttribute("tabx", tabx);
        return "admin/goods/modifyTabx";
    }

    // 查询 - 页面渲染
    @RequestMapping("listTabx")
    public String tabxList(Model model,
                           @RequestParam(value = "tabxname", defaultValue = "") String tabxname,
                           @RequestParam(value = "start", defaultValue = "1") int start,
                           @RequestParam(value = "size", defaultValue = "5") int size) {

        // 预处理：处理跳转带来的脏数据
        Object isDelete = model.getAttribute("isDelete");
        if (isDelete != null) {
            model.asMap().remove("isDelete");
        }
        // 1、创建分页器
        PageHelper.startPage(start, size, "tabxid asc");
        // 2、从后端获取数据
        List<Tabx> tabxes = tabxService.tabxList(tabxname);
        // 3、对数据分页
        PageInfo<Tabx> page = new PageInfo<>(tabxes);
        // 4、将数据封装到model中，并传到前端方便渲染
        model.addAttribute("pages", page);
        model.addAttribute("tabxes", tabxes);
        // 5、对下面视图进行渲染
        return "admin/goods/tabx";
    }
}

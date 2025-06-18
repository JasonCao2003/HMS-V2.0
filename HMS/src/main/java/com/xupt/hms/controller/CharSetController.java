package com.xupt.hms.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xupt.hms.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
public class CharSetController {

    @Resource
    private StatisticsService statisticsService;

    @RequestMapping("/charset")
    public String CharSet(Model model) {
        // 单笔销售额 -- 条形图
        List<Double> data = statisticsService.bookingTotalList();
        List<Date> addtime = statisticsService.bookingAddTimeList();
        model.addAttribute("chartData", data);
        model.addAttribute("addtime", addtime);

        // 各类客房当月销售量--饼状图
        List<String> cate = statisticsService.roomsCateList();
        List<Integer> hits = statisticsService.roomsHitsList();
        // 截取前六条数据
        List<String> subCate = cate.subList(0, Math.min(6, cate.size()));
        List<Integer> subHits = hits.subList(0, Math.min(6, hits.size()));
        model.addAttribute("cate", subCate);
        model.addAttribute("hits", subHits);


        List<String> tabxCate = statisticsService.tabxCateList();
        List<String> place = statisticsService.tabxPlaceList();
        List<Integer> num = statisticsService.tabxNumList();
        model.addAttribute("tabxCate", tabxCate);
        model.addAttribute("place", place);
        model.addAttribute("num", num);


        List<Double> forecast = ForeCast();
        List<Date> forecastdate = ForeCastDate();
        model.addAttribute("forecast", forecast);
        model.addAttribute("forecastdate", forecastdate);
        return "admin/home";
    }


    public List<Double> ForeCast() {
        String response;
        try{
            Socket socket = new Socket("localhost", 50000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            // 发送数据
            out.println("Hello Server!");
            // 接收响应
            response = in.readLine();
            // 关闭连接
            socket.close();
        }catch (Exception e){
            response = "{\"data\": [0.9665464758872986, 1.0043094158172607, 0.8525944948196411, 1.1143420934677124, 1.0245126485824585, 1.32907235622406, 0.9630747437477112]}";
        }

        List<Double> data = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            JsonNode dataArray = jsonNode.get("data");
            if (dataArray.isArray()) {
                data = new ArrayList<>();
                Iterator<JsonNode> elements = dataArray.elements();
                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    data.add(element.asDouble());
                }
                return data;
            } else {
                System.out.println("预测数据获取失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public List<Date> ForeCastDate() {
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 获取未来七天的日期
        List<LocalDate> futureDates = new ArrayList<>();
        for (int i = 1; i < 8; i++) {
            futureDates.add(currentDate.plusDays(i));
        }
        // 将LocalDate转换为Date
        List<Date> dateList = futureDates.stream()
                .map(localDate -> java.sql.Date.valueOf(localDate))
                .collect(Collectors.toList());
        return dateList;
    }
}

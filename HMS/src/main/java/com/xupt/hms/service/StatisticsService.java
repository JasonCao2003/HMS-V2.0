package com.xupt.hms.service;

import com.xupt.hms.pojo.Tabx;

import java.util.Date;
import java.util.List;


public interface StatisticsService {

    List<Double> bookingTotalList();

    List<Date> bookingAddTimeList();

    List<String> roomsCateList();

    List<Integer> roomsHitsList();

    List<String> tabxCateList();

    List<String> tabxPlaceList();

    List<Integer> tabxNumList();

    List<Tabx> tabxList();
}

package com.xupt.hms.service;

import com.xupt.hms.pojo.Booking;

import java.util.List;

public interface BookingService {


    // 增加
    Boolean bookingAdd(Booking booking);

    // 查询
    List<Booking> bookingList(String name);

    // 删除
    boolean bookingDelete(Integer id);

    // 修改
    Boolean bookingUpdate(Booking booking);

    // 通过id
    Booking selectById(Integer id);

}

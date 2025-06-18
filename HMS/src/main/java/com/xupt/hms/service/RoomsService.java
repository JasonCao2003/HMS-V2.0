package com.xupt.hms.service;

import com.xupt.hms.pojo.Rooms;

import java.util.List;

public interface RoomsService {
    // 增加
    Boolean roomsAdd(Rooms rooms);

    // 查询
    List<Rooms> roomsList(String roomsno);

    // 删除
    boolean roomsDelete(Integer id);

    // 修改
    Boolean roomsUpdate(Rooms rooms);

    // 通过id
    Rooms selectById(Integer id);
}

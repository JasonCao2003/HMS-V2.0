package com.xupt.hms.service;


import com.xupt.hms.pojo.Roomorders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public interface RoomordersService {

    // 查询
    List<Roomorders> roomordersList(String ordercode);

    Boolean roomordersAdd(Roomorders roomorders);

    // 删除
    boolean roomordersDelete(Integer id);

    // 修改
    Boolean roomordersUpdate(Roomorders roomorders);

    // 通过id
    Roomorders selectById(Integer id);

    List<Roomorders> listByUserid(int usersid);
}

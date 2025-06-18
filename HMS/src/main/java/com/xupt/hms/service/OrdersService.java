package com.xupt.hms.service;

import com.xupt.hms.pojo.Orders;

import java.util.List;

public interface OrdersService {


    // 查询
    List<Orders> ordersList(String name);

    List<Orders> listByUserid(Integer id);


    Boolean ordersAdd(Orders orders);

    // 删除
    boolean ordersDelete(Integer id);

    // 修改
    Boolean ordersUpdate(Orders orders);

    // 通过id
    Orders selectById(Integer id);
}

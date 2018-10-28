package com.wm.edu.mapper.order;

import com.wm.edu.model.order.T_order_arrive;

public interface T_order_arriveMapper {
    int deleteByPrimaryKey(String id);

    int insert(T_order_arrive record);

    int insertSelective(T_order_arrive record);

    T_order_arrive selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T_order_arrive record);

    int updateByPrimaryKey(T_order_arrive record);
}
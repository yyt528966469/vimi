package com.wm.edu.mapper.order;

import com.wm.edu.model.order.T_order_commodity;

import java.util.List;

public interface T_order_commodityMapper {
    int deleteByPrimaryKey(String id);

    int insert(T_order_commodity record);

    int insertSelective(T_order_commodity record);

    T_order_commodity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T_order_commodity record);

    int updateByPrimaryKey(T_order_commodity record);

    void insertOrderCommodityList(List<T_order_commodity> list);
}
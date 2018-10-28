package com.wm.edu.mapper.order;

import com.wm.edu.model.order.T_order_express;

public interface T_order_expressMapper {
    int deleteByPrimaryKey(String express_id);

    int insert(T_order_express record);

    int insertSelective(T_order_express record);

    T_order_express selectByPrimaryKey(String express_id);

    int updateByPrimaryKeySelective(T_order_express record);

    int updateByPrimaryKey(T_order_express record);

    T_order_express getOneByOrderId(String order_id);
}
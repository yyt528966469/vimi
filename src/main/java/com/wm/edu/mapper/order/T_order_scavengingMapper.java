package com.wm.edu.mapper.order;

import com.wm.edu.model.order.T_order_scavenging;

public interface T_order_scavengingMapper {

     void insertSelective(T_order_scavenging t_order_scavenging);


     T_order_scavenging selectByPrimaryKey(String id);
}

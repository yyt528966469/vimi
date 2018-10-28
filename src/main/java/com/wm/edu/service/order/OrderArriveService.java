package com.wm.edu.service.order;

import com.wm.edu.mapper.order.T_order_arriveMapper;
import com.wm.edu.model.order.T_order_arrive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderArriveService {

    @Autowired
    private T_order_arriveMapper t_order_arriveMapper;

    public void addOrderArrive(T_order_arrive t_order_arrive){
        t_order_arriveMapper.insertSelective(t_order_arrive);
    }
}

package com.wm.edu.service.order;

import com.wm.edu.mapper.order.T_order_scavengingMapper;
import com.wm.edu.model.order.T_order_scavenging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderScavengingService {

    @Autowired
    private T_order_scavengingMapper t_order_scavengingMapper;

    public void insertSelective(T_order_scavenging t_order_scavenging){
        t_order_scavengingMapper.insertSelective(t_order_scavenging);
    }

}

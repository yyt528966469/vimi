package com.wm.edu.service.store;

import com.wm.edu.mapper.store.T_baseMapper;
import com.wm.edu.model.store.T_base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TBaseService {

    @Autowired
    private T_baseMapper t_baseMapper;

    public T_base getBaseById(String id){
        return t_baseMapper.selectByPrimaryKey(id);
    }
}

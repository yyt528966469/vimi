package com.wm.edu.mapper.store;

import com.wm.edu.model.store.T_base;

public interface T_baseMapper {
    int deleteByPrimaryKey(String id);

    int insert(T_base record);

    int insertSelective(T_base record);

    T_base selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T_base record);

    int updateByPrimaryKey(T_base record);
}
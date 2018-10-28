package com.wm.edu.mapper.vip;

import com.wm.edu.model.vip.T_recharge;

import java.util.List;

public interface T_rechargeMapper {
    int deleteByPrimaryKey(String id);

    int insert(T_recharge record);

    int insertSelective(T_recharge record);

    T_recharge selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T_recharge record);

    int updateByPrimaryKey(T_recharge record);

    List<T_recharge> getRechargeList(T_recharge t_recharge);
}
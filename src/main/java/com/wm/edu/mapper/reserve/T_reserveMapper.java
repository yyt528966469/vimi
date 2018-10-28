package com.wm.edu.mapper.reserve;

import com.wm.edu.model.reserve.T_reserve;

import java.util.List;

public interface T_reserveMapper {
    int deleteByPrimaryKey(String id);

    int insert(T_reserve record);

    int insertSelective(T_reserve record);

    T_reserve selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T_reserve record);

    int updateByPrimaryKey(T_reserve record);

    /**
     * 后台分页查询
     * @param t_reserve
     * @return
     */
    List<T_reserve> getReserveList(T_reserve t_reserve);

    /**
     * 获取用户预约单
     * @return
     */
    List<T_reserve> getUserReserveList(String c_user_id);
}
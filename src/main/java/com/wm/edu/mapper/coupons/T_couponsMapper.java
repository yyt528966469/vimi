package com.wm.edu.mapper.coupons;

import com.wm.edu.model.coupons.T_coupons;

import java.util.List;

public interface T_couponsMapper {
    int deleteByPrimaryKey(String coupons_id);

    int insert(T_coupons record);

    int insertSelective(T_coupons record);

    T_coupons selectByPrimaryKey(String coupons_id);

    int updateByPrimaryKeySelective(T_coupons record);

    int updateByPrimaryKey(T_coupons record);

    List<T_coupons> getCouponsList(T_coupons t_coupons);
}
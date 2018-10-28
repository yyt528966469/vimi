package com.wm.edu.mapper.coupons;

import com.wm.edu.model.coupons.T_coupons;
import com.wm.edu.model.coupons.T_user_coupons;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface T_user_couponsMapper {
    int deleteByPrimaryKey(String id);

    int insert(T_user_coupons record);

    int insertSelective(T_user_coupons record);

    T_user_coupons selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T_user_coupons record);

    int updateByPrimaryKey(T_user_coupons record);

    T_user_coupons getOneCoupons(@Param("c_user_id") String c_user_id, @Param("coupons_id")String coupons_id);

    List<T_user_coupons> getUserCouponsList(T_user_coupons t_user_coupons);

    T_coupons getMaxCoupons(T_user_coupons t_user_coupons);

    List<T_coupons> getAllCouponsList(String c_user_id);

    Integer getUserCouponsNum(String c_user_id);

    void updateUserCouspons(T_user_coupons t_user_coupons);
}
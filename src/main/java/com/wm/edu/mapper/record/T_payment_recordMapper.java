package com.wm.edu.mapper.record;

import com.wm.edu.model.record.T_payment_record;

import java.util.List;

public interface T_payment_recordMapper {
    int deleteByPrimaryKey(String id);

    int insert(T_payment_record record);

    int insertSelective(T_payment_record record);

    T_payment_record selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T_payment_record record);

    int updateByPrimaryKey(T_payment_record record);

    /**
     * 获取支付记录
     * @param c_user_id
     * @return
     */
    List<T_payment_record> getPayList(String c_user_id);
}
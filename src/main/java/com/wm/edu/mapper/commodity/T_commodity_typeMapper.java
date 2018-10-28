package com.wm.edu.mapper.commodity;

import com.wm.edu.model.commodity.T_commodity_type;

import java.util.List;

public interface T_commodity_typeMapper {
    int deleteByPrimaryKey(String type_id);

    int insert(T_commodity_type record);

    int insertSelective(T_commodity_type record);

    T_commodity_type selectByPrimaryKey(String type_id);

    int updateByPrimaryKeySelective(T_commodity_type record);

    int updateByPrimaryKey(T_commodity_type record);

    List<T_commodity_type> getTypeList(T_commodity_type t_commodity_type);
}
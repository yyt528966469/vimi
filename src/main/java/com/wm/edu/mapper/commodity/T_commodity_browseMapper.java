package com.wm.edu.mapper.commodity;

import com.wm.edu.model.commodity.T_commodity_browse;
import org.apache.ibatis.annotations.Param;

public interface T_commodity_browseMapper {
    int deleteByPrimaryKey(String id);

    int insert(T_commodity_browse record);

    int insertSelective(T_commodity_browse record);

    T_commodity_browse selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(T_commodity_browse record);

    int updateByPrimaryKey(T_commodity_browse record);

    T_commodity_browse getBrowseByCommIdAndUserId(@Param("commodity_id") String commodity_id, @Param("c_user_id")String c_user_id);
}
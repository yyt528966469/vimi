package com.wm.edu.mapper.commodity;

import com.wm.edu.model.commodity.T_commodity_info;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface T_commodity_infoMapper {
    int deleteByPrimaryKey(String commodity_id);

    int insert(T_commodity_info record);

    int insertSelective(T_commodity_info record);

    T_commodity_info selectByPrimaryKey(String commodity_id);

    int updateByPrimaryKeySelective(T_commodity_info record);

    int updateByPrimaryKey(T_commodity_info record);

    List<T_commodity_info> getCommList(T_commodity_info t_commodity_info);

    List<T_commodity_info > getRecommendComm();

    List<T_commodity_info> getInfoByComm_num();

    List<T_commodity_info> getCommListByType(T_commodity_info t_commodity_info);

    T_commodity_info getCommodityDetails(@Param("commodity_id") String commodity_id,@Param("c_user_id") String c_user_id);
}
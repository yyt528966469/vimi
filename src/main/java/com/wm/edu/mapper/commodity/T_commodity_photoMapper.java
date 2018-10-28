package com.wm.edu.mapper.commodity;

import com.wm.edu.model.commodity.T_commodity_photo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface T_commodity_photoMapper {
    int deleteByPrimaryKey(String photo_id);

    int insert(T_commodity_photo record);

    int insertSelective(T_commodity_photo record);

    T_commodity_photo selectByPrimaryKey(String photo_id);

    int updateByPrimaryKeySelective(T_commodity_photo record);

    int updateByPrimaryKey(T_commodity_photo record);

    void insertPhotoList(List<T_commodity_photo> list);

    void deletePhoto(@Param("commodity_id") String commodity_id, @Param("img_type")Integer img_type);
}
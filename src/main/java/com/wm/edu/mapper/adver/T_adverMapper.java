package com.wm.edu.mapper.adver;

import com.wm.edu.model.adver.T_adver;

import java.util.List;

public interface T_adverMapper {
    int deleteByPrimaryKey(String adver_id);

    int insert(T_adver record);

    int insertSelective(T_adver record);

    T_adver selectByPrimaryKey(String adver_id);

    int updateByPrimaryKeySelective(T_adver record);

    int updateByPrimaryKey(T_adver record);

    List<T_adver> getAdverList(T_adver t_adver);
}
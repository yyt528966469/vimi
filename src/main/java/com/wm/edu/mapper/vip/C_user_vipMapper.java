package com.wm.edu.mapper.vip;

import com.wm.edu.model.vip.C_user_vip;

import java.util.List;

public interface C_user_vipMapper {
    int deleteByPrimaryKey(String id);

    int insert(C_user_vip record);

    int insertSelective(C_user_vip record);

    C_user_vip selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(C_user_vip record);

    int updateByPrimaryKey(C_user_vip record);

    List<C_user_vip> getVipList(C_user_vip c_user_vip);

    C_user_vip getVipByUserId(String c_user_id);
}
package com.wm.edu.mapper.cuser;


import com.wm.edu.model.base.Express;
import com.wm.edu.model.cuser.C_user_info;

import java.util.List;

public interface C_user_infoMapper {
    int deleteByPrimaryKey(String cUserId);

    int insert(C_user_info record);

    int insertSelective(C_user_info record);

    C_user_info selectByPrimaryKey(String c_user_id);

    int updateByPrimaryKeySelective(C_user_info record);

    int updateByPrimaryKey(C_user_info record);

    Integer getAllUserNum();

    C_user_info getUserByOpenid(String openid);

    List<C_user_info> getUserList(C_user_info c_user_info);

    void addEx(List<Express> list);
}
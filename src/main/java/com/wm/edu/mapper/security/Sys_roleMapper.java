package com.wm.edu.mapper.security;


import com.wm.edu.model.security.Sys_role;
import com.wm.edu.model.security.Sys_role_res;

import java.util.List;

public interface Sys_roleMapper {

    List<Sys_role> getSysRole(Sys_role sys_role);

    List<Sys_role> getSysRoleByUserId(Integer user_id);

    int deleteByPrimaryKey(Integer id);

    int insert(Sys_role record);

    int insertSelective(Sys_role record);

    Sys_role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Sys_role record);

    int updateByPrimaryKey(Sys_role record);

    void addRoleRes(List<Sys_role_res> list);

    void deleteRoleRes(Integer role_id);

    /**
     * 根据角色id获取对应权限
     * @param role_id
     * @return
     */
    List<Sys_role_res> getRoleResByRole(Integer role_id);
}
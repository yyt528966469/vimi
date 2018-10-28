package com.wm.edu.mapper.sysuser;



import com.wm.edu.model.admin.Sys_user;
import com.wm.edu.model.security.Sys_role;
import com.wm.edu.model.security.Sys_role_user;

import java.util.List;

public interface SysUserMapper {

    List<Sys_user> getSysUserList(Sys_user sys_user);

    Sys_user getSysUserById(Integer user_id);

    Sys_user getSysUserByName(String username);

    void saveSysUser(Sys_user sys_user);

    void updateSysUser(Sys_user sys_user);

    void addRoleUser(Sys_role_user sys_role_user);

    void deleteRoleUser(Integer user_id);

    List<Sys_role> getRoleByUserId(Integer user_id);
}

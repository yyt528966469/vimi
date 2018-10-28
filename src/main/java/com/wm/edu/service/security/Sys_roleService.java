package com.wm.edu.service.security;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.security.Sys_roleMapper;
import com.wm.edu.model.security.Sys_role;
import com.wm.edu.model.security.Sys_role_res;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sysRoleService")
public class Sys_roleService {
    @Autowired
    private Sys_roleMapper sys_roleMapper;


    public Page<Sys_role> getSysRoleList(Sys_role sys_role ){
        Page<Sys_role> page=PageHelper.startPage(sys_role.getPage(),sys_role.getRows());
        sys_roleMapper.getSysRole(sys_role);
        return page;
    }
    /**
     * 获取所有角色
     * @return
     */
    public List<Sys_role> getSysRole(Sys_role sys_role){
        return sys_roleMapper.getSysRole(sys_role);
    }

    public List<Sys_role> getSysRoleByUserId(Integer user_id){
        return sys_roleMapper.getSysRoleByUserId(user_id);
    }

    public Integer saveSysRole(Sys_role sys_role){

        int result=200;
        try {
            if(sys_role.getId()==null){//新增
                sys_roleMapper.insertSelective(sys_role);
            }else {
                sys_roleMapper.updateByPrimaryKeySelective(sys_role);
            }
        }catch (Exception e){
            e.printStackTrace();
            result=502;
        }
        return result;

    }


    public Integer addRoleRes(List<Sys_role_res> list, Integer role_id){
        Integer result=200;
        try {
            sys_roleMapper.deleteRoleRes(role_id);
            sys_roleMapper.addRoleRes(list);
        }catch (Exception e){
            result=502;
            e.printStackTrace();
        }

        return result;
    }

    public List<Sys_role_res> getRoleResByRole(Integer role_id){
        return  sys_roleMapper.getRoleResByRole(role_id);
    }

    public Sys_role getRoleById(Integer role_id){
        return sys_roleMapper.selectByPrimaryKey(role_id);
    }

    /**
     * 删除角色，以及对应的权限
     * @param id
     * @return
     */
    @Transactional
    public Integer deleteRole(Integer id){
        Integer result=200;
        try {
            sys_roleMapper.deleteByPrimaryKey(id);

            sys_roleMapper.deleteRoleRes(id);
        }catch (Exception e){
            e.printStackTrace();
            result=502;
            throw new  RuntimeException();
        }

        return result;
    }

}

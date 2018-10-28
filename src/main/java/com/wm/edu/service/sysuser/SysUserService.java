package com.wm.edu.service.sysuser;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.sysuser.SysUserMapper;
import com.wm.edu.model.admin.Sys_user;
import com.wm.edu.model.security.Sys_role;
import com.wm.edu.model.security.Sys_role_user;
import com.wm.edu.service.security.Sys_roleService;
import com.wm.edu.utils.md5.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sysUserService")
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private Sys_roleService sys_roleService;

    /**
     * 分页查询系统用户信息
     *
     * @return
     */

    public Page<Sys_user> getSysUserList(Sys_user sys_user, Integer pageNum, Integer pageNow){
        Page<Sys_user> page=PageHelper.startPage(pageNum,pageNow);
        sysUserMapper.getSysUserList(sys_user);
        return page;
    }

    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    public Sys_user getUserById(Integer id){
        Sys_user user=sysUserMapper.getSysUserById(id);
        List<Sys_role> list=sysUserMapper.getRoleByUserId(id);
        user.setRoles(list);
        return user;
    }

    public Sys_user getUserByName(String name){
        Sys_user user=sysUserMapper.getSysUserByName(name);
        List<Sys_role> list=sysUserMapper.getRoleByUserId(user.getUser_id());
        user.setRoles(list);
        return user;
    }

    /**
     * 保存系统用户信息
     * @param user
     * @return
     */
    public String saveUserInfo(Sys_user user){
        String result="0";//默认保存成功
        String password=user.getPassword();
        if(StringUtils.isNotEmpty(password)){//进行Md5加密
            password=MD5Utils.md5Password(password);
            user.setPassword(password);
        }


        if(user.getUser_id()==null){//新增
            user.setStatus(0);//新增时默认是开启状态
            sysUserMapper.saveSysUser(user);
        }else {//编辑
            sysUserMapper.updateSysUser(user);
        }
        Sys_user sys_user=sysUserMapper.getSysUserByName(user.getUsername());
        List<Sys_role> list=user.getRoles();
        sysUserMapper.deleteRoleUser(sys_user.getUser_id());
        if(list!=null){
            for (Sys_role role:list){
                Sys_role_user sys_role_user=new Sys_role_user();
                sys_role_user.setSys_role_id(role.getId());
                sys_role_user.setSys_user_id(sys_user.getUser_id());
                sysUserMapper.addRoleUser(sys_role_user);
            }
        }

        return result;
    }

    /**
     * 更新用户状态
     * @param
     * @param
     * @return
     */

    public String updateStatus(Sys_user sys_user){
        String result="0";//默认保存成功
        try {
            sysUserMapper.updateSysUser(sys_user);
        }catch (Exception e){
            e.printStackTrace();
            result="1";
        }
        return result;
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    public Sys_user findByUsername(String username){
       return sysUserMapper.getSysUserByName(username);
    }

    /**
     * 验证该用户
     * @param sys_user
     * @return
     */
    public String checkUser(Sys_user sys_user){
        String result="0";
        Sys_user user=findByUsername(sys_user.getUsername());
        if(user!=null){
            String pass=sys_user.getPassword();//获取用户输入的密码
            pass=MD5Utils.md5Password(pass);
            String word=user.getPassword();//获取用户密码
            if(!word.equals(pass)){
                result="2";//密码错误
            }
            if(user.getStatus()==1){
                result="3";//该账号被禁用
            }
            //result
        }else {
            result="1";//该用户不存在
        }
        return result;
    }

    public List<Sys_role> getSysRole(){
        Sys_role sys_role=new Sys_role();
        return sys_roleService.getSysRole(sys_role);
    }

}

package com.wm.edu.controller.sysuser;

import com.wm.edu.model.admin.Sys_user;
import com.wm.edu.model.security.Sys_role;
import com.wm.edu.service.sysuser.SysUserService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysuser/")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/main")
    public ModelAndView main(HttpServletRequest request){

        return new ModelAndView("sysuser/adminList");
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(Sys_user sys_user, @RequestParam(value = "page", defaultValue = "0")  Integer page,
                                   @RequestParam(value = "rows", defaultValue = "20") Integer rows) throws Exception {
        Map<String,Object> map=new HashMap<>();
        Page<Sys_user> pages=sysUserService.getSysUserList(sys_user,page,rows);
        //Page<T_activit> pages=activitService.findBookCriteria(page, size,new T_activit());

       // Page<Sys_user> pages=sysUserService.findSysUserCriteria(pageable,sys_user);

       // model.put("pages",pages);
        map.put("rows", pages.getResult());//当前记录数
        map.put("records", pages.getCountColumn());
        map.put("page", page);
        map.put("total", pages.getPageNum());
        return map;
    }

    /**
     * 跳转新增OR修改页面
     * @param
     * @return
     */
    @RequestMapping("/addUser")
    public ModelAndView addUser( Map<String,Object> model){
        Sys_user sys_user=new Sys_user();
        List<Sys_role> list=sysUserService.getSysRole();
        model.put("roleList",list);
        model.put("user",sys_user);
        return new ModelAndView("sysuser/addAdmin");
    }

    @RequestMapping("/editUser")
    public ModelAndView editUser(Integer user_id, Map<String,Object> model){
        Sys_user sys_user=new Sys_user();
        if(user_id!=null){
            sys_user=sysUserService.getUserById(user_id);
        }
        List<Sys_role> list=sysUserService.getSysRole();
        model.put("roleList",list);
        model.put("user",sys_user);

        return new ModelAndView("sysuser/addAdmin");
    }

    /**
     * 保存用户信息
     * @param sys_user
     * @return
     */
    @RequestMapping("/saveUserInfo")
    @ResponseBody
    public Map<String,Object> saveUserInfo(Sys_user sys_user){
        Map<String,Object> map=new HashMap<String,Object>();

        String result=sysUserService.saveUserInfo(sys_user);
        map.put("result",result);
        return map;
    }

    /**
     * 禁用或启用
     * @param
     * @param
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public Map<String,Object> updateStatus(Sys_user sys_user){
        Map<String,Object> map=new HashMap<String,Object>();

        String result=sysUserService.updateStatus(sys_user);//sysUserService.updateStatus(user_id,status);
        map.put("result",result);
        return map;
    }

    /**
     * 新增用户时判断用户是否存在
     * @param username
     * @return
     */
    @RequestMapping("/checkUsername")
    @ResponseBody
    public Map<String,Object> checkUsername(String username){
        Map<String,Object> map=new HashMap<String,Object>();

        Sys_user sys_user=sysUserService.findByUsername(username);
        String result="0";
        if(sys_user!=null){
            result="1";//该用户已存在
        }
        map.put("result",result);
        return map;
    }


}

package com.wm.edu.controller.security;

import com.wm.edu.model.security.Sys_role;
import com.wm.edu.model.security.Sys_role_res;
import com.wm.edu.service.security.Sys_roleService;
import com.wm.edu.utils.common.ResultBody;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysrole")
public class SysRoleController {
    @Autowired
    private Sys_roleService sys_roleService;
    @RequestMapping("/main")
    public String main(){
        return "security/sysrole/roleList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(Sys_role sys_role){
        Map<String,Object> map=new HashMap<>();

        Page<Sys_role> page=sys_roleService.getSysRoleList(sys_role);
        map.put("rows", page.getResult());//当前记录数
        map.put("records", page.getTotal());
        map.put("page",page.getPageNum());
        map.put("total",  page.getPages());
        return map;
    }


    @RequestMapping("/editRole")
    public String editRole(Integer id, Model model){
        Sys_role sys_role=new Sys_role();
        if(id!=null){
            sys_role=sys_roleService.getRoleById(id);
        }
        model.addAttribute("sys_role",sys_role);
        return "security/sysrole/addRole";
    }

    /**
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/getTree")
    public String getTree(String id, Model model){
        model.addAttribute("role_id",id);
        return "security/sysrole/res_tree";
    }

    /**
     * 分配权限
     * @param ids
     * @param role_id
     * @return
     */
    @RequestMapping("/saveRoleRes")
    @ResponseBody
    public Map<String,Object> saveRoleRes(String ids,Integer role_id){
        Map<String,Object> map=new HashMap<>();
        ResultBody body=new ResultBody();
        String [] arr=ids.split(",");
        List<Sys_role_res> list=new ArrayList<>();
        if(arr!=null&&arr.length>0){
            for (String res_id:arr){
                Sys_role_res sys_role_res=new Sys_role_res();
                sys_role_res.setRes_id(Integer.parseInt(res_id));
                sys_role_res.setRole_id(role_id);
                list.add(sys_role_res);
            }
        }
        Integer code=sys_roleService.addRoleRes(list,role_id);
        body.setCode(code);
        map.put("result",body);
        return map;
    }

    /**
     * 删除角色及权限
     * @param id
     * @return
     */
    @RequestMapping("/deleteRole")
    @ResponseBody
    public ResultBody deleteRole(Integer id){
        Integer code=sys_roleService.deleteRole(id);
        ResultBody body=new ResultBody();
        body.setCode(code);
        return body;
    }
    @RequestMapping("/saveRole")
    @ResponseBody
    public ResultBody saveRole(Sys_role sys_role){
        Integer code=sys_roleService.saveSysRole(sys_role);
        ResultBody body=new ResultBody();
        body.setCode(code);
        return body;
    }

}

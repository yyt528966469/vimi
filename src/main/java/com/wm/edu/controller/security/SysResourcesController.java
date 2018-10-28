package com.wm.edu.controller.security;

import com.wm.edu.model.admin.Sys_user;
import com.wm.edu.model.security.SysResources;
import com.wm.edu.model.security.Sys_role_res;
import com.wm.edu.service.security.SysResourcesService;
import com.wm.edu.service.security.Sys_roleService;
import com.wm.edu.service.sysuser.SysUserService;
import com.wm.edu.utils.common.ResultBody;
import com.wm.edu.utils.util.MenuUtil;
import com.github.pagehelper.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/resources")
public class SysResourcesController {

    @Autowired
    private SysResourcesService sysResourcesService;

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private Sys_roleService sys_roleService;
    @RequestMapping("/main")
    public String main(){
        return "security/resources/resourcesList";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(SysResources resources){
        Map<String,Object> map=new HashMap<>();

        Page<SysResources> page=sysResourcesService.getResList(resources);
        map.put("rows", page.getResult());//当前记录数
        map.put("records", page.getTotal());
        map.put("page",page.getPageNum());
        map.put("total",  page.getPages());
        return map;
    }
    @RequestMapping("/addRes")
    public String addRes(Integer res_id, Model model){
        SysResources sysResources=new SysResources();

        SysResources resources=new SysResources();
        resources.setRes_type(0);
        List<SysResources> list=sysResourcesService.getParentRes(resources);
        model.addAttribute("sysResources",sysResources);
        model.addAttribute("resList",list);
        return "security/resources/addResources";
    }

    @RequestMapping("/editRes")
    public String editRes(Integer res_id, Model model){
        SysResources sysResources=new SysResources();
        if(res_id!=null){
            sysResources=sysResourcesService.getResInfoById(res_id);
        }
        SysResources resources=new SysResources();
        resources.setRes_type(0);
        List<SysResources> list=sysResourcesService.getParentRes(resources);
        model.addAttribute("sysResources",sysResources);
        model.addAttribute("resList",list);
        return "security/resources/addResources";
    }
    @RequestMapping("/saveResInfo")
    @ResponseBody
    public ResultBody saveResInfo(SysResources resources){
        ResultBody res=sysResourcesService.saveResInfo(resources);

        return res;
    }

    /**
     * 获取登录用户菜单
     *
     * @param
     * @return
     */
    @RequestMapping(produces = "text/plain;charset=UTF-8", value = "getMenuHtml")
    @ResponseBody
    public String getMenuHtml(HttpServletRequest request,
                              HttpServletResponse response) {
        String username=(String) (request.getSession().getAttribute("ACTIVITY_WEB_LAST_USERNAME"));
        Sys_user sys_user=sysUserService.getUserByName(username);
        String menuStr = MenuUtil.getSysResourcesHtml(request,sys_user);
        return menuStr;
    }
    @RequestMapping("/loadDataJsTree")
    @ResponseBody
    public List<JSONObject> loadDataJsTree(HttpServletRequest request,Integer role_id) {
        List<JSONObject> list=new ArrayList<>();
        List<Sys_role_res> roleResList=sys_roleService.getRoleResByRole(role_id);
        SysResources sysResources=new SysResources();
        sysResources.setParent_id(0);
        List<SysResources> resList=sysResourcesService.getResListByBean(sysResources);
        Iterator<SysResources> iterable= resList.iterator();
        while (iterable.hasNext()){
            SysResources resources = iterable.next();
            list.add(getResourceJosn(resources,roleResList));
        }
        return list;
    }

    public JSONObject getResourceJosn(SysResources resources,List<Sys_role_res> roleResList) {
        JSONObject json = new JSONObject();
        json.put("id", resources.getRes_id());
        json.put("name", resources.getName());
        if(roleResList!=null){
            for(Sys_role_res res:roleResList){
                if(res.getRes_id()==resources.getRes_id()){
                    json.put("checked", true);
                }
            }
        }
        if (resources.getChildren() != null
                && resources.getChildren().size() > 0) {
            json.put("open", true);
            JSONArray jsonArray = new JSONArray();

            for (SysResources res : resources.getChildren()) {
                jsonArray.add(getResourceJosn(res,roleResList));
            }
            json.put("children", jsonArray);
        } else {
            json.put("open", true);
        }
        return json;
    }

    @RequestMapping("/getRes")
    @ResponseBody
    public Map<String,Object> getRes(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        //Object o=new Object();
        List<SysResources> list=(List<SysResources> )request.getSession().getAttribute("USER_RES");
        map.put("resList",list);
        return map;
    }
    @RequestMapping("/deleteRes")
    @ResponseBody
    public ResultBody deleteRes(Integer id){
        ResultBody body=new ResultBody();
        Integer code=sysResourcesService.deleteRes(id);
        body.setCode(code);
        return body;
    }

}

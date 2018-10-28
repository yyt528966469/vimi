package com.wm.edu.controller.cuser;

import com.github.pagehelper.Page;
import com.wm.edu.model.commodity.T_commodity_type;
import com.wm.edu.model.cuser.C_user_info;
import com.wm.edu.model.order.T_order;
import com.wm.edu.service.cuser.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/cuser")
@Controller
public class CuserController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/main")
    public String main(){

        return "cuser/cuserList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(C_user_info c_user_info){
        Map<String,Object> map=new HashMap<>();
        Page<C_user_info> page=userInfoService.getUserPage(c_user_info);
        map.put("rows", page.getResult());//当前记录数
        map.put("records", page.getTotal());
        map.put("page",page.getPageNum());
        map.put("total",  page.getPages());
        return map;
    }


}

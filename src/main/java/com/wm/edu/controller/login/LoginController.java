package com.wm.edu.controller.login;


import com.wm.edu.model.admin.Sys_user;
import com.wm.edu.model.order.OrderReport;
import com.wm.edu.model.security.SysResources;
import com.wm.edu.service.cuser.UserInfoService;
import com.wm.edu.service.order.OrderService;
import com.wm.edu.service.security.SysResourcesService;
import com.wm.edu.service.security.Sys_roleService;
import com.wm.edu.service.sysuser.SysUserService;
import com.wm.edu.utils.util.MenuUtil;
import com.wm.edu.utils.util.Prient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class LoginController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysResourcesService sysResourcesService;
    @Autowired
    private Sys_roleService sys_roleService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserInfoService userInfoService;
    @RequestMapping("/login")
    public String login(HttpServletRequest request){
        //Prient.prient();
        //newsService.addNews();
        //newsService.getNewsById("DMFQVGPU00018AOP");
        //newsService.getNewsList(0,10);
        return "login";
    }

   /* @RequestMapping("/")
    public String index() {
        return "index";
    }*/

    @RequestMapping("/checkUser")
    @ResponseBody
    public Map<String,Object> checkUser(Sys_user sys_user, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        String result=sysUserService.checkUser(sys_user);
        if("0".equals(result)){
            request.getSession().setAttribute("ACTIVITY_WEB_LAST_USERNAME",sys_user.getUsername());
            //request.getSession().setMaxInactiveInterval(30*60);
        }
        map.put("result",result);
        return map;
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        String useranme=(String) request.getSession().getAttribute("ACTIVITY_WEB_LAST_USERNAME");
        Sys_user sys_user=sysUserService.findByUsername(useranme);
        MenuUtil.init(sysResourcesService,sys_roleService);
        List<SysResources> list=MenuUtil.getRes(request,sys_user);
        request.setAttribute("list",list);
        request.setAttribute("sys_user",sys_user);
        return "index";
    }
    @RequestMapping("/echats")
    public String echats(Model model){
        Integer user_num=userInfoService.getAllUserNum();
        Integer today_order=orderService.getToDayOrderNum();
        model.addAttribute("user_num",user_num);
        model.addAttribute("today_order",today_order);
        return "index/index";
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        request.getSession().removeAttribute("ACTIVITY_WEB_LAST_USERNAME");
       return new ModelAndView("login");
    }

    @RequestMapping("/getInfo")
    @ResponseBody
    public Map<String,Object> getInfo(){
        Map<String,Object> map=new HashMap<>();
        List<OrderReport> list=orderService.getWeekOrderNum();
        List<Integer> orderList=new ArrayList<>();
        List<Float> priceList=new ArrayList<>();
        for(OrderReport m:list){
            orderList.add(m.getOrder_num());
            priceList.add(m.getPrice_sum());
        }
        map.put("weekList",getWeek());
        map.put("orderNumList",orderList);
        map.put("priceList",priceList);
        return map;
    }
    private List<String> getWeek(){
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar=Calendar.getInstance();
        int w=calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println(w);
        List<String> list=new ArrayList<>();
        for (int i=0;i<7;i++){
            list.add(weekDays[(w+i)%7]);
        }
        System.out.println(list);
        return list;
    }

}

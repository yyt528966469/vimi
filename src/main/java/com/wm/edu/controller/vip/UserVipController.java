package com.wm.edu.controller.vip;

import com.github.pagehelper.Page;
import com.wm.edu.model.vip.C_user_vip;
import com.wm.edu.model.vip.T_recharge;
import com.wm.edu.service.vip.RechargeService;
import com.wm.edu.service.vip.UserVipService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/uservip")
@Controller
public class UserVipController {

    @Autowired
    private UserVipService userVipService;
    @Autowired
    private RechargeService rechargeService;
    @RequestMapping("/main")
    public String main(){
        return "uservip/uservipList";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(C_user_vip c_user_vip){
        Map<String,Object> map=new HashMap<>();
        Page<C_user_vip> page=userVipService.getVipPage(c_user_vip);
        map.put("rows", page.getResult());//当前记录数
        map.put("records", page.getTotal());
        map.put("page",page.getPageNum());
        map.put("total",  page.getPages());
        return map;
    }

    /**
     *
     * @param t_recharge
     * @param model
     * @return
     */
    @RequestMapping("/getRecgargeList")
    public String getRecgargeList(T_recharge t_recharge, Model model){
        List<T_recharge> list=rechargeService.getRechargePage(t_recharge);
        model.addAttribute("rechargeList",list);
        return "uservip/recharge/rechargeList";
    }
    @RequestMapping("/addOrEditRecgarge")
    public String addOrEditRecgarge(String id,Model model){
        T_recharge t_recharge=new T_recharge();
        if(StringUtils.isNotEmpty(id)){
            t_recharge=rechargeService.getRechargeById(id);
        }
        model.addAttribute("t_recharge",t_recharge);
        return "uservip/recharge/addRecharge";
    }

    /**
     *
     * @param t_recharge
     * @return
     */
    @RequestMapping("/saveRecharge")
    @ResponseBody
    public Map<String,Object> saveRecharge(T_recharge t_recharge){
        Map<String,Object> map=new HashMap<>();
        String res=rechargeService.saveRecharge(t_recharge);
        map.put("result",res);
        return map;
    }
    @RequestMapping("/deleteRecharge")
    @ResponseBody
    public Map<String,Object> deleteRecharge(String id){
        Map<String,Object> map=new HashMap<>();
        String res=rechargeService.deleteRecharge(id);
        map.put("result",res);
        return map;
    }
}

package com.wm.edu.controller.coupons;

import com.github.pagehelper.Page;
import com.wm.edu.model.coupons.T_coupons;
import com.wm.edu.service.coupons.CouponsService;
import com.wm.edu.utils.common.AttaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/coupons")
@Controller
public class CouponsController {

    @Autowired
    private CouponsService couponsService;

    @RequestMapping("/main")
    public String main(){
        return "coupons/couponsList";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(T_coupons t_coupons){
        Map<String,Object> map=new HashMap<>();
        Page<T_coupons> page=couponsService.getCouponsPage(t_coupons);
        map.put("rows", page.getResult());//当前记录数
        map.put("records", page.getTotal());
        map.put("page",page.getPageNum());
        map.put("total",  page.getPages());
        return map;
    }

    @RequestMapping("/toAddorEdit")
    public String toAddorEdit(String coupons_id, Model model){
        T_coupons t_coupons=new T_coupons();
        if(StringUtils.isNotEmpty(coupons_id)){
            t_coupons= couponsService.getCouponsById(coupons_id);
        }
        model.addAttribute("t_coupons",t_coupons);
        return "coupons/addCoupons";
    }
    @RequestMapping("/saveCoupons")
    @ResponseBody
    public Map<String,Object> saveCoupons(T_coupons t_coupons){
        Map<String,Object> map=new HashMap<>();

        String result=couponsService.saveCoupons(t_coupons);
        map.put("result",result);
        return map;
    }
    @RequestMapping("/deleteCoupons")
    @ResponseBody
    public Map<String,Object> deleteCoupons(String coupons_id){
        Map<String,Object> map=new HashMap<>();
        String result=couponsService.deleteCoupons(coupons_id);
        map.put("result",result);
        return map;
    }
    @RequestMapping("/getUrl")
    public String getUrl(String coupons_id,Model model){
        model.addAttribute("coupons_id",coupons_id);
        return "coupons/couponsUrl";
    }
}

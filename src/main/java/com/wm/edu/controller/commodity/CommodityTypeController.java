package com.wm.edu.controller.commodity;

import com.github.pagehelper.Page;
import com.wm.edu.mapper.commodity.T_commodity_typeMapper;
import com.wm.edu.model.commodity.T_commodity_type;
import com.wm.edu.service.commodity.CommodityTypeService;
import com.wm.edu.utils.common.AttaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/commoditytype")
public class CommodityTypeController {

    @Autowired
    private CommodityTypeService commodityTypeService;
    @RequestMapping("/main")
    public String main(){

        return "commoditytype/comTypeList";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(T_commodity_type t_commodity_type){
        Map<String,Object> map=new HashMap<>();
        Page<T_commodity_type> page=commodityTypeService.getTypeList(t_commodity_type);
        map.put("rows", page.getResult());//当前记录数
        map.put("records", page.getTotal());
        map.put("page",page.getPageNum());
        map.put("total",  page.getPages());
        return map;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/toAddOrEdit")
    public String toAddOrEdit(String type_id, Model model){
        T_commodity_type commodity_type=new T_commodity_type();
        if (StringUtils.isNotEmpty(type_id)){
            commodity_type=commodityTypeService.getTypeById(type_id);
        }
        model.addAttribute("commodity_type",commodity_type);
        return "commoditytype/addComType";
    }

    @RequestMapping("/saveType")
    @ResponseBody
    public Map<String,Object> saveType(T_commodity_type t_commodity_type, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        t_commodity_type.setImg(AttaUtils.getAllUrl(request,t_commodity_type.getImg()));
        String result=commodityTypeService.saveType(t_commodity_type);
        map.put("result",result);
        return map;
    }
    @RequestMapping("/deleteType")
    @ResponseBody
    public Map<String,Object> deleteType(String type_id){
        Map<String,Object> map=new HashMap<>();
        String result=commodityTypeService.deleteType(type_id);
        map.put("result",result);
        return map;
    }


}

package com.wm.edu.controller.commodity;

import com.github.pagehelper.Page;
import com.wm.edu.model.commodity.T_commodity_info;
import com.wm.edu.model.commodity.T_commodity_type;
import com.wm.edu.service.commodity.CommodityInfoService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.util.ExportExcelUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/commodity")
public class CommodityInfoController {

    @Autowired
    private CommodityInfoService commodityInfoService;

    @RequestMapping("/main")
    public String main(Model model){
        List<T_commodity_type> list=commodityInfoService.getAllType();
        model.addAttribute("typeList",list);
        return "commodity/commodityInfoList";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(T_commodity_info t_commodity_info){
        Map<String,Object> map=new HashMap<>();
        Page<T_commodity_info> page=commodityInfoService.getCommodityList(t_commodity_info);
        map.put("rows", page.getResult());//当前记录数
        map.put("records", page.getTotal());
        map.put("page",page.getPageNum());
        map.put("total",  page.getPages());
        return map;
    }
    @RequestMapping("/toAddOrEdit")
    public String toAddOrEdit(String commodity_id,Model model){
        T_commodity_info commodity_info=new T_commodity_info();
        if(StringUtils.isNotEmpty(commodity_id)){
            commodity_info = commodityInfoService.getCommById(commodity_id);
        }
        List<T_commodity_type> list=commodityInfoService.getAllType();
        model.addAttribute("commodity_info",commodity_info);
        model.addAttribute("typeList",list);
        return "commodity/addCommodityInfo";
    }

    @RequestMapping("/saveCommodity")
    @ResponseBody
    public Map<String,Object> saveCommodity(T_commodity_info t_commodity_info,HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        t_commodity_info.setImg_src(AttaUtils.getAllUrl(request,t_commodity_info.getImg_src()));
        String[] head_name=request.getParameterValues("head_name");
        String[] text_name=request.getParameterValues("text_name");
        String result=commodityInfoService.saveCommInfo(t_commodity_info,head_name,text_name,request);
        map.put("result",result);
        return map;
    }

    @RequestMapping("/uploadImages")
    @ResponseBody
    public Map<String, Object> uploadImages(HttpServletRequest request, HttpServletResponse response, @RequestParam("image_file") MultipartFile image_file){
        return ExportExcelUtils.uploadFile(request,image_file);
    }

    @RequestMapping("/uploadHead")
    @ResponseBody
    public Map<String, Object> uploadHead(HttpServletRequest request, HttpServletResponse response, @RequestParam("photo_head") MultipartFile photo_head){
        return ExportExcelUtils.uploadFile(request,photo_head);
    }

    @RequestMapping("/uploadText")
    @ResponseBody
    public Map<String, Object> uploadText(HttpServletRequest request, HttpServletResponse response, @RequestParam("photo_text") MultipartFile photo_text){
        return ExportExcelUtils.uploadFile(request,photo_text);
    }

    /**
     * 删除商品
     * @return
     */
    @RequestMapping("/deleteCommodity")
    @ResponseBody
    public Map<String,Object> deleteCommodity(String commodity_id){
        Map<String,Object> map=new HashMap<>();
        String result=commodityInfoService.deleteCommodity(commodity_id);
        map.put("result",result);
        return map;
    }

    /**
     * 更新状态
     * @param commodity_id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public Map<String,Object> updateStatus(String commodity_id,Integer status){
        Map<String,Object> map=new HashMap<>();
        T_commodity_info commodity_info=new T_commodity_info();
        commodity_info.setCommodity_id(commodity_id);
        commodity_info.setStatus(status);
        String result=commodityInfoService.updateStatus(commodity_info);
        map.put("result",result);
        return map;
    }
    @RequestMapping("/getUrl")
    public String getUrl(String commodity_id,Model model){
        model.addAttribute("commodity_id",commodity_id);
        return "commodity/commodityUrl";
    }
}

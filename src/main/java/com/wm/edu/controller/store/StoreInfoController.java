package com.wm.edu.controller.store;

import com.wm.edu.model.store.T_base;
import com.wm.edu.model.store.T_store_info;
import com.wm.edu.service.store.StoreInfoService;
import com.wm.edu.service.store.TBaseService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.util.ExportExcelUtils;
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
import java.util.Map;

@Controller
@RequestMapping("/store")
public class StoreInfoController {
    @Autowired
    private StoreInfoService storeInfoService;
    @Autowired
    private TBaseService tBaseService;

    @RequestMapping("/main")
    public String main(Model model){
        T_store_info store_info=storeInfoService.getStoreInfoById("180823141815751957");
        if(store_info==null){
            store_info=new T_store_info();
            model.addAttribute("store_info",store_info);
            model.addAttribute("t_base",new T_base());
            return "store/editStore";
        }else {
            model.addAttribute("store_info",store_info);
            T_base t_base=tBaseService.getBaseById(store_info.getStore_id());
            model.addAttribute("t_base",t_base);
            return "store/storeInfo";
        }


    }
    @RequestMapping("/getStoreEdit")
    public String getStoreEdit(Model model){

        T_store_info store_info=storeInfoService.getStoreInfoById("180823141815751957");
        T_base t_base=tBaseService.getBaseById(store_info.getStore_id());
        model.addAttribute("store_info",store_info);
        model.addAttribute("t_base",t_base);
        return "store/editStore";
    }

    /**
     * 保存商店信息
     * @param t_store_info
     * @return
     */
    @RequestMapping("/saveStoreInfo")
    @ResponseBody
    public Map<String,Object> saveStoreInfo(T_store_info t_store_info,HttpServletRequest request,T_base t_base){
        t_store_info.setImg_src(AttaUtils.getAllUrl(request,t_store_info.getImg_src()));
        Map<String,Object> map=new HashMap<>();
        String result=storeInfoService.saveStoreInfo(t_store_info,t_base);
        map.put("result",result);
        return map;
    }

    @RequestMapping("/toMap")
    public String toMap(String lng,String lat,Model model){
        model.addAttribute("lng",lng);
        model.addAttribute("lat",lat);
        return "store/baiduMap";
    }

    @RequestMapping("/uploadImages")
    @ResponseBody
    public Map<String, Object> uploadImages(HttpServletRequest request, HttpServletResponse response, @RequestParam("image_file") MultipartFile image_file){
        return ExportExcelUtils.uploadFile(request,image_file);
    }


}

package com.wm.edu.controller.adver;

import com.github.pagehelper.Page;
import com.wm.edu.model.adver.T_adver;
import com.wm.edu.service.adver.AdverService;
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
import java.util.Map;

@Controller
@RequestMapping("/tadver")
public class AdverController {

    @Autowired
    private AdverService adverService;
    @RequestMapping("/main")
    public String main(){
        return "adver/adverList";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(T_adver t_adver){
        Map<String,Object> map=new HashMap<>();
        Page<T_adver> page=adverService.getAdverList(t_adver);
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
    public String toAddOrEdit(String adver_id, Model model){
        T_adver t_adver=new T_adver();
        if(StringUtils.isNotEmpty(adver_id)){
            t_adver=adverService.getAdverById(adver_id);
        }
        model.addAttribute("t_adver",t_adver);
        return "adver/addAdver";
    }

    @RequestMapping("/uploadImages")
    @ResponseBody
    public Map<String, Object> uploadImages(HttpServletRequest request, HttpServletResponse response, @RequestParam("image_file") MultipartFile image_file){
        return ExportExcelUtils.uploadFile(request,image_file);
    }

    @RequestMapping("/saveAdver")
    @ResponseBody
    public Map<String,Object> saveAdver(T_adver t_adver,HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        t_adver.setAdver_img(AttaUtils.getAllUrl(request,t_adver.getAdver_img()));
        String result=adverService.saveAdver(t_adver);
        map.put("result",result);
        return map;
    }
    @RequestMapping("/updateStatus")
    @ResponseBody
    public Map<String,Object> updateStatus(String adver_id,Integer status){
        Map<String,Object> map=new HashMap<>();
        T_adver t_adver=new T_adver();
        t_adver.setAdver_id(adver_id);
        t_adver.setStatus(status);
        String result=adverService.updateStatus(t_adver);
        map.put("result",result);
        return map;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/deleteAdver")
    @ResponseBody
    public Map<String,Object> deleteAdver(String adver_id){
        Map<String,Object> map=new HashMap<>();
        String result=adverService.deleteAdver(adver_id);
        map.put("result",result);
        return map;
    }

}

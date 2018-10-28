package com.wm.edu.controller.reserve;

import com.aliyuncs.exceptions.ClientException;
import com.github.pagehelper.Page;
import com.wm.edu.model.reserve.ReserveEnum;
import com.wm.edu.model.reserve.T_reserve;
import com.wm.edu.model.store.T_store_info;
import com.wm.edu.service.reserve.ReserveService;
import com.wm.edu.service.store.StoreInfoService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.http.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reserve")
public class ReserveController {


    @Autowired
    private ReserveService reserveService;

    @Autowired
    private StoreInfoService storeInfoService;
    @RequestMapping("/main")
    public String main(Model model){
        ReserveEnum[] reserveEnums=ReserveEnum.values();
        model.addAttribute("reserveEnums",reserveEnums);
        return "reserve/reserveList";
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(T_reserve t_reserve){
        Map<String,Object> map=new HashMap<>();
        Page<T_reserve> page=reserveService.getReservePage(t_reserve);
        map.put("rows", page.getResult());//当前记录数
        map.put("records", page.getTotal());
        map.put("page",page.getPageNum());
        map.put("total",  page.getPages());
        return map;
    }

    /**
     * 查看详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/toDetails")
    public String toDetails(String id,Model model){
        T_reserve t_reserve=reserveService.getReserveById(id);
        model.addAttribute("t_reserve",t_reserve);
        return "reserve/reserveDetails";
    }

    /**
     *
     * @param t_reserve
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public Map<String,Object> updateStatus(T_reserve t_reserve){
        Map<String,Object> map=new HashMap<>();
        String store_id=AttaUtils.getConfigValueByKey("store_id");
        Integer code=reserveService.updateStatus(t_reserve);
        if(code==200){
            if(t_reserve.getStatus()==1){
                T_reserve reserve=reserveService.getReserveById(t_reserve.getId());
                T_store_info t_store_info=storeInfoService.getStoreInfoById(store_id);
                String info=reserve.getR_date()+" "+reserve.getR_time()+","+reserve.getPerson_num()+"人，"+reserve.getPosition();
                String json="{\"storename\":\""+t_store_info.getStore_name()+"\",\"info\":\""+info+"\",\"num\":\"10\",\"phone\":\""+t_store_info.getPhone()+"\"}";
                try {
                    SmsUtil.sendSms(reserve.getPhone(),json,"SMS_148610711");
                } catch (ClientException e) {
                    e.printStackTrace();
                }
            }

        }

        map.put("code",code);
        return map;
    }

}

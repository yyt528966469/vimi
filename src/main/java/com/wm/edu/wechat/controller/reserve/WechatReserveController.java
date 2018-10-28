package com.wm.edu.wechat.controller.reserve;

import com.github.pagehelper.Page;
import com.wm.edu.model.reserve.T_reserve;
import com.wm.edu.service.reserve.ReserveService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.common.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/wechat/reserve")
public class WechatReserveController {

    @Autowired
    private ReserveService reserveService;

    /**
     * 获取预约日期
     *"今天-10.18","明天-10.19","后天-10.20","周日-10.21","周一-10.22","周二-10.23","周三-10.24"
     * @return
     */
    @RequestMapping("/getData")
    public ResultBody getData(){
        ResultBody body=new ResultBody();
        SimpleDateFormat sdf=new SimpleDateFormat("MM.dd");
        String[] arr=new String[7];
        Calendar cal=Calendar.getInstance();
        for (int i=0;i<arr.length;i++){
            arr[i]=sdf.format(cal.getTime());
            cal.add(Calendar.DATE,1);
        }
        /*arr[0]="今天-"+arr[0];
        arr[1]="明天-"+arr[1];
        arr[2]="后天-"+arr[2];*/
        /*List<String> list=getWeek();
        if(list!=null){
            for (int i=0,j=list.size()-3;i<j;i++){
                arr[i+3]=list.get(i+3)+"-"+arr[i+3];
            }
        }*/
        body.setData(arr);
        return body;
    }

    private List<String> getWeek(){
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar calendar=Calendar.getInstance();
        int w=calendar.get(Calendar.DAY_OF_WEEK);
        w--;
        System.out.println(w);
        List<String> list=new ArrayList<>();
        for (int i=0;i<7;i++){
            list.add(weekDays[(w+i)%7]);
        }
        System.out.println(list);
        return list;
    }

    /**
     * 去预约
     * @param t_reserve
     * @return
     */
    @RequestMapping("/toReserve")
    public ResultBody toReserve(T_reserve t_reserve){
        ResultBody body=new ResultBody();
        Map<String,Object> map=new HashMap<>();
        t_reserve.setId(AttaUtils.getOrderId());
        t_reserve.setCreate_time(new Date());
        t_reserve.setStatus(0);
        Integer code=reserveService.insertReserve(t_reserve);
        body.setCode(code);
        map.put("id",t_reserve.getId());
        body.setData(map);
        return body;
    }

    /**
     * 预约单详情
     * @param id
     * @return
     */
    @RequestMapping("/getReserveById")
    public ResultBody getReserveById(String id){
        ResultBody body=new ResultBody();
        T_reserve t_reserve=reserveService.getReserveById(id);
        body.setData(t_reserve);
        return body;
    }

    /**
     * 获取预约单列表
     * @param c_user_id
     * @return
     */
    @RequestMapping("/getReserveList")
    public ResultBody getReserveList(String c_user_id){
        ResultBody body=new ResultBody();

        List<T_reserve> list=reserveService.getUserReserveList(c_user_id);
        body.setData(list);
        return body;
    }

    /**
     * 用户取消预约单
     * @param t_reserve
     * @return
     */
    @RequestMapping("/cancelReserve")
    public ResultBody cancelReserve(T_reserve t_reserve){
        ResultBody body=new ResultBody();
        Integer code=reserveService.updateStatus(t_reserve);
        body.setCode(code);
        return body;
    }
}

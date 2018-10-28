package com.wm.edu.wechat.controller.order;

import com.github.pagehelper.Page;
import com.wm.edu.model.order.T_order;
import com.wm.edu.model.order.T_order_express;
import com.wm.edu.model.order.T_order_scavenging;
import com.wm.edu.model.store.T_base;
import com.wm.edu.model.store.T_store_info;
import com.wm.edu.service.order.OrderService;
import com.wm.edu.service.shop.ShoppingCartService;
import com.wm.edu.service.store.TBaseService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.common.ResultBody;
import com.wm.edu.utils.cookie.CookieUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/wechat/order")
public class WechatOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private TBaseService tBaseService;


    /**
     * 获取订单列表
     * @param t_order
     * @return
     */
    @RequestMapping("/getOrderList")
    public ResultBody getOrderList(T_order t_order,HttpServletRequest request){
        ResultBody body=new ResultBody();

        /*Cookie cookie=CookieUtil.getCookie(request,"c_user_id");
        if(cookie!=null&&StringUtils.isNotEmpty(cookie.getValue())){
            c_user_id=cookie.getValue();
        }
        System.out.println("c_user_id="+c_user_id);*/
        //t_order.setC_user_id(c_user_id);
        t_order.setSidx("order_time");
        t_order.setSord("desc");
        Page<T_order> page=orderService.getUserOrderList(t_order);
        body.setData(page.getResult());
        return body;
    }

    /**
     * 获取订单详情
     * @param order_id
     * @return
     */
    @RequestMapping("/getOrderDetails")
    public ResultBody getOrderDetails(String order_id){
        ResultBody body=new ResultBody();
        Map<String,Object> map=new HashMap<>();
        T_order t_order=orderService.getOrderById(order_id);
        map.put("t_order",t_order);
        if(t_order!=null){
            //if(t_order.getEat_type()==1){
                String store_id=AttaUtils.getConfigValueByKey("store_id");
                T_store_info t_store_info=orderService.getStoreInfoById(store_id);
                map.put("t_store_info",t_store_info);
           // }
        }
        body.setData(map);
        return body;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/getSettlementInfo")
    public ResultBody getSettlementInfo(HttpServletRequest request,String c_user_id){
        ResultBody body=new ResultBody();
        //CookieUtil.getCookie(request,"user_id");
        //String c_user_id="123";
        String store_id=AttaUtils.getConfigValueByKey("store_id");
        Map<String,Object> map=orderService.getSettlementInfo(c_user_id,store_id);
        body.setData(map);
        return body;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/toOrder")
    public ResultBody toOrder(T_order t_order,HttpServletRequest request){
        ResultBody body=new ResultBody();
        //t_order.setOrder_type(0);
        //String c_user_id="123";
        String store_id=AttaUtils.getConfigValueByKey("store_id");
        //t_order.setC_user_id(c_user_id);
        t_order.setStore_id(store_id);
        t_order.setOrder_id(AttaUtils.getOrderId());
        Integer code=200;
        String time=request.getParameter("time");
        String date=request.getParameter("date");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        if("0".equals(date)){

        }else if ("1".equals(date)){
            calendar.add(Calendar.DATE,1);
        }else {
            calendar.add(Calendar.DATE,2);
        }
        String datetime=sdf.format(calendar.getTime())+" "+time;
        Date d=AttaUtils.formatToDate(datetime,"yyyy-MM-dd HH:mm");
        if(t_order.getEat_type()==0){
            String address_id=request.getParameter("address_id");
            code=orderService.toOrder(t_order,address_id,d);
        }else if(t_order.getEat_type()==1){
            String phone=request.getParameter("phone");

            code=orderService.toDdOrder(t_order,phone,d);
        }
        body.setCode(code);
        Map<String,Object> map=new HashMap<>();
        map.put("order_id",t_order.getOrder_id());
        body.setData(map);
        return body;
    }

    /**
     * 店内点餐下单
     * @param t_order
     * @param request
     * @return
     */
    @RequestMapping("/toDnOrder")
    public ResultBody toDnOrder(T_order t_order,HttpServletRequest request){
        ResultBody body=new ResultBody();
        //t_order.setOrder_type(0);
        //String
        //c_user_id="123";
        String store_id=AttaUtils.getConfigValueByKey("store_id");
        //t_order.setC_user_id(c_user_id);
        t_order.setStore_id(store_id);
        t_order.setOrder_id(AttaUtils.getOrderId());
        Integer code=200;
        if(t_order.getEat_type()==2){
            String table_num=request.getParameter("table_num");
            String person_num=request.getParameter("person_num");
            if(StringUtils.isNotEmpty(table_num)&&StringUtils.isNotEmpty(person_num)){
                T_order_scavenging scavenging=new T_order_scavenging();
                scavenging.setPerson_num(Integer.parseInt(person_num));
                scavenging.setTable_num(Integer.parseInt(table_num));
                code=orderService.toDnOrder(t_order,scavenging);
            }
        }
        body.setCode(code);
        Map<String,Object> map=new HashMap<>();
        map.put("order_id",t_order.getOrder_id());
        body.setData(map);
        return body;
    }


    @RequestMapping("/toShopOrder")
    public ResultBody toShopOrder(T_order t_order,HttpServletRequest request){
        ResultBody body=new ResultBody();
        //t_order.setOrder_type(1);
        t_order.setEat_type(3);
        String store_id=AttaUtils.getConfigValueByKey("store_id");
        //t_order.setC_user_id(c_user_id);
        t_order.setStore_id(store_id);
        t_order.setOrder_id(AttaUtils.getOrderId());
        String address_id=request.getParameter("address_id");
        String coupons_id=request.getParameter("coupons_id");
        Integer code=orderService.toShopOrder(t_order,address_id,coupons_id);
        body.setCode(code);
        Map<String,Object> map=new HashMap<>();
        map.put("order_id",t_order.getOrder_id());
        body.setData(map);
        return body;
    }


    /**
     * 确认订单
     * @return
     */
    @RequestMapping("/confirmOrder")
    public ResultBody confirmOrder(String c_user_id){
        ResultBody body=new ResultBody();
        //String user_id="123";
        String store_id=AttaUtils.getConfigValueByKey("store_id");
        Map<String,Object> map=orderService.confirmOrder(c_user_id,store_id);
        ResultBody resultBody=getTime(0);
        List<String> list=(List<String>) resultBody.getData();
        if(list!=null&&list.size()>0){
            map.put("mr_time",list.get(0));
        }
        body.setData(map);
        return body;
    }


    @RequestMapping("/confirmShopOrder")
    public ResultBody confirmShopOrder(String c_user_id){
        ResultBody body=new ResultBody();
        //String user_id="123";
        //String store_id=AttaUtils.getConfigValueByKey("store_id");
        Map<String,Object> map=orderService.confirmShopOrder(c_user_id);
        ResultBody resultBody=getTime(0);
        List<String> list=(List<String>) resultBody.getData();
        if(list!=null&&list.size()>0){
            map.put("mr_time",list.get(0));
        }
        body.setData(map);
        return body;
    }

    /**
     * 到店取餐
     * @param c_user_id
     * @return
     */
    @RequestMapping("/ddConfirmOrder")
    public ResultBody ddconfirmOrder(String c_user_id){
        ResultBody body=new ResultBody();
        //String user_id="123";
        //c_user_id="123";
        String store_id=AttaUtils.getConfigValueByKey("store_id");
        Map<String,Object> map=orderService.ddconfirmOrder(c_user_id,store_id);
        ResultBody resultBody=getTime(0);
        List<String> list=(List<String>) resultBody.getData();
        if(list!=null&&list.size()>0){
            map.put("mr_time",list.get(0));
        }
        body.setData(map);
        return body;
    }

    /**
     *修改订单
     * @param order_id
     * @param status
     * @return
     */
    @RequestMapping("/updateStatus")
    public ResultBody updateStatus(String order_id,Integer status){
        ResultBody body=new ResultBody();
        T_order t_order=new T_order();
        t_order.setOrder_id(order_id);
        t_order.setStatus(status);
        orderService.updateStatus(t_order);
        return body;
    }

    @RequestMapping("/getDate")
    public ResultBody getDate(){
        ResultBody body=new ResultBody();
        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
        String[] arr={"今天","明天","后天"};
        Calendar cal   =   Calendar.getInstance();
        String day_1=sdf.format(cal.getTime());
        //System.out.println(day_1);
        cal.add(Calendar.DATE,1);
        String day_2=sdf.format(cal.getTime());
        //System.out.println(day_2);
        cal.add(Calendar.DATE,1);
        String day_3=sdf.format(cal.getTime());
        ///System.out.println(day_3);
        arr[0]=arr[0]+"("+day_1+")";
        arr[1]=arr[1]+"("+day_2+")";
        arr[2]=arr[2]+"("+day_3+")";
        body.setData(arr);
        return body;
    }

    @RequestMapping("/getTime")
    public ResultBody getTime(Integer date){
        ResultBody body=new ResultBody();
        List<String> list=new ArrayList<>();
        String store_id=AttaUtils.getConfigValueByKey("store_id");
        T_store_info t_store_info=orderService.getStoreInfoById(store_id);
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        String start="09:00";
        String end="21:00";
        if(t_store_info!=null){
            start=t_store_info.getStart_str();
            end=t_store_info.getEnd_str();
        }
        Calendar cal=Calendar.getInstance();
        try {
            long aaa=sdf.parse(start).getTime();
            String time=sdf.format(cal.getTime());
            long s=sdf.parse(time).getTime();
            if(date==0&&aaa<s){
                    long e = sdf.parse(end).getTime();
                    if(e>s){
                        long count=(e-s)/(30*60*1000);

                        String[] arr=time.split(":");
                        Integer min=Integer.parseInt(arr[1]);
                        if(min<=30){
                            cal.add(Calendar.MINUTE,60-min);
                        }else {
                            cal.add(Calendar.MINUTE,90-min);
                        }
                        list.add(sdf.format(cal.getTime()));
                        //System.out.println(count);
                        for (int i=2;i<count;i++){
                            cal.add(Calendar.MINUTE,30);
                            list.add(sdf.format(cal.getTime()));
                        }
                    }



            }else {
                //Calendar cal=Calendar.getInstance();

                    cal.setTime(sdf.parse(start));
                    long star=sdf.parse(start).getTime();
                    long e=sdf.parse(end).getTime();
                    long count=(e-star)/(30*60*1000);
                    for (int i=1;i<count;i++){
                        cal.add(Calendar.MINUTE,30);
                        list.add(sdf.format(cal.getTime()));
                    }


            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        body.setData(list);
        return body;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/getTableNum")
    public ResultBody getTableNum(){
        ResultBody body=new ResultBody();
        String store_id=AttaUtils.getConfigValueByKey("store_id");
        T_base t_base=tBaseService.getBaseById(store_id);
        int[] arr=null;
        if(t_base!=null){
            Integer start=t_base.getTable_start();
            Integer end=t_base.getTable_end();
            if(start!=null&&end!=null){

            }else {
                start=1;
                end=10;
            }
            arr=new int[end-start+1];
            for (int i=0;i<arr.length;i++){
                arr[i]=start++;
            }
        }
        //int[] arr={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19};
        body.setData(arr);
        return body;
    }

    @RequestMapping("/getLogisticsInfo")
    public ResultBody getLogisticsInfo(String order_id){
        ResultBody body=new ResultBody();

        T_order_express t_order_express=orderService.getLogisticsInfo(order_id);
        body.setData(t_order_express);
        return body;
    }

    /**
     * 获取电商订单详情
     * @param order_id
     * @return
     */
    @RequestMapping("/getShopOrderDetails")
    public ResultBody getShopOrderDetails(String order_id){
        ResultBody body=new ResultBody();
        Map<String,Object> map=new HashMap<>();
        T_order t_order=orderService.getShopOrderById(order_id);
        map.put("t_order",t_order);
        if(t_order!=null){
            //if(t_order.getEat_type()==1){
            String store_id=AttaUtils.getConfigValueByKey("store_id");
            T_store_info t_store_info=orderService.getStoreInfoById(store_id);
            map.put("t_store_info",t_store_info);
            // }
        }
        body.setData(map);
        return body;
    }




}

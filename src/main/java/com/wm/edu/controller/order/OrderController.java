package com.wm.edu.controller.order;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.wm.edu.model.base.Express;
import com.wm.edu.model.order.*;
import com.wm.edu.model.print.Goods;
import com.wm.edu.model.print.PrintOrder;
import com.wm.edu.model.store.T_store_info;
import com.wm.edu.service.order.OrderService;
import com.wm.edu.service.store.TBaseService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.util.ExportExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private TBaseService tBaseService;

    @RequestMapping("/main")
    public String main(Model model,String type){
        OrderStauteEnum[] orderStauteEnums=OrderStauteEnum.values();
        model.addAttribute("orderStauteEnums",orderStauteEnums);
        if("0".equals(type)){
            return "order/orderList";
        }else if("1".equals(type)){
            return "order/storeOrderList";
        }else if("2".equals(type)) {
            return "order/storeDnOrderList";
        }else  {
            return "order/shopOrder/shopOrderList";
        }

    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(T_order t_order){
        Map<String,Object> map=new HashMap<>();
        Page<T_order> page=orderService.getOrderList(t_order);
        map.put("rows", page.getResult());//当前记录数
        map.put("records", page.getTotal());
        map.put("page",page.getPageNum());
        map.put("total",  page.getPages());
        return map;
    }


    @RequestMapping("/shopList")
    @ResponseBody
    public Map<String,Object> shopList(T_order t_order){
        Map<String,Object> map=new HashMap<>();
        Page<T_order> page=orderService.getShopOrderList(t_order);
        map.put("rows", page.getResult());//当前记录数
        map.put("records", page.getTotal());
        map.put("page",page.getPageNum());
        map.put("total",  page.getPages());
        return map;
    }

    /**
     *
     */
    @RequestMapping("/exportOrder")
    public void exportOrder(T_order t_order, HttpServletRequest request, HttpServletResponse response){
        List<T_order> list=orderService.getAllOrderList(t_order);
        String title="订单管理";
        Map<String,String> map=new LinkedHashMap<>();
        map.put("order_id","订单号");
        map.put("name","姓名");
        map.put("phone","电话");
        map.put("address","收件人地址");
        map.put("price","价格");
        map.put("status","订单状态");
        map.put("create_time","创建时间");
        map.put("pay_time","支付时间");

        JSONArray ja=new JSONArray();
        if(list!=null){
            for (T_order order:list){
                ExportOrder exportOrder=new ExportOrder();
                exportOrder.setOrder_id(order.getOrder_id());
                exportOrder.setStatus(order.getStatus_name());
                exportOrder.setCreate_time(order.getOrder_time());
                exportOrder.setPay_time(order.getPay_time());
                exportOrder.setPrice(order.getReal_price()+"");
                if(order.getT_order_address()!=null){
                    exportOrder.setName(order.getT_order_address().getName());
                    exportOrder.setPhone(order.getT_order_address().getPhone());
                    exportOrder.setAddress(order.getT_order_address().getProvince()+order.getT_order_address().getCity()+order.getT_order_address().getCounty()+order.getT_order_address().getDetails_addr());
                }
                ja.add(exportOrder);
            }
        }

        ExportExcelUtils.downloadExcelFile(title,map,ja,response,request);
    }
    @RequestMapping("/toDetails")
    public String toDetails(String order_id, Model model){
        T_order t_order=orderService.getOrderById(order_id);
        model.addAttribute("t_order",t_order);
        return "order/orderDetails";
    }

    @RequestMapping("/toShopDetails")
    public String toShopDetails(String order_id, Model model){
        T_order t_order=orderService.getShopOrderById(order_id);
        model.addAttribute("t_order",t_order);
        return "order/shopOrder/shopOrderDetails";
    }
    @RequestMapping("/inputLogistics")
    public String inputLogistics(String order_id, Model model){
        List<Express> list=orderService.getAllExpress();
        model.addAttribute("order_id",order_id);
        model.addAttribute("expressList",list);
        return "order/shopOrder/inputLogistics";
    }

    /**
     *
     * @return
     */
    @RequestMapping("/sureLogistics")
    @ResponseBody
    public Map<String,Object> sureLogistics(T_order_express t_order_express){
        Map<String,Object> map=new HashMap<>();
        String res=orderService.sureLogistics(t_order_express);
        map.put("result",res);
        return map;
    }


    @RequestMapping("/orderReceiptDetails")
    public String orderReceiptDetails(String order_id,String eat_type, Model model){
        T_order t_order=orderService.getOrderById(order_id);
        model.addAttribute("t_order",t_order);
        if("0".equals(eat_type)){
            return "index/orderReceiptDetails";
        }else {
            return "index/orderDdDetails";
        }

    }
    @RequestMapping("/toStoreDetails")
    public String toStoreDetails(String order_id, Model model){
        T_order t_order=orderService.getOrderById(order_id);
        model.addAttribute("t_order",t_order);
        return "order/storeOrderDetails";
    }

    /**
     * 更改订单状态
      * @param t_order
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public Map<String,Object> updateStatus(T_order t_order){
        Map<String,Object> map=new HashMap<>();
        String result=orderService.updateStatus(t_order);
        map.put("result",result);
        return map;
    }

    @RequestMapping("/getOrderInfo")
    @ResponseBody
    public Map<String,Object> getOrderInfo(String order_id){
        Map<String,Object> map=new HashMap<>();
        T_order t_order=orderService.getOrderById(order_id);
        PrintOrder printOrder=new PrintOrder();
        if(t_order!=null){
            List<T_order_commodity> list=t_order.getCommodity_infoList();

            if(list!=null&&list.size()>0){
                List<Goods> goodsList=new ArrayList<>();
                int sum_com=0;
                float sum_price=0.0f;
                for (T_order_commodity commodity:list){
                    Goods goods=new Goods();
                    goods.setName(commodity.getCommodity_name());
                    goods.setCount(commodity.getComm_num()+"");
                    goods.setPrice(commodity.getCommodity_price()+"");
                    goods.setTotal(commodity.getSum_price()+"");
                    sum_com+=commodity.getComm_num();
                    sum_price+=commodity.getSum_price();
                    goodsList.add(goods);
                }
                printOrder.setGoodsList(goodsList);
                printOrder.setTotalCount(sum_com+"");

                T_order_address t_order_address=t_order.getT_order_address();
                if(t_order_address!=null){
                    sum_price+=t_order_address.getDistribution_price();
                    printOrder.setDistribution_price(""+t_order_address.getDistribution_price());
                    sum_price+=t_order_address.getPacking_price();
                    printOrder.setPacking_price(t_order_address.getPacking_price()+"");
                    printOrder.setRemarks(t_order.getRemarks());
                    printOrder.setAddress(t_order_address.getProvince()+t_order_address.getCity()+t_order_address.getCounty()+t_order_address.getDetails_addr());
                }
                DecimalFormat df = new DecimalFormat("#0.00");
                printOrder.setTotal(df.format(sum_price));
            }

            //t_order.getT_order_address().getDistribution_price();

            String store_id=AttaUtils.getConfigValueByKey("store_id");
            T_store_info store_info=orderService.getStoreInfoById(store_id);
            if(store_info!=null){
                printOrder.setShop(store_info.getStore_name());
            }
            printOrder.setOrderNo(t_order.getOrder_id());
            printOrder.setOrderTime(AttaUtils.dateToStr(t_order.getOrder_time()));
            printOrder.setTitle("订单信息");

            T_order_address t_order_address=t_order.getT_order_address();
            if(t_order_address!=null){
                printOrder.setName(t_order_address.getName());
                printOrder.setPhone(t_order_address.getPhone());
            }
        }

        map.put("printOrder",printOrder);
        return map;
    }


    @RequestMapping("/getDdOrderInfo")
    @ResponseBody
    public Map<String,Object> getDdOrderInfo(String order_id){
        Map<String,Object> map=new HashMap<>();
        T_order t_order=orderService.getOrderById(order_id);
        PrintOrder printOrder=new PrintOrder();
        if(t_order!=null){
            List<T_order_commodity> list=t_order.getCommodity_infoList();
            if(list!=null&&list.size()>0){
                List<Goods> goodsList=new ArrayList<>();
                int sum_com=0;
                float sum_price=0.0f;
                for (T_order_commodity commodity:list){
                    Goods goods=new Goods();
                    goods.setName(commodity.getCommodity_name());
                    goods.setCount(commodity.getComm_num()+"");
                    goods.setPrice(commodity.getCommodity_price()+"");
                    goods.setTotal(commodity.getSum_price()+"");
                    sum_com+=commodity.getComm_num();
                    sum_price+=commodity.getSum_price();
                    goodsList.add(goods);
                }
                printOrder.setGoodsList(goodsList);
                printOrder.setTotalCount(sum_com+"");

                //T_order_address t_order_address=t_order.getT_order_address();
                /*if(t_order_address!=null){
                    sum_price+=t_order_address.getDistribution_price();
                    printOrder.setDistribution_price(""+t_order_address.getDistribution_price());
                    sum_price+=t_order_address.getPacking_price();
                    printOrder.setPacking_price(t_order_address.getPacking_price()+"");
                    printOrder.setRemarks(t_order.getRemarks());
                    printOrder.setAddress(t_order_address.getProvince()+t_order_address.getCity()+t_order_address.getCounty()+t_order_address.getDetails_addr());
                }*/
                printOrder.setRemarks(t_order.getRemarks());
                DecimalFormat df = new DecimalFormat("#0.00");
                printOrder.setTotal(df.format(sum_price));
            }

            //t_order.getT_order_address().getDistribution_price();

            String store_id=AttaUtils.getConfigValueByKey("store_id");
            T_store_info store_info=orderService.getStoreInfoById(store_id);
            if(store_info!=null){
                printOrder.setShop(store_info.getStore_name());
            }
            printOrder.setOrderNo(t_order.getOrder_id());
            printOrder.setOrderTime(AttaUtils.dateToStr(t_order.getOrder_time()));
            printOrder.setTitle("订单信息");
            T_order_arrive t_order_arrive=t_order.getT_order_arrive();
            if(t_order_arrive!=null){
                printOrder.setPhone(t_order_arrive.getPhone());
            }
            T_order_scavenging t_order_scavenging=t_order.getT_order_scavenging();
            if(t_order_scavenging!=null){
                printOrder.setTable_num(t_order_scavenging.getTable_num()+"");
                printOrder.setPerson_num(t_order_scavenging.getPerson_num()+"");
            }
            printOrder.setAddress(t_order.getEat_type()+"");
           /* T_order_address t_order_address=t_order.getT_order_address();
            if(t_order_address!=null){
                printOrder.setName(t_order_address.getName());
                printOrder.setPhone(t_order_address.getPhone());
            }*/
        }

        map.put("printOrder",printOrder);
        return map;
    }

    /**
     *
     * @param order_id
     * @return
     */
    @RequestMapping("/seachLogs")
    public String seachLogs(String order_id,Model model){
        T_order_express t_order_express=orderService.getLogisticsInfo(order_id);
        model.addAttribute("t_order_express",t_order_express);
        return "order/shopOrder/seachLogs";
    }

}

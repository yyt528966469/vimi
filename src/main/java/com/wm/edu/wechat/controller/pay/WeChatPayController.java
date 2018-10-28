package com.wm.edu.wechat.controller.pay;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.wm.edu.model.order.T_order;
import com.wm.edu.model.store.T_store_info;
import com.wm.edu.service.order.OrderService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.common.ResultBody;
import com.wm.edu.utils.pay.PayUtils;
import com.wm.edu.utils.pay.common.*;
import com.wm.edu.utils.pay.model.OrderInfo;
import com.wm.edu.utils.pay.model.OrderReturnInfo;
import com.wm.edu.utils.pay.model.SignInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/wechat/pay")
public class WeChatPayController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/weChatPay")
    public ResultBody weChatPay(HttpServletRequest request, HttpServletResponse response){

        ResultBody body=new ResultBody();
        String openid=request.getParameter("openid");
        String order_id=request.getParameter("order_id");
        T_order t_order=orderService.getOrdermin(order_id);
        String store_id=AttaUtils.getConfigValueByKey("store_id");
        T_store_info t_store_info=orderService.getStoreInfoById(store_id);
        OrderInfo order = new OrderInfo();
        if(t_order!=null){
            Float real_price=t_order.getReal_price();
            if(real_price!=null){
                int price=(int)(real_price*100);
                order.setTotal_fee(price);
            }
            order.setOut_trade_no(t_order.getOrder_id());
        }

        order.setAppid(Configure.getAppID());
        order.setMch_id(Configure.getMch_id());
        order.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
        order.setBody(t_store_info.getStore_name()+"-下单");
        String spbill_create_ip = getIpAddr(request);
        order.setSpbill_create_ip(spbill_create_ip);
        order.setNotify_url(AttaUtils.getConfigValueByKey("pay_notify_url"));
        order.setTrade_type("JSAPI");
        order.setOpenid(openid);
        order.setSign_type("MD5");
        //生成签名
        String sign = null;

        try {
            sign = Signature.getSign(order);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //String stringA="appid="+order.getAppid()+"&body="+order.getBody()+"&mch_id="+order.getMch_id()+"&nonce_str="+order.getNonce_str();
        //stringA=stringA+"&key="+Configure.getKey();
        //System.out.println(stringA);
        //sign=MD5.MD5Encode(stringA).toUpperCase();
        System.out.println(sign);
        order.setSign(sign);

        String result = null;
        try {
            result = HttpRequest.sendPost("https://api.mch.weixin.qq.com/pay/unifiedorder", order);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //System.out.println(result);
        //L.info("---------下单返回:"+result);
        System.out.println("---------下单返回:"+result);
        XStream xStream = new XStream();
        xStream.alias("xml", OrderReturnInfo.class);

        OrderReturnInfo returnInfo = (OrderReturnInfo)xStream.fromXML(result);


        SignInfo signInfo = new SignInfo();
        signInfo.setAppId(Configure.getAppID());
        long time = System.currentTimeMillis()/1000;
        signInfo.setTimeStamp(String.valueOf(time));
        signInfo.setNonceStr(RandomStringGenerator.getRandomStringByLength(32));
        signInfo.setRepay_id("prepay_id="+returnInfo.getPrepay_id());
        signInfo.setSignType("MD5");
        JSONObject json = new JSONObject();
        //生成签名
        try {
            String two_sign = Signature.getSign(signInfo);
            json.put("paySign", two_sign);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //JSONObject json = new JSONObject();
        json.put("timeStamp", signInfo.getTimeStamp());
        json.put("nonceStr", signInfo.getNonceStr());
        json.put("package", signInfo.getRepay_id());
        json.put("signType", signInfo.getSignType());

        System.out.println("-------再签名:"+json.toJSONString());
        body.setData(json);
        return body;
    }

    @RequestMapping("/PayResult")
    public ResultBody PayResult(HttpServletRequest request, HttpServletResponse response){
        ResultBody body=new ResultBody();
        String reqParams = null;
        try {
            reqParams = StreamUtil.read(request.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("-------支付结果:"+reqParams);
        try {
            Map map=PayUtils.doXMLParse(reqParams);
            String returnCode = (String) map.get("return_code");
            if("SUCCESS".equals(returnCode)){
                String out_trade_no = (String) map.get("out_trade_no");
                T_order order=orderService.getOrdermin(out_trade_no);
                if (order!=null&&order.getStatus()==0){
                    T_order t_order=new T_order();
                    t_order.setOrder_id(out_trade_no);
                    t_order.setPay_time(new Date());
                    if(order.getEat_type()!=3){
                        t_order.setStatus(1);
                    }else {
                        t_order.setStatus(2);
                    }
                    orderService.updateStatus(t_order);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }




}

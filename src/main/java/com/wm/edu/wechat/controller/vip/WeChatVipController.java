package com.wm.edu.wechat.controller.vip;

import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import com.wm.edu.model.order.T_order;
import com.wm.edu.model.record.T_payment_record;
import com.wm.edu.model.vip.C_user_vip;
import com.wm.edu.model.vip.T_recharge;
import com.wm.edu.service.record.PaymentRecordService;
import com.wm.edu.service.vip.RechargeService;
import com.wm.edu.service.vip.UserVipService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.common.ResultBody;
import com.wm.edu.utils.pay.PayUtils;
import com.wm.edu.utils.pay.common.*;
import com.wm.edu.utils.pay.model.OrderInfo;
import com.wm.edu.utils.pay.model.OrderReturnInfo;
import com.wm.edu.utils.pay.model.SignInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/wechat/vip")
@RestController
public class WeChatVipController {

    @Autowired
    private UserVipService userVipService;
    @Autowired
    private RechargeService rechargeService;
    @Autowired
    private PaymentRecordService paymentRecordService;


    /**
     * 获取用户信息
     * @param c_user_id
     * @return
     */
    @RequestMapping("/checkVip")
    public ResultBody checkVip(String c_user_id){
        ResultBody body=new ResultBody();
        C_user_vip c_user_vip=userVipService.getVipByUserId(c_user_id);
        body.setData(c_user_vip);
        return body;
    }
    @RequestMapping("/saveUserVip")
    public ResultBody saveUserVip(C_user_vip c_user_vip){
        ResultBody body=new ResultBody();
        Integer code=userVipService.saveUserVip(c_user_vip);
        body.setCode(code);
        return body;
    }

    /**
     * 获取充值面值
     * @return
     */
    @RequestMapping("/getRecgarge")
    public ResultBody getRecgarge(){
        ResultBody body=new ResultBody();
        T_recharge t_recharge=new T_recharge();
        List<T_recharge> list=rechargeService.getRechargePage(t_recharge);
        body.setData(list);
        return body;
    }

    /**
     * 点击充值
     * @param request
     * @return
     */
   /* @RequestMapping("/toRecgarge")
    public ResultBody toRecgarge(HttpServletRequest request){
        ResultBody body=new ResultBody();

        String c_user_id=request.getParameter("c_user_id");

        T_payment_record record=new T_payment_record();
        record.setId(AttaUtils.getNo());
        record.setC_user_id(c_user_id);
        record.setContent("会员充值");
        record.setCreate_time(new Date());
        record.setType(1);
        if(t_recharge!=null) {
            record.setPrice(t_recharge.getPay_price());
        }
        Integer code=paymentRecordService.insertRecord(record);
        body.setCode(code);
        Map<String,Object> map=new HashMap<>();
        map.put("record_id",record.getId());
        body.setData(map);
        return body;
    }*/
    /**
     * 充值
     * @return
     */
    @RequestMapping("/payRecgarge")
    public ResultBody payRecgarge(HttpServletRequest request){
        ResultBody body=new ResultBody();
        String recharge_id=request.getParameter("recharge_id");
        T_recharge t_recharge=rechargeService.getRechargeById(recharge_id);
        String openid=request.getParameter("openid");
        OrderInfo order = new OrderInfo();
        if(t_recharge!=null){
            float pay_price=t_recharge.getPay_price();
            int price=(int)(pay_price*100);
            order.setTotal_fee(price);
        }
        order.setBody("会员充值");
        order.setOut_trade_no(AttaUtils.getNo());
        order.setAppid(Configure.getAppID());
        order.setMch_id(Configure.getMch_id());
        order.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));

        String spbill_create_ip = getIpAddr(request);
        order.setSpbill_create_ip(spbill_create_ip);
        order.setNotify_url(AttaUtils.getConfigValueByKey("pay_recharge_url"));
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
        json.put("record_id", order.getOut_trade_no());
        System.out.println("-------再签名:"+json.toJSONString());
        body.setData(json);
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


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return body;
    }

    /**
     * 支付成功回调
     * @param t_payment_record
     * @param recharge_id
     * @return
     */
    @RequestMapping("/paySuccess")
    public ResultBody paySuccess(T_payment_record t_payment_record,String recharge_id){
        ResultBody body=new ResultBody();
        Integer code=rechargeService.paysuccess(recharge_id,t_payment_record);
        body.setData(code);
        return body;
    }

    /**
     * 获取支付记录
     * @param c_user_id
     * @return
     */
    @RequestMapping("/getPayRecord")
    public ResultBody getPayRecord(String c_user_id){
        ResultBody body=new ResultBody();
        List<T_payment_record> list=paymentRecordService.getPayRecord(c_user_id);
        body.setData(list);
        return body;
    }
}

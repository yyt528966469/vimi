package com.wm.edu.wechat.controller.pay;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.wm.edu.model.order.T_order;
import com.wm.edu.service.order.OrderService;
import com.wm.edu.utils.common.ResultBody;
import com.wm.edu.utils.pay.PayResult;
import com.wm.edu.utils.pay.PayUtils;
import com.wm.edu.utils.pay.common.Configure;
import com.wm.edu.utils.pay.common.RandomStringGenerator;
import com.wm.edu.utils.pay.common.Signature;
import com.wm.edu.utils.pay.common.StreamUtil;
import com.wm.edu.utils.pay.returnpay.CertHttpUtil;
import com.wm.edu.utils.pay.returnpay.RefundOrder;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.*;

@RestController
@RequestMapping("/wechat/refund")
public class WeChatRefundController {

    @Autowired
    private OrderService orderService;

    @RequestMapping("/refundPay")
    public ResultBody refundPay(HttpServletRequest request){
        ResultBody body=new ResultBody();
        String order_id=request.getParameter("order_id");
        if(StringUtils.isNotEmpty(order_id)){
            //System.out.println("order_id="+order_id);
            T_order t_order=orderService.getOrdermin(order_id);
                //String path = "C:\\cert\\apiclient_cert.p12";
                String result = PayUtils.refundPay(t_order);
                System.out.println(result);
        }

        return body;
    }





    /*退款回调*/
    @RequestMapping(value = "/refundResult")
    public ResultBody refundResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResultBody body=new ResultBody();
        String reqParams = StreamUtil.read(request.getInputStream());
        System.out.println("退款结果："+reqParams);
        Map result = PayUtils.doXMLParse(reqParams);
        String return_code = (String) result.get("return_code");//返回状态码
        if (return_code.equals("SUCCESS")) {


        }
        StringBuffer sb = new StringBuffer("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
        return body;
    }






}

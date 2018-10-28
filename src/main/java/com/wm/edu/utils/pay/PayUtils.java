package com.wm.edu.utils.pay;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.wm.edu.model.order.T_order;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.pay.common.Configure;
import com.wm.edu.utils.pay.common.RandomStringGenerator;
import com.wm.edu.utils.pay.common.Signature;
import com.wm.edu.utils.pay.returnpay.CertHttpUtil;
import com.wm.edu.utils.pay.returnpay.RefundOrder;
import org.apache.commons.lang3.StringUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

/**
 * yyt
 */
public class PayUtils {
    public static String refundPay(T_order t_order){
        String result ="";
        if(t_order!=null) {
            RefundOrder refundOrder = new RefundOrder();
            refundOrder.setAppid(Configure.getAppID());
            refundOrder.setMch_id(Configure.getMch_id());
            float price = t_order.getReal_price();
            int real_price = (int) (price * 100);
            refundOrder.setTotal_fee(String.valueOf(real_price));
            refundOrder.setRefund_fee(String.valueOf(real_price));
            refundOrder.setOut_trade_no(t_order.getOrder_id());
            refundOrder.setNonce_str(RandomStringGenerator.getRandomStringByLength(32));
            refundOrder.setSign_type("MD5");
            refundOrder.setOut_refund_no(UUID.randomUUID().toString().replace("-", ""));
            refundOrder.setNotify_url(AttaUtils.getConfigValueByKey("refund_notify_url"));
            //生成签名
            String sign = null;
            try {
                sign = Signature.getSign(refundOrder);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            refundOrder.setSign(sign);

            XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
            xStreamForRequestPostData.alias("xml", refundOrder.getClass());
            //将要提交给API的数据对象转换成XML格式数据Post给API
            String postDataXML = xStreamForRequestPostData.toXML(refundOrder);
            System.out.println("xml=" + postDataXML);
            //String path = "C:\\cert\\apiclient_cert.p12";
            result = CertHttpUtil.postData("https://api.mch.weixin.qq.com/secapi/pay/refund", postDataXML);
            //System.out.println(result);
        }
        return result;
    }

    public static Map doXMLParse(String strxml) throws Exception {
        if(null == strxml || "".equals(strxml)) {
            return null;
        }
        Map m = new HashMap();
        InputStream in = String2Inputstream(strxml);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if(children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }
            m.put(k, v);
        }				//关闭流
        in.close();
        return m;
    }

    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }
        return sb.toString();
    }

    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }


}

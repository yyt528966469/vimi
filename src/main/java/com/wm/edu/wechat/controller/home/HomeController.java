package com.wm.edu.wechat.controller.home;

import com.alibaba.fastjson.JSONObject;
import com.wm.edu.model.cuser.C_user_info;
import com.wm.edu.service.cuser.UserInfoService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.cookie.CookieUtil;
import com.wm.edu.utils.http.HttpUtils;
import com.wm.edu.utils.pay.common.Configure;
import com.wm.edu.utils.util.AesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wechat/home")
public class HomeController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/index")
    @ResponseBody
    public Map index(String code, HttpServletResponse response){
        Map map = new HashMap();
        System.out.println("获取code值，code="+code);
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }
        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = Configure.getAppID();
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = Configure.getSecret();
        //授权（必填）
        String grant_type = "authorization_code";
        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpUtils.get("https://api.weixin.qq.com/sns/jscode2session?"+params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        C_user_info user_info=userInfoService.getUserByOpenid(openid);
        if(user_info==null){
            user_info=new C_user_info();
            user_info.setC_user_id(AttaUtils.getNo());
            user_info.setOpen_id(openid);
            user_info.setCreate_time(new Date());
            userInfoService.insertUserInfo(user_info);
            //CookieUtil.setCookie(response,"c_user_id",user_info.getC_user_id());
        }
        //CookieUtil.setCookie(response,"c_user_id",user_info.getC_user_id());
        map.put("openid",openid);
        map.put("c_user_id",user_info.getC_user_id());
        map.put("session_key",session_key);
        return map;
    }

    @RequestMapping("/decodeUserInfo")
    @ResponseBody
    public Map decodeUserInfo(String encryptedData, String iv, String code) {

        Map map = new HashMap();
        System.out.println("获取code值，code="+code);
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            map.put("status", 0);
            map.put("msg", "code 不能为空");
            return map;
        }
        //小程序唯一标识   (在微信小程序管理后台获取)
        String wxspAppid = "wx9fd89c6e5b278f3b";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "ccec90c91c8ac2c9372a4b66435ac8ea";
        //授权（必填）
        String grant_type = "authorization_code";
        //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
        //请求参数
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpUtils.get("https://api.weixin.qq.com/sns/jscode2session?"+params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //获取会话密钥（session_key）
        String session_key = json.get("session_key").toString();
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");

        //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
        try {
            String result = AesUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            if (null != result && result.length() > 0) {
                map.put("status", 1);
                map.put("msg", "解密成功");

                JSONObject userInfoJSON = JSONObject.parseObject(result);
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                map.put("userInfo", userInfo);
                System.out.println("获取用户信息，userInfo="+userInfo);
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("status", 0);
        map.put("msg", "解密失败");
        return map;
    }
}

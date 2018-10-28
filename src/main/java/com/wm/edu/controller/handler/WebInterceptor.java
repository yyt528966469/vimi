
package com.wm.edu.controller.handler;


import com.wm.edu.utils.util.ApplicationContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ParameterizableViewController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class WebInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(WebInterceptor.class);
    private static ApplicationContext applicationContext=null;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if(o instanceof ParameterizableViewController){
            if(applicationContext==null){
                ParameterizableViewController param=(ParameterizableViewController)o;
                applicationContext=param.getApplicationContext();
                ApplicationContextUtils.setApplicationContext(applicationContext);
            }
        }


/**
         * 对来自后台的请求统一进行日志处理
         */
        String uri = request.getRequestURI();
        //System.out.println(request.getSession().getMaxInactiveInterval());

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        request.getContextPath();
        //response.setHeader("Access-Control-Allow-Origin", "*");//处理跨域问题
        //String host=request.getHeader("Host");
        //String queryString = request.getContextPath();
       // System.out.println(request);
       if(uri.contains("/login")||uri.contains("checkUser")){
            return true;
        }
        //if(uri.contains("front")||uri.contains(".jpg")||uri.contains(".png")||uri.contains(".gif")||uri.contains(".jpeg")||uri.contains(".bmp")){
        if(uri.contains("wechat")||uri.contains("upload")||uri.contains("ueditorUpload")){

            return true;
        }else {
            String username=(String)request.getSession().getAttribute("ACTIVITY_WEB_LAST_USERNAME");
            if(StringUtils.isEmpty(username)){
                response.sendRedirect(request.getContextPath()+"/login");
                return false;
            }
        }
        //response.setHeader("X-Frame-Options", "SAMEORIGIN");
       // logger.info(String.format("请求参数, url: %s, method: %s, uri: %s, params: %s", url, method, uri, queryString));
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

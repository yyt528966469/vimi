package com.wm.edu.wechat.controller.coupons;

import com.wm.edu.model.coupons.T_coupons;
import com.wm.edu.model.coupons.T_user_coupons;
import com.wm.edu.service.coupons.CouponsService;
import com.wm.edu.service.coupons.UserCouponsService;
import com.wm.edu.utils.common.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/wechat/coupons")
public class WechatCouponsController {

    @Autowired
    private UserCouponsService userCouponsService;


    /**
     * 用户添加优惠劵
     * @param t_user_coupons
     * @return
     */
    @RequestMapping("/addCoupons")
    public ResultBody addCoupons(T_user_coupons t_user_coupons){
        ResultBody body=new ResultBody();
        Integer code=userCouponsService.saveUserCoupons(t_user_coupons);
        body.setCode(code);
        return body;
    }

    /**
     * 判断用户是否已经领劵
     * @param c_user_id
     * @param coupons_id
     * @return
     */
    @RequestMapping("/getOneCoupons")
    public ResultBody getOneCoupons(String c_user_id,String coupons_id){
        ResultBody body=new ResultBody();
        String res=userCouponsService.getOneCoupons(c_user_id,coupons_id);
        body.setData(res);
        return body;
    }

    /**
     * 获取用户优惠劵列表
     * @param c_user_id
     * @return
     */
    @RequestMapping("/getUserCouponsList")
    public ResultBody getUserCouponsList(String c_user_id){
        ResultBody body=new ResultBody();
        T_user_coupons user_coupons=new T_user_coupons();
        user_coupons.setC_user_id(c_user_id);
        List<T_user_coupons> list=userCouponsService.getUserCouponsList(user_coupons);
        body.setData(list);
        return body;
    }

    /**
     *
     * @param c_user_id
     * @return
     */
    @RequestMapping("/getAllCouponsList")
    public ResultBody getAllCouponsList(String c_user_id){
        ResultBody body=new ResultBody();
        List<T_coupons> list= userCouponsService.getAllCouponsList(c_user_id);
        body.setData(list);
        return body;
    }
   /* @RequestMapping("/getUserCouponsNum")
    public ResultBody getUserCouponsNum(String c_user_id){
        ResultBody body=new ResultBody();
        userCouponsService.getUserCouponsNum(c_user_id);
        return body;
    }*/




    @RequestMapping("/test")
    public ResultBody test(String c_user_id){
        ResultBody body=new ResultBody();
        T_user_coupons t_user_coupons=new T_user_coupons();
        t_user_coupons.setC_user_id(c_user_id);

        t_user_coupons.setSum_price(200.0f);
        T_coupons t_coupons=userCouponsService.getMaxCoupons(t_user_coupons);
        body.setData(t_coupons);
        return body;
    }
}


package com.wm.edu.service.coupons;

import com.wm.edu.mapper.coupons.T_user_couponsMapper;
import com.wm.edu.model.coupons.T_coupons;
import com.wm.edu.model.coupons.T_user_coupons;
import com.wm.edu.utils.common.AttaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserCouponsService {

    @Autowired
    private T_user_couponsMapper t_user_couponsMapper;
    @Autowired
    private CouponsService couponsService;

    public Integer saveUserCoupons(T_user_coupons t_user_coupons){
        Integer code=200;
        try {
            T_coupons t_coupons=couponsService.getCouponsById(t_user_coupons.getCoupons_id());
            Calendar cal=Calendar.getInstance();
            cal.add(Calendar.DATE,t_coupons.getEffective_day());//获取有效期
            t_user_coupons.setOver_time(cal.getTime());
            t_user_coupons.setId(AttaUtils.getNo());
            t_user_coupons.setCreate_time(new Date());
            t_user_coupons.setStatus(0);
            t_user_couponsMapper.insertSelective(t_user_coupons);
        }catch (Exception e){
            code=502;
            e.printStackTrace();
        }

        return code;
    }

    /**
     * 获取用户是否已领取优惠券
     * @param c_user_id
     * @param coupons_id
     * @return
     */
    public String getOneCoupons(String c_user_id,String coupons_id){
        String res="0";
        T_user_coupons user_coupons=t_user_couponsMapper.getOneCoupons(c_user_id,coupons_id);
        if(user_coupons!=null){
            res="1";
        }
        return res;
    }

    public List<T_user_coupons> getUserCouponsList(T_user_coupons t_user_coupons){
        return t_user_couponsMapper.getUserCouponsList(t_user_coupons);
    }

    public T_coupons getMaxCoupons(T_user_coupons t_user_coupons){
        return t_user_couponsMapper.getMaxCoupons(t_user_coupons);
    }

    /**
     *
     * @param c_user_id
     * @return
     */
    public List<T_coupons> getAllCouponsList(String c_user_id){
        return t_user_couponsMapper.getAllCouponsList(c_user_id);
    }

    public Integer getUserCouponsNum(String c_user_id){
        return t_user_couponsMapper.getUserCouponsNum(c_user_id);
    }

    public void updateUserCouspons(T_user_coupons t_user_coupons){
        t_user_couponsMapper.updateUserCouspons(t_user_coupons);
    }

}

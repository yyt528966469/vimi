package com.wm.edu.service.coupons;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.coupons.T_couponsMapper;
import com.wm.edu.model.coupons.T_coupons;
import com.wm.edu.utils.common.AttaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CouponsService {
    @Autowired
    private T_couponsMapper t_couponsMapper;

    /**
     * 分页查询
     * @param t_coupons
     * @return
     */
    public Page<T_coupons> getCouponsPage(T_coupons t_coupons){
        //String orderStandesc=t_coupons.getSidx()+" "+t_coupons.getSord();
        Page<T_coupons> page=PageHelper.startPage(t_coupons.getPage(),t_coupons.getRows(),t_coupons.getOrderStandesc());
        t_couponsMapper.getCouponsList(t_coupons);
        return page;
    }

    /**
     *
     *
     * @param coupons_id
     * @return
     */
    public T_coupons getCouponsById(String coupons_id){
        return t_couponsMapper.selectByPrimaryKey(coupons_id);
    }



    public String saveCoupons(T_coupons t_coupons){
        String result="0";
        try {
            if(StringUtils.isNotEmpty(t_coupons.getCoupons_id())){
                t_couponsMapper.updateByPrimaryKeySelective(t_coupons);
            }else {
                t_coupons.setCoupons_id(AttaUtils.getNo());
                t_coupons.setCreate_time(new Date());
                t_couponsMapper.insertSelective(t_coupons);
            }

        }catch (Exception e){
            e.printStackTrace();
            result="1";
        }

        return result;
    }

    public String deleteCoupons(String id){
        String result="0";
        try {
            t_couponsMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            result="1";
        }

        return result;
    }
}

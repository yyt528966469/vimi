package com.wm.edu.service.vip;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.vip.C_user_vipMapper;
import com.wm.edu.model.vip.C_user_vip;
import com.wm.edu.utils.common.AttaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserVipService {

    @Autowired
    private C_user_vipMapper c_user_vipMapper;

    public Page<C_user_vip> getVipPage(C_user_vip c_user_vip){
        Page<C_user_vip> page=PageHelper.startPage(c_user_vip.getPage(),c_user_vip.getRows(),c_user_vip.getOrderStandesc());
        c_user_vipMapper.getVipList(c_user_vip);
        //c_user_vipMapper
        return page;
    }

    public C_user_vip getVipByUserId(String c_user_id){
        return c_user_vipMapper.getVipByUserId(c_user_id);
    }

    public Integer saveUserVip(C_user_vip c_user_vip){
        Integer code=200;
        try {
            if(StringUtils.isNotEmpty(c_user_vip.getId())){
                c_user_vipMapper.updateByPrimaryKeySelective(c_user_vip);
            }else {
                c_user_vip.setId(AttaUtils.getNo());
                c_user_vip.setCreate_time(new Date());
                c_user_vip.setBalance(0.0f);
                c_user_vip.setIntegral(0);//积分
                c_user_vip.setDiscount(9.0f);//折扣
                c_user_vipMapper.insertSelective(c_user_vip);
            }

        }catch (Exception e){
            e.printStackTrace();
            code=502;
        }

        return code;
    }



}

package com.wm.edu.service.vip;

import com.github.pagehelper.Page;
import com.wm.edu.mapper.vip.T_rechargeMapper;
import com.wm.edu.model.record.T_payment_record;
import com.wm.edu.model.vip.C_user_vip;
import com.wm.edu.model.vip.T_recharge;
import com.wm.edu.service.record.PaymentRecordService;
import com.wm.edu.utils.common.AttaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RechargeService {

    @Autowired
    private T_rechargeMapper t_rechargeMapper;
    @Autowired
    private PaymentRecordService paymentRecordService;
    @Autowired
    private UserVipService userVipService;

    public List<T_recharge> getRechargePage(T_recharge t_recharge){
        return t_rechargeMapper.getRechargeList(t_recharge);
    }

    public T_recharge getRechargeById(String id){
        return t_rechargeMapper.selectByPrimaryKey(id);
    }

    public String saveRecharge(T_recharge t_recharge){
        String res="0";
        try {
            if(StringUtils.isNotEmpty(t_recharge.getId())){
                t_rechargeMapper.updateByPrimaryKeySelective(t_recharge);
            }else {
                t_recharge.setId(AttaUtils.getNo());
                t_rechargeMapper.insertSelective(t_recharge);
            }

        }catch (Exception e){
            e.printStackTrace();
            res="1";
        }


        return res;
    }

    @Transactional
    public Integer paysuccess(String recharge_id, T_payment_record t_payment_record){
        Integer code=200;
        try {
            T_recharge t_recharge=getRechargeById(recharge_id);
            t_payment_record.setPrice(t_recharge.getPrice());
            t_payment_record.setContent("会员充值");
            t_payment_record.setType(1);
            paymentRecordService.insertRecord(t_payment_record);
            C_user_vip c_user_vip=userVipService.getVipByUserId(t_payment_record.getC_user_id());
            float balance=c_user_vip.getBalance();
            BigDecimal price=new BigDecimal(balance);
            price=price.add(new BigDecimal(t_payment_record.getPrice()));
            C_user_vip user_vip=new C_user_vip();
            user_vip.setId(c_user_vip.getId());
            user_vip.setBalance(price.floatValue());
            userVipService.saveUserVip(user_vip);
        }catch (Exception e){
            e.printStackTrace();
            code=502;
            throw new RuntimeException();
        }
        return code;
    }

    public String deleteRecharge(String id){
        String res="0";
        try {
            t_rechargeMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            res="1";
        }
        return res;
    }


}

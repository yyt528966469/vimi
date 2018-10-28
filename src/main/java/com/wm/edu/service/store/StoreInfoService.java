package com.wm.edu.service.store;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.store.T_baseMapper;
import com.wm.edu.mapper.store.T_store_infoMapper;
import com.wm.edu.model.store.T_base;
import com.wm.edu.model.store.T_store_info;
import com.wm.edu.utils.common.AttaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StoreInfoService {

    @Autowired
    private T_store_infoMapper t_store_infoMapper;
    @Autowired
    private T_baseMapper t_baseMapper;

    public String saveStoreInfo(T_store_info t_store_info, T_base t_base){
        String result="0";
        try {
            if(StringUtils.isNotEmpty(t_store_info.getStore_id())){
                t_base.setId(t_store_info.getStore_id());
                t_baseMapper.updateByPrimaryKeySelective(t_base);
                t_store_infoMapper.updateByPrimaryKeySelective(t_store_info);
            }else {
                t_store_info.setStore_id(AttaUtils.getNo());
                t_store_info.setCreate_time(new Date());
                t_store_infoMapper.insertSelective(t_store_info);
                t_base.setId(t_store_info.getStore_id());
                t_baseMapper.insertSelective(t_base);
            }
        }catch (Exception e){
            e.printStackTrace();
            result="1";
        }
        return result;
    }

    public T_store_info getStoreInfoById(String id){
        return t_store_infoMapper.selectByPrimaryKey(id);
    }


    public T_base getBaseInfo(){
        String id=AttaUtils.getConfigValueByKey("store_id");
        return t_baseMapper.selectByPrimaryKey(id);
    }

}

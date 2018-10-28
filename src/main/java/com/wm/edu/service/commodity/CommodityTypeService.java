package com.wm.edu.service.commodity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.commodity.T_commodity_typeMapper;
import com.wm.edu.model.commodity.T_commodity_type;
import com.wm.edu.utils.common.AttaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommodityTypeService {

    @Autowired
    private T_commodity_typeMapper t_commodity_typeMapper;

    /**
     * 分页查询
     * @param t_commodity_type
     * @return
     */
    public Page<T_commodity_type> getTypeList(T_commodity_type t_commodity_type){
        Page<T_commodity_type> page=PageHelper.startPage(t_commodity_type.getPage(),t_commodity_type.getRows());
        t_commodity_typeMapper.getTypeList(t_commodity_type);
        return page;
    }

    /**
     * 根据ID获取对象
     * @param id
     * @return
     */
    public T_commodity_type getTypeById(String id){
        return t_commodity_typeMapper.selectByPrimaryKey(id);
    }

    public String saveType(T_commodity_type t_commodity_type){
        String result="0";
        try {
            if(StringUtils.isNotEmpty(t_commodity_type.getType_id())){
                t_commodity_typeMapper.updateByPrimaryKeySelective(t_commodity_type);
            }else {
                t_commodity_type.setType_id(AttaUtils.getNo());
                t_commodity_type.setCreate_time(new Date());
                t_commodity_typeMapper.insertSelective(t_commodity_type);
            }
        }catch (Exception e){
            e.printStackTrace();
            result="1";
        }
        return result;
    }

    public String deleteType(String type_id){
        String result="0";
        try {
            t_commodity_typeMapper.deleteByPrimaryKey(type_id);
        }catch (Exception e){
            e.printStackTrace();
            result="1";
        }
        return result;
    }

    public List<T_commodity_type> getAllType(){
        T_commodity_type t_commodity_type=new T_commodity_type();
        return t_commodity_typeMapper.getTypeList(t_commodity_type);
    }
}

package com.wm.edu.service.cuser;

import com.wm.edu.mapper.cuser.C_user_addressMapper;
import com.wm.edu.model.base.T_city;
import com.wm.edu.model.cuser.C_user_address;
import com.wm.edu.service.redis.RedisService;
import com.wm.edu.utils.common.AttaUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserAddressService {

    @Autowired
    private C_user_addressMapper c_user_addressMapper;
    @Autowired
    private RedisService redisService;

    /**
     * 获取地址列表
     * @param c_user_id
     * @return
     */
    public List<C_user_address> getAddressList(String c_user_id){
        return c_user_addressMapper.getAddressList(c_user_id);
    }

    public C_user_address getAddreddById(String address_id){
        return c_user_addressMapper.selectByPrimaryKey(address_id);
    }

    public C_user_address getMrAdderss(String c_user_id){
        return c_user_addressMapper.getMrAdderss(c_user_id);
    }

    public Integer saveAddress(C_user_address c_user_address){
        Integer code=200;
        try {
            if(StringUtils.isNotEmpty(c_user_address.getAddress_id())){
                c_user_addressMapper.updateByPrimaryKeySelective(c_user_address);
            }else {
                List<C_user_address> list=getAddressList(c_user_address.getC_user_id());
                if(list!=null&&list.size()>0){
                    c_user_address.setStatus(0);
                }else {
                    c_user_address.setStatus(1);
                }
                c_user_address.setAddress_id(AttaUtils.getNo());
                c_user_addressMapper.insertSelective(c_user_address);
            }
        }catch (Exception e){
            code=502;
            e.printStackTrace();
        }

        return code;
    }

    @Transactional
    public Integer updateStatus(String address_id,String c_user_id){
        Integer code=200;
        try {
            c_user_addressMapper.updateAddressStatus(c_user_id);//将所有状态改为非默认状态
            c_user_addressMapper.updateStatus(address_id);//修改默认值
        }catch (Exception e){
            e.printStackTrace();
            code=502;
            throw new RuntimeException();
        }
        return code;
    }

    public Integer deleteAddress(String address_id){
        Integer code=200;
        try {
            c_user_addressMapper.deleteByPrimaryKey(address_id);
        }catch (Exception e){
            e.printStackTrace();
            code=502;
        }
        return code;
    }

    public List<T_city> getCityInfo(T_city t_city){
        //使用redis存储省市区
        /*String key="*_"+t_city.getParid()+"_"+t_city.getType();
        Set<String> set=redisService.getPatten(key);
        List<T_city> list=new ArrayList<>();
        Iterator<String> it=set.iterator();
        while (it.hasNext()){
            String result=it.next();
            String value=redisService.getStr(result);
            JSONObject jsonObject=JSONObject.fromObject(value);
            T_city city=(T_city) JSONObject.toBean(jsonObject,T_city.class);
            list.add(city);
        }

        Collections.sort(list, new Comparator<T_city>() {
            @Override
            public int compare(T_city o1, T_city o2) {

                return Integer.parseInt(o1.getId())-Integer.parseInt(o2.getId());
            }
        });
        return list;*/
        return c_user_addressMapper.getCityInfo(t_city);
    }

    public List<T_city> getAllCityInfo(){
        return c_user_addressMapper.getAllCityInfo();
    }
}

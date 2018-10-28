package com.wm.edu.mapper.cuser;

import com.wm.edu.model.base.T_city;
import com.wm.edu.model.cuser.C_user_address;

import java.util.List;

public interface C_user_addressMapper {
    int deleteByPrimaryKey(String address_id);

    int insert(C_user_address record);

    int insertSelective(C_user_address record);

    /**
     * 根据ID获取用户信息
     * @param address_id
     * @return
     */
    C_user_address selectByPrimaryKey(String address_id);

    int updateByPrimaryKeySelective(C_user_address record);

    int updateByPrimaryKey(C_user_address record);

    /**
     * 获取用户地址
     * @param c_user_id
     * @return
     */
    List<C_user_address> getAddressList(String c_user_id);

    void updateStatus(String address_id);

    void updateAddressStatus(String c_user_id);

    /**
     * 获取默认地址
     * @param c_user_id
     * @return
     */
    C_user_address getMrAdderss(String c_user_id);

    /**
     * 获取城市信息
     * @param t_city
     * @return
     */
    List<T_city> getCityInfo(T_city t_city);

    /**
     * 获取省份城市所有信息
     * @return
     */
    List<T_city> getAllCityInfo();
}
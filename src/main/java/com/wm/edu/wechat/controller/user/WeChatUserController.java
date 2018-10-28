package com.wm.edu.wechat.controller.user;

import com.wm.edu.model.base.T_city;
import com.wm.edu.model.cuser.C_user_address;
import com.wm.edu.model.cuser.C_user_info;
import com.wm.edu.model.store.T_store_info;
import com.wm.edu.service.coupons.UserCouponsService;
import com.wm.edu.service.cuser.UserAddressService;
import com.wm.edu.service.cuser.UserInfoService;
import com.wm.edu.service.store.StoreInfoService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.common.ResultBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wechat/user")
public class WeChatUserController {

    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private StoreInfoService storeInfoService;

    @Autowired
    private UserCouponsService userCouponsService;

    /**
     *
     * @return
     */
    @RequestMapping("/getAddressList")
    public ResultBody getAddressList(String c_user_id){
        ResultBody body=new ResultBody();
        //String user_id="123";
        List<C_user_address> list=userAddressService.getAddressList(c_user_id);
        body.setData(list);
        return body;
    }

    /**
     * 根据ID获取地址信息
     * @param address_id
     * @return
     */
    @RequestMapping("/getAddressById")
    public ResultBody getAddressById(String address_id){
        ResultBody body=new ResultBody();
        C_user_address c_user_address=userAddressService.getAddreddById(address_id);
        c_user_address.setProvince(c_user_address.getProvince()+","+c_user_address.getCity()+","+c_user_address.getCounty());
        body.setData(c_user_address);
        return body;
    }

    /**
     *
     * @param c_user_address
     * @return
     */
    @RequestMapping("/saveAddress")
    public ResultBody saveAddress(C_user_address c_user_address){
        ResultBody body=new ResultBody();
        //String c_user_id="123";
        //c_user_address.setC_user_id(c_user_id);
        String str=c_user_address.getProvince();
        if(StringUtils.isNotEmpty(str)){
            String[] arr=str.split(",");
            c_user_address.setProvince(arr[0]);
            c_user_address.setCity(arr[1]);
            c_user_address.setCounty(arr[2]);
        }
        //c_user_address.setLabel(0);
        Integer code=userAddressService.saveAddress(c_user_address);
        body.setCode(code);
        return body;
    }
    @RequestMapping("/updateStatus")
    public ResultBody updateStatus(String address_id,String c_user_id){
        ResultBody body=new ResultBody();
        //String c_user_id="123";
        Integer code=userAddressService.updateStatus(address_id,c_user_id);
        body.setCode(code);
        return body;
    }

    /**
     * 删除地址
     * @param address_id
     * @return
     */
    @RequestMapping("/deleteAddress")
    public ResultBody deleteAddress(String address_id){
        ResultBody body=new ResultBody();
        Integer code=userAddressService.deleteAddress(address_id);
        body.setCode(code);
        return body;
    }

    /**
     * 获取所有省市区
     * @param t_city
     * @return
     */
    @RequestMapping("/getCityInfo")
    public ResultBody getCityInfo(T_city t_city){
        ResultBody body=new ResultBody();
        long start=System.currentTimeMillis();
        if(StringUtils.isEmpty(t_city.getId())){
            t_city.setParid("none");
            t_city.setType(1);
        }else {
            t_city.setParid(t_city.getId());
            //t_city.setType(1);
        }
        List<T_city> list=userAddressService.getCityInfo(t_city);
        body.setData(list);
        long end=System.currentTimeMillis();
        System.out.println("执行时间"+(end-start));
        return body;
    }

    @RequestMapping("/getStore")
    public ResultBody getStore(){
        ResultBody body=new ResultBody();
        String store_id=AttaUtils.getConfigValueByKey("store_id");
        T_store_info t_store_info=storeInfoService.getStoreInfoById(store_id);
        body.setData(t_store_info);
        return body;
    }

    /**
     * 更新用户信息
     * @param c_user_info
     * @return
     */
    @RequestMapping("/updateUser")
    public ResultBody updateUser(C_user_info c_user_info){
        ResultBody body=new ResultBody();
        Integer code=userInfoService.updateUserInfo(c_user_info);
        body.setCode(code);
        return body;
    }

    /**
     * 用户中心
     * @param c_user_id
     * @return
     */
    @RequestMapping("/personCenter")
    public ResultBody personCenter(String c_user_id){
        ResultBody body=new ResultBody();
        Map<String,Object> map=new HashMap<>();
        Integer coupons_num=userCouponsService.getUserCouponsNum(c_user_id);
        map.put("coupons_num",coupons_num);
        body.setData(map);
        return body;
    }

}

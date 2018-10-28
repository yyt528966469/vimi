package com.wm.edu.model.order;

import com.wm.edu.model.base.PageEntity;
import com.wm.edu.model.commodity.T_commodity_info;
import com.wm.edu.model.coupons.T_coupons;
import com.wm.edu.model.cuser.C_user_info;
import com.wm.edu.model.store.T_store_info;
import com.wm.edu.utils.common.AttaUtils;

import java.util.Date;
import java.util.List;

public class T_order extends PageEntity {
    private String order_id;

    private String c_user_id;

    private Float pay_price;

    private Float real_price;

    private Date order_time;

    private Date pay_time;

    private Integer pay_type;

    private Integer status;

    private Integer eat_type;

    private Date over_time;

    private String reserved1;

    private String reserved2;

    private String reserved3;

    private String store_id;

    private String coupons_id;//优惠劵ID

    private String remarks;//备注

    private C_user_info c_user_info;//用户信息

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    private List<T_order_commodity> commodity_infoList;//商品信息

    private T_order_address t_order_address;//联系人信息

    private T_store_info t_store_info;//商店信息

    private String status_name;//状态名

    private T_order_arrive t_order_arrive;

    private T_coupons t_coupons;

    private T_order_scavenging t_order_scavenging;//店内扫码订单

    private T_order_shop_address t_order_shop_address;

    public String getCoupons_id() {
        return coupons_id;
    }

    public void setCoupons_id(String coupons_id) {
        this.coupons_id = coupons_id;
    }

    public T_coupons getT_coupons() {
        return t_coupons;
    }

    public void setT_coupons(T_coupons t_coupons) {
        this.t_coupons = t_coupons;
    }

    public T_order_shop_address getT_order_shop_address() {
        return t_order_shop_address;
    }

    public void setT_order_shop_address(T_order_shop_address t_order_shop_address) {
        this.t_order_shop_address = t_order_shop_address;
    }

    public T_order_scavenging getT_order_scavenging() {
        return t_order_scavenging;
    }

    public void setT_order_scavenging(T_order_scavenging t_order_scavenging) {
        this.t_order_scavenging = t_order_scavenging;
    }

    public T_order_arrive getT_order_arrive() {
        return t_order_arrive;
    }

    public void setT_order_arrive(T_order_arrive t_order_arrive) {
        this.t_order_arrive = t_order_arrive;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public T_store_info getT_store_info() {
        return t_store_info;
    }

    public void setT_store_info(T_store_info t_store_info) {
        this.t_store_info = t_store_info;
    }

    public String getStatus_name() {
        if(status!=null){
            status_name=OrderStauteEnum.getName(status);
        }
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public T_order_address getT_order_address() {
        return t_order_address;
    }


    public void setT_order_address(T_order_address t_order_address) {
        this.t_order_address = t_order_address;
    }

    public List<T_order_commodity> getCommodity_infoList() {
        return commodity_infoList;
    }

    public void setCommodity_infoList(List<T_order_commodity> commodity_infoList) {
        this.commodity_infoList = commodity_infoList;
    }

    public C_user_info getC_user_info() {
        return c_user_info;
    }

    public void setC_user_info(C_user_info c_user_info) {
        this.c_user_info = c_user_info;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id == null ? null : order_id.trim();
    }

    public String getC_user_id() {
        return c_user_id;
    }

    public void setC_user_id(String c_user_id) {
        this.c_user_id = c_user_id == null ? null : c_user_id.trim();
    }

    public Float getPay_price() {
        return pay_price;
    }

    public void setPay_price(Float pay_price) {
        this.pay_price = pay_price;
    }

    public Float getReal_price() {
        return real_price;
    }

    public void setReal_price(Float real_price) {
        this.real_price = real_price;
    }

    public Date getOrder_time() {
        return order_time;
    }

    public void setOrder_time(Date order_time) {
        this.order_time = order_time;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Integer getPay_type() {
        return pay_type;
    }

    public void setPay_type(Integer pay_type) {
        this.pay_type = pay_type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getEat_type() {
        return eat_type;
    }

    public void setEat_type(Integer eat_type) {
        this.eat_type = eat_type;
    }

    public Date getOver_time() {
        return over_time;
    }

    public void setOver_time(Date over_time) {
        this.over_time = over_time;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }

    public String getReserved3() {
        if(order_time!=null){
            reserved3=AttaUtils.dateToStr(order_time);
        }
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3 == null ? null : reserved3.trim();
    }
}
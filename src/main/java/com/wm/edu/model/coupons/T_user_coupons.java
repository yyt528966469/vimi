package com.wm.edu.model.coupons;

import java.util.Date;

public class T_user_coupons {
    private String id;

    private String c_user_id;

    private String coupons_id;

    private Date create_time;

    private Date over_time;

    private Float sum_price;//查询用

    private Integer status;

    private T_coupons t_coupons;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getSum_price() {
        return sum_price;
    }

    public void setSum_price(Float sum_price) {
        this.sum_price = sum_price;
    }

    public Date getOver_time() {
        return over_time;
    }

    public void setOver_time(Date over_time) {
        this.over_time = over_time;
    }



    public T_coupons getT_coupons() {
        return t_coupons;
    }

    public void setT_coupons(T_coupons t_coupons) {
        this.t_coupons = t_coupons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getC_user_id() {
        return c_user_id;
    }

    public void setC_user_id(String c_user_id) {
        this.c_user_id = c_user_id == null ? null : c_user_id.trim();
    }

    public String getCoupons_id() {
        return coupons_id;
    }

    public void setCoupons_id(String coupons_id) {
        this.coupons_id = coupons_id == null ? null : coupons_id.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
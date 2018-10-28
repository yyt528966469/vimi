package com.wm.edu.model.vip;

import com.wm.edu.model.base.PageEntity;

import java.util.Date;

public class C_user_vip extends PageEntity {
    private String id;

    private String name;

    private String phone;

    private Date create_time;

    private String c_user_id;

    private String reserved1;

    private Float balance;

    private Integer integral;//积分

    private Float discount;//折扣

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getC_user_id() {
        return c_user_id;
    }

    public void setC_user_id(String c_user_id) {
        this.c_user_id = c_user_id == null ? null : c_user_id.trim();
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }
}
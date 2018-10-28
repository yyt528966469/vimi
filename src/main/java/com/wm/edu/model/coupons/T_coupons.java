package com.wm.edu.model.coupons;

import com.wm.edu.model.base.PageEntity;

import java.util.Date;

public class T_coupons extends PageEntity {
    private String coupons_id;

    private Integer coupons_price;

    private Integer qs_je;

    private Date create_time;

    private Integer effective_day;

    private String reserved1;

    private Integer lq_status;//是否领取，0未领取，1已领取

    public Integer getLq_status() {
        return lq_status;
    }

    public void setLq_status(Integer lq_status) {
        this.lq_status = lq_status;
    }

    public String getCoupons_id() {
        return coupons_id;
    }

    public void setCoupons_id(String coupons_id) {
        this.coupons_id = coupons_id == null ? null : coupons_id.trim();
    }

    public Integer getCoupons_price() {
        return coupons_price;
    }

    public void setCoupons_price(Integer coupons_price) {
        this.coupons_price = coupons_price;
    }

    public Integer getQs_je() {
        return qs_je;
    }

    public void setQs_je(Integer qs_je) {
        this.qs_je = qs_je;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getEffective_day() {
        return effective_day;
    }

    public void setEffective_day(Integer effective_day) {
        this.effective_day = effective_day;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }
}
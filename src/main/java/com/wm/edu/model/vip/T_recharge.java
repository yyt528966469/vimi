package com.wm.edu.model.vip;

public class T_recharge {
    private String id;

    private Float price;

    private Float pay_price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getPay_price() {
        return pay_price;
    }

    public void setPay_price(Float pay_price) {
        this.pay_price = pay_price;
    }
}
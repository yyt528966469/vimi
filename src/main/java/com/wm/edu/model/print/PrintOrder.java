package com.wm.edu.model.print;

import java.util.List;

public class PrintOrder {
    private String title;
    private String name;
    private String phone;
    private String orderTime;
    private String orderNo;
    private String shop;
    private String total;
    private String totalCount;

    private String address;

    private String remarks;

    //private String sex;

    private String packing_price;

    private String distribution_price;

    private List<Goods> goodsList;

    private String table_num;

    private String person_num;

    public String getTable_num() {
        return table_num;
    }

    public void setTable_num(String table_num) {
        this.table_num = table_num;
    }

    public String getPerson_num() {
        return person_num;
    }

    public void setPerson_num(String person_num) {
        this.person_num = person_num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPacking_price() {
        return packing_price;
    }

    public void setPacking_price(String packing_price) {
        this.packing_price = packing_price;
    }

    public String getDistribution_price() {
        return distribution_price;
    }

    public void setDistribution_price(String distribution_price) {
        this.distribution_price = distribution_price;
    }
}

package com.wm.edu.model.order;


import java.util.Date;

public class T_order_address {
    private String order_address_id;

    private String order_id;

    private String address_id;

    private String name;

    private String phone;

    private String province;

    private String city;

    private String county;

    private String details_addr;

    private String reserved1;

    private Float packing_price;

    private Float distribution_price;

    private String sex;

    private Integer label;

    private Date arrive_time;

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Float getPacking_price() {
        return packing_price;
    }

    public void setPacking_price(Float packing_price) {
        this.packing_price = packing_price;
    }

    public Float getDistribution_price() {
        return distribution_price;
    }

    public void setDistribution_price(Float distribution_price) {
        this.distribution_price = distribution_price;
    }

    public String getOrder_address_id() {
        return order_address_id;
    }

    public void setOrder_address_id(String order_address_id) {
        this.order_address_id = order_address_id == null ? null : order_address_id.trim();
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id == null ? null : order_id.trim();
    }

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id == null ? null : address_id.trim();
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getDetails_addr() {
        return details_addr;
    }

    public void setDetails_addr(String details_addr) {
        this.details_addr = details_addr == null ? null : details_addr.trim();
    }

    public Date getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(Date arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }
}
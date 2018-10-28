package com.wm.edu.model.order;

import java.text.DecimalFormat;

public class T_order_commodity {
    private String id;

    private String order_id;

    private String commodity_id;

    private Integer comm_num;

    private Float sum_price;

    private String commodity_name;

    private Float commodity_price;

    private String reserved1;

    private String img_src;

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id == null ? null : order_id.trim();
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id == null ? null : commodity_id.trim();
    }

    public Integer getComm_num() {
        return comm_num;
    }

    public void setComm_num(Integer comm_num) {
        this.comm_num = comm_num;
    }

    public Float getSum_price() {
        return sum_price;
    }

    public void setSum_price(Float sum_price) {
        this.sum_price = sum_price;
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name == null ? null : commodity_name.trim();
    }

    public String getCommodity_price() {
        String str=null;
        if(commodity_price!=null){
            DecimalFormat df = new DecimalFormat("#0.00");
            str=df.format(commodity_price);
        }
        return str;
    }

    public void setCommodity_price(Float commodity_price) {
        this.commodity_price = commodity_price;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }
}
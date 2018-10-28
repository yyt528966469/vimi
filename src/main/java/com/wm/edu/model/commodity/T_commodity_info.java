package com.wm.edu.model.commodity;

import com.wm.edu.model.base.PageEntity;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

public class T_commodity_info extends PageEntity {
    private String commodity_id;

    private String commodity_name;

    private String commodity_type;

    private Float price;

    private String img_src;

    private String commodity_introduction;

    private Integer status;

    private String reserved1;

    private String reserved2;

    private Date create_time;

    private Integer mouth_num;

    private Integer is_recommend;

    private Integer weight;

    private T_commodity_type t_commodity_type;

    private List<T_commodity_photo> photoList;

    private Integer cart_num;//用户购物车该商品的数量

    private Integer comm_num;//用户购物车该商品的数量

    public Integer getCart_num() {
        return cart_num;
    }

    public void setCart_num(Integer cart_num) {
        this.cart_num = cart_num;
    }

    public Integer getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(Integer is_recommend) {
        this.is_recommend = is_recommend;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<T_commodity_photo> getPhotoList() {
        return photoList;
    }

    public Integer getMouth_num() {
        return mouth_num;
    }

    public void setMouth_num(Integer mouth_num) {
        this.mouth_num = mouth_num;
    }

    public void setPhotoList(List<T_commodity_photo> photoList) {
        this.photoList = photoList;
    }

    public T_commodity_type getT_commodity_type() {
        return t_commodity_type;
    }

    public void setT_commodity_type(T_commodity_type t_commodity_type) {
        this.t_commodity_type = t_commodity_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id == null ? null : commodity_id.trim();
    }

    public String getCommodity_name() {
        return commodity_name;
    }

    public void setCommodity_name(String commodity_name) {
        this.commodity_name = commodity_name == null ? null : commodity_name.trim();
    }

    public String getCommodity_type() {
        return commodity_type;
    }

    public void setCommodity_type(String commodity_type) {
        this.commodity_type = commodity_type == null ? null : commodity_type.trim();
    }

    public String getPrice() {
        String str=null;
        if(price!=null){
            DecimalFormat df = new DecimalFormat("#0.00");
            str=df.format(price);
        }
        return str;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src == null ? null : img_src.trim();
    }

    public String getCommodity_introduction() {
        return commodity_introduction;
    }

    public void setCommodity_introduction(String commodity_introduction) {
        this.commodity_introduction = commodity_introduction == null ? null : commodity_introduction.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }


    public Integer getComm_num() {
        return cart_num;
    }

    public void setComm_num(Integer comm_num) {
        this.cart_num = comm_num;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2 == null ? null : reserved2.trim();
    }
}
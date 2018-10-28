package com.wm.edu.model.commodity;

import com.wm.edu.model.base.PageEntity;

import java.util.Date;

public class T_commodity_type extends PageEntity {
    private String type_id;

    private String type_name;

    private Integer sort;

    private Date create_time;

    private String img;

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id == null ? null : type_id.trim();
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name == null ? null : type_name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }
}
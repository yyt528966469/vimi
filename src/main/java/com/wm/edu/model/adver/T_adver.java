package com.wm.edu.model.adver;

import com.wm.edu.model.base.PageEntity;

import java.util.Date;

public class T_adver extends PageEntity {
    private String adver_id;

    private String adver_img;

    private Integer sort;

    private Integer status;

    private Date create_time;

    private String adver_name;

    private String adver_url;

    public String getAdver_id() {
        return adver_id;
    }

    public void setAdver_id(String adver_id) {
        this.adver_id = adver_id == null ? null : adver_id.trim();
    }

    public String getAdver_img() {
        return adver_img;
    }

    public void setAdver_img(String adver_img) {
        this.adver_img = adver_img == null ? null : adver_img.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getAdver_name() {
        return adver_name;
    }

    public void setAdver_name(String adver_name) {
        this.adver_name = adver_name == null ? null : adver_name.trim();
    }

    public String getAdver_url() {
        return adver_url;
    }

    public void setAdver_url(String adver_url) {
        this.adver_url = adver_url == null ? null : adver_url.trim();
    }
}
package com.wm.edu.model.commodity;

public class T_commodity_photo {
    private String photo_id;

    private String commodity_id;

    private String img_url;

    private Integer sort;

    private Integer img_type;

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id == null ? null : photo_id.trim();
    }

    public String getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(String commodity_id) {
        this.commodity_id = commodity_id == null ? null : commodity_id.trim();
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url == null ? null : img_url.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getImg_type() {
        return img_type;
    }

    public void setImg_type(Integer img_type) {
        this.img_type = img_type;
    }
}
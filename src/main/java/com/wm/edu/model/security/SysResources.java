package com.wm.edu.model.security;

import com.wm.edu.model.base.PageEntity;

import java.util.ArrayList;
import java.util.List;

public class SysResources extends PageEntity {
    private Integer res_id;

    private String name;

    public String res_name;

    private Integer parent_id;

    private String par_name;

    private Integer res_type;

    private String res_url;

    private String img;

    private SysResources parentResources;

    private List<SysResources> children=new ArrayList<>();

    public String getPar_name() {
        return par_name;
    }

    public void setPar_name(String par_name) {
        this.par_name = par_name;
    }

    public SysResources getParentResources() {
        return parentResources;
    }

    public void setParentResources(SysResources parentResources) {
        this.parentResources = parentResources;
    }

    public List<SysResources> getChildren() {
        return children;
    }

    public void setChildren(List<SysResources> children) {
        this.children = children;
    }

    public Integer getRes_id() {
        return res_id;
    }

    public void setRes_id(Integer res_id) {
        this.res_id = res_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getRes_type() {
        return res_type;
    }

    public void setRes_type(Integer res_type) {
        this.res_type = res_type;
    }

    public String getRes_url() {
        return res_url;
    }

    public void setRes_url(String res_url) {
        this.res_url = res_url == null ? null : res_url.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getRes_name() {
        return res_name;
    }

    public void setRes_name(String res_name) {
        this.res_name = res_name;
    }
}
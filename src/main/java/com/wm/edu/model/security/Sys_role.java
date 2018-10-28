package com.wm.edu.model.security;

import com.wm.edu.model.base.PageEntity;

import java.util.ArrayList;
import java.util.List;

public class Sys_role extends PageEntity {
    private Integer id;
    private String name;

    private List<SysResources> sysResList = new ArrayList<SysResources>();

    public List<SysResources> getSysResList() {
        return sysResList;
    }

    public void setSysResList(List<SysResources> sysResList) {
        this.sysResList = sysResList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

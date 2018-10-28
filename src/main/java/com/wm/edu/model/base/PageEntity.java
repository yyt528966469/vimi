package com.wm.edu.model.base;

import org.apache.commons.lang3.StringUtils;

public class PageEntity {
    Integer page=1;//默认从第一页
    Integer rows=20;//默认每页20条
    String sidx;//排序字段
    String sord;//asc

    String orderStandesc;


    public String getOrderStandesc() {
        if(StringUtils.isNotEmpty(sidx)&&StringUtils.isNotEmpty(sord)){
            orderStandesc=sidx+" "+sord;
        }
        return orderStandesc;
    }

    public void setOrderStandesc(String orderStandesc) {
        this.orderStandesc = orderStandesc;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

}

package com.wm.edu.model.order;

public enum OrderStauteEnum {
    DFK("待付款",0),DJD("待接单",1),DFH("待发货",2),DSH("待收货",3),YWC("已完成",4),YQX("已取消",7);
    private String name;
    private Integer index;
    private OrderStauteEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (OrderStauteEnum c : OrderStauteEnum.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}

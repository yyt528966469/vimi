package com.wm.edu.model.reserve;

public enum ReserveEnum {

    DQR("待确认",0),YYCG("预约成功",1),YYSB("预约失败",6),YQX("已取消",7);
    private String name;
    private Integer index;
    private ReserveEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (ReserveEnum c : ReserveEnum.values()) {
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

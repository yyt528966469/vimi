package com.wm.edu.utils.common;

public enum StauteEnum {
    OK("success",200),EXCEPTION("exception",502),ERROR("error",500),CLIENT("未在指定客户端打开",508);
    private String name;
    private Integer index;
    private StauteEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 普通方法
    public static String getName(int index) {
        for (StauteEnum c : StauteEnum.values()) {
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

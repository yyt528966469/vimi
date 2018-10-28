package com.wm.edu.model.base;

public class T_city {

    private String id;
    private String name;
    private String parid;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParid() {
        return parid;
    }

    public void setParid(String parid) {
        this.parid = parid;
    }

    @Override
    public String toString() {
        return "[id="+id+",name="+name+",pid="+parid+"]";
    }
}

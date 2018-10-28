package com.wm.edu.model.order;

public class T_order_scavenging {

    private String id;
    private Integer table_num;
    private Integer person_num;
    private String order_id;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTable_num() {
        return table_num;
    }

    public void setTable_num(Integer table_num) {
        this.table_num = table_num;
    }

    public Integer getPerson_num() {
        return person_num;
    }

    public void setPerson_num(Integer person_num) {
        this.person_num = person_num;
    }
}

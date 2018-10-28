package com.wm.edu.model.store;

public class T_base {
    private String id;

    private Float packing_price;

    private Float distribution_price;

    private Integer table_start;

    private Integer table_end;

    public Integer getTable_start() {
        return table_start;
    }

    public void setTable_start(Integer table_start) {
        this.table_start = table_start;
    }

    public Integer getTable_end() {
        return table_end;
    }

    public void setTable_end(Integer table_end) {
        this.table_end = table_end;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Float getPacking_price() {
        return packing_price;
    }

    public void setPacking_price(Float packing_price) {
        this.packing_price = packing_price;
    }

    public Float getDistribution_price() {
        return distribution_price;
    }

    public void setDistribution_price(Float distribution_price) {
        this.distribution_price = distribution_price;
    }
}
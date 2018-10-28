package com.wm.edu.model.order;

public class T_order_express {
    private String express_id;

    private String name;

    private String express_num;

    private String order_id;

    public String getExpress_id() {
        return express_id;
    }

    public void setExpress_id(String express_id) {
        this.express_id = express_id == null ? null : express_id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getExpress_num() {
        return express_num;
    }

    public void setExpress_num(String express_num) {
        this.express_num = express_num == null ? null : express_num.trim();
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id == null ? null : order_id.trim();
    }
}
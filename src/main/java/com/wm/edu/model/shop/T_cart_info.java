package com.wm.edu.model.shop;

import java.text.DecimalFormat;

public class T_cart_info {
    private Integer c_shop_sum;
    private Float c_price_sum;

    public Integer getC_shop_sum() {
        String str=null;
        if(c_shop_sum==null){
            c_shop_sum=0;
        }
        return c_shop_sum;
    }

    public void setC_shop_sum(Integer c_shop_sum) {
        this.c_shop_sum = c_shop_sum;
    }

    public String getC_price_sum() {
        String str=c_price_sum+"";
        if(c_price_sum!=null){
            DecimalFormat df = new DecimalFormat("#0.00");
            str=df.format(c_price_sum);
        }else {
            str="0.00";
        }
        return str;
    }

    public void setC_price_sum(Float c_price_sum) {
        this.c_price_sum = c_price_sum;
    }
}

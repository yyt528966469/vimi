package com.wm.edu.mapper.shop;

import com.wm.edu.model.shop.T_cart_info;
import com.wm.edu.model.shop.T_shopping_cart;

import java.util.List;

public interface T_shopping_cartMapper {
    int deleteByPrimaryKey(String cart_id);

    int insert(T_shopping_cart record);

    int insertSelective(T_shopping_cart record);

    T_shopping_cart selectByPrimaryKey(String cart_id);

    int updateByPrimaryKeySelective(T_shopping_cart record);

    int updateByPrimaryKey(T_shopping_cart record);

    T_shopping_cart getCartByUserIdAndCommId(T_shopping_cart t_shopping_cart);

    List<T_shopping_cart> getCartList(T_shopping_cart t_shopping_cart);

    void deleteCartByUserId(String c_user_id);

    T_cart_info getUserCartSum(String c_user_id);
}
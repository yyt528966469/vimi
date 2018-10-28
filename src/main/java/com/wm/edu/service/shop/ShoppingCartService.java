package com.wm.edu.service.shop;

import com.wm.edu.mapper.shop.T_shopping_cartMapper;
import com.wm.edu.model.shop.T_cart_info;
import com.wm.edu.model.shop.T_shopping_cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {

    @Autowired
    private T_shopping_cartMapper t_shopping_cartMapper;

    public T_shopping_cart getCartByUserIdAndCommId(String user_id,String commodity_id){
        T_shopping_cart cart=new T_shopping_cart();
        cart.setC_user_id(user_id);
        cart.setCommodity_id(commodity_id);
        return t_shopping_cartMapper.getCartByUserIdAndCommId(cart);
    }

    public void insertCart(T_shopping_cart t_shopping_cart){
        t_shopping_cartMapper.insertSelective(t_shopping_cart);
    }

    public void updateCart(T_shopping_cart t_shopping_cart){
        t_shopping_cartMapper.updateByPrimaryKeySelective(t_shopping_cart);
    }

    public void deleteCart(String id){
        t_shopping_cartMapper.deleteByPrimaryKey(id);
    }

    /**
     * 获取列表
     * @param t_shopping_cart
     * @return
     */
    public List<T_shopping_cart> getCartList(T_shopping_cart t_shopping_cart){
        return t_shopping_cartMapper.getCartList(t_shopping_cart);
    }

    public void deleteCartByUserId(String c_user_id){
        t_shopping_cartMapper.deleteCartByUserId(c_user_id);
    }

    public T_cart_info getUserCartSum(String c_user_id){
        return t_shopping_cartMapper.getUserCartSum(c_user_id);
    }

}

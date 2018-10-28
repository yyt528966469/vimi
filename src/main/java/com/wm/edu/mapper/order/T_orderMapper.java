package com.wm.edu.mapper.order;

import com.wm.edu.model.base.Express;
import com.wm.edu.model.order.OrderReport;
import com.wm.edu.model.order.T_order;

import java.util.List;
import java.util.Map;

public interface T_orderMapper {
    int insert(T_order record);

    int insertSelective(T_order record);

    List<T_order> getOrderList(T_order t_order);

    /**
     * 电商订单
     * @param t_order
     * @return
     */
    List<T_order> getShopOrderList(T_order t_order);

    /**
     * 获取订单所有信息
     * @param order_id
     * @return
     */
    T_order getOrderById(String order_id);

    /**
     * 获取商城订单详情
     * @param order_id
     * @return
     */
    T_order getShopOrderById(String order_id);

    /**
     * 获取外卖
     * @param //order_id
     * @return
     */
    //T_order getWmOrderById(String order_id);

    void updateSelectiveById(T_order t_order);

    List<OrderReport> getWeekOrderNum();

    List<T_order> getUserOrderList(T_order t_order);

    Integer getToDayOrderNum();

    List<Express> getAllExpress();

    /**
     * 只获取订单基本信息，不获取其他对象
     * @param order_id
     * @return
     */
    T_order getOrdermin(String order_id);


}
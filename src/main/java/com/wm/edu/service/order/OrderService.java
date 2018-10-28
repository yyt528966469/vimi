package com.wm.edu.service.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.cuser.C_user_addressMapper;
import com.wm.edu.mapper.order.*;
import com.wm.edu.model.base.Express;
import com.wm.edu.model.coupons.T_coupons;
import com.wm.edu.model.coupons.T_user_coupons;
import com.wm.edu.model.cuser.C_user_address;
import com.wm.edu.model.cuser.C_user_info;
import com.wm.edu.model.order.*;
import com.wm.edu.model.shop.T_cart_info;
import com.wm.edu.model.shop.T_shopping_cart;
import com.wm.edu.model.store.T_base;
import com.wm.edu.model.store.T_store_info;
import com.wm.edu.service.coupons.CouponsService;
import com.wm.edu.service.coupons.UserCouponsService;
import com.wm.edu.service.cuser.UserAddressService;
import com.wm.edu.service.shop.ShoppingCartService;
import com.wm.edu.service.store.StoreInfoService;
import com.wm.edu.service.store.TBaseService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.pay.PayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 *
 */
@Service
public class OrderService {
    @Autowired
    private T_orderMapper t_orderMapper;
    @Autowired
    private T_order_commodityMapper t_order_commodityMapper;
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private TBaseService tBaseService;
    @Autowired
    private T_order_addressMapper t_order_addressMapper;
    @Autowired
    private StoreInfoService storeInfoService;
    @Autowired
    private OrderArriveService orderArriveService;
    @Autowired
    private OrderScavengingService orderScavengingService;
    @Autowired
    private T_order_shop_addressMapper t_order_shop_addressMapper;
    @Autowired
    private T_order_expressMapper t_order_expressMapper;

    @Autowired
    private UserCouponsService userCouponsService;
    @Autowired
    private CouponsService couponsService;


    public Page<T_order> getOrderList(T_order t_order){
        String orderStandesc=t_order.getSidx()+" "+t_order.getSord();
        Page<T_order> page=PageHelper.startPage(t_order.getPage(),t_order.getRows(),orderStandesc);
        t_orderMapper.getOrderList(t_order);
        return page;
    }

    public Page<T_order> getShopOrderList(T_order t_order){
        String orderStandesc=t_order.getSidx()+" "+t_order.getSord();
        Page<T_order> page=PageHelper.startPage(t_order.getPage(),t_order.getRows(),orderStandesc);
        t_orderMapper.getShopOrderList(t_order);
        return page;
    }

    public List<T_order> getAllOrderList(T_order t_order){
        return t_orderMapper.getOrderList(t_order);
    }

    public T_order getOrderById(String order_id){
        return t_orderMapper.getOrderById(order_id);
    }
    public T_order getShopOrderById(String order_id){
        return t_orderMapper.getShopOrderById(order_id);
    }

    /**
     * 获取简单订单信息
     * @param order_id
     * @return
     */
    public T_order getOrdermin(String order_id){
        return t_orderMapper.getOrdermin(order_id);
    }

    /**
     * 更新订单状态
     * @param t_order
     * @return
     */
    public String updateStatus(T_order t_order){
        String result="0";
        try {
            if(t_order.getStatus()==7){
                T_order order=getOrdermin(t_order.getOrder_id());
                if(order.getStatus()==1||order.getStatus()==2){
                    String reqParams =PayUtils.refundPay(order);//执行退款操作
                    Map map = PayUtils.doXMLParse(reqParams);
                    String return_code = (String) map.get("return_code");//返回状态码
                    if (return_code.equals("SUCCESS")) {
                        t_order.setOver_time(new Date());
                        t_orderMapper.updateSelectiveById(t_order);
                    }
                }else {
                    t_order.setOver_time(new Date());
                    t_orderMapper.updateSelectiveById(t_order);
                }
            }else {
                t_orderMapper.updateSelectiveById(t_order);
            }

        }catch (Exception e){
            e.printStackTrace();
            result="1";
        }
        return result;
    }

    public List<OrderReport> getWeekOrderNum(){
        return t_orderMapper.getWeekOrderNum();
    }

    public Map<String,Object> getSettlementInfo(String c_user_id,String store_id){
        Map<String,Object> map=new HashMap<>();
        C_user_address user_address=userAddressService.getMrAdderss(c_user_id);
        map.put("user_address",user_address);
        T_shopping_cart shopping_cart=new T_shopping_cart();
        shopping_cart.setC_user_id(c_user_id);
        List<T_shopping_cart> shopCartList=shoppingCartService.getCartList(shopping_cart);
        map.put("shopCartList",shopCartList);
        T_base t_base=tBaseService.getBaseById(store_id);
        map.put("t_base",t_base);
        float sum_price=0.0f;
        BigDecimal price = new BigDecimal(String.valueOf(sum_price));
        if(shopCartList!=null&&shopCartList.size()>0){
            for (T_shopping_cart cart:shopCartList){
                price=price.add(new BigDecimal(String.valueOf(cart.getSum_price())));
                //sum_price+=cart.getSum_price();
            }
        }
        if(t_base!=null){
            price=price.add(new BigDecimal(String.valueOf(t_base.getDistribution_price())));
            price=price.add(new BigDecimal(String.valueOf(t_base.getPacking_price())));
        }
        map.put("sum_price",price.floatValue());
        return map;
    }

    /**
     * 执行下单操作
     * @param t_order
     * @param address_id
     * @return
     */
    @Transactional
    public Integer toOrder(T_order t_order,String address_id,Date date){
        Integer code=200;

        try {
            float sum_price=0.0f;
            BigDecimal price = new BigDecimal(String.valueOf(sum_price));
            T_shopping_cart shopping_cart=new T_shopping_cart();
            shopping_cart.setC_user_id(t_order.getC_user_id());
            //获取购物车商品
            List<T_shopping_cart> shopCartList=shoppingCartService.getCartList(shopping_cart);
            List<T_order_commodity> commodityList=new ArrayList<>();
            if(shopCartList!=null&&shopCartList.size()>0){//计算所有商品总价
                for (T_shopping_cart cart:shopCartList){
                    T_order_commodity commodity=new T_order_commodity();//将购物车对象加入订单商品中
                    price=price.add(new BigDecimal(String.valueOf(cart.getSum_price())));
                    commodity.setId(AttaUtils.getNo());
                    commodity.setComm_num(cart.getComm_num());
                    commodity.setCommodity_id(cart.getCommodity_id());
                    commodity.setCommodity_name(cart.getCommodity_name());
                    commodity.setCommodity_price(Float.parseFloat(cart.getCommodity_price()));
                    commodity.setSum_price(cart.getSum_price());
                    commodity.setOrder_id(t_order.getOrder_id());
                    commodity.setImg_src(cart.getImg_src());
                    commodityList.add(commodity);
                    //sum_price+=cart.getSum_price();
                }
            }
            T_base t_base=tBaseService.getBaseById(t_order.getStore_id());
            if(t_base!=null){//包装费和配送费
                price=price.add(new BigDecimal(String.valueOf(t_base.getDistribution_price())));
                price=price.add(new BigDecimal(String.valueOf(t_base.getPacking_price())));
            }
            t_order.setPay_price(price.floatValue());
            /**
             * 这里要判断是否存在优惠劵
             */
            //int totle_price=(int)(price.floatValue()*100);//将金额单位转化为分
            t_order.setReal_price(price.floatValue());
            t_order.setPay_type(0);//在线支付
            t_order.setStatus(0);//待支付

           // t_order.setC_user_id(c_user_id);
           // t_order.setStore_id(store_id);
            //t_order.setEat_type(0);//0，快递，1到店
            t_order.setOrder_time(new Date());
            t_orderMapper.insertSelective(t_order);//新增订单
            shoppingCartService.deleteCartByUserId(t_order.getC_user_id());//删除购物车商品
            t_order_commodityMapper.insertOrderCommodityList(commodityList);//添加至订单商品表中
            if(t_order.getEat_type()==0){
                //查询用户收件地址
                C_user_address user_address=userAddressService.getAddreddById(address_id);
                T_order_address order_address=new T_order_address();
                order_address.setAddress_id(user_address.getAddress_id());
                order_address.setCity(user_address.getCity());
                order_address.setCounty(user_address.getCounty());
                order_address.setDetails_addr(user_address.getDetails_addr());
                order_address.setName(user_address.getName());
                order_address.setOrder_address_id(AttaUtils.getNo());
                order_address.setOrder_id(t_order.getOrder_id());
                order_address.setPhone(user_address.getPhone());
                order_address.setProvince(user_address.getProvince());
                order_address.setPacking_price(t_base.getPacking_price());
                order_address.setDistribution_price(t_base.getDistribution_price());
                order_address.setSex(user_address.getSex());
                order_address.setArrive_time(date);
                t_order_addressMapper.insertSelective(order_address);
            }

        }catch (Exception e){
            e.printStackTrace();
            code=502;
            throw new RuntimeException();
        }
        return code;
    }

    /**
     * 到店下单
     * @param t_order
     * @param phone
     * @param date
     * @return
     */
    @Transactional
    public Integer toDdOrder(T_order t_order,String phone,Date date){
        Integer code=200;

        try {
            float sum_price=0.0f;
            BigDecimal price = new BigDecimal(String.valueOf(sum_price));
            T_shopping_cart shopping_cart=new T_shopping_cart();
            shopping_cart.setC_user_id(t_order.getC_user_id());
            //获取购物车商品
            List<T_shopping_cart> shopCartList=shoppingCartService.getCartList(shopping_cart);
            List<T_order_commodity> commodityList=new ArrayList<>();
            if(shopCartList!=null&&shopCartList.size()>0){//计算所有商品总价
                for (T_shopping_cart cart:shopCartList){
                    T_order_commodity commodity=new T_order_commodity();//将购物车对象加入订单商品中
                    price=price.add(new BigDecimal(String.valueOf(cart.getSum_price())));
                    commodity.setId(AttaUtils.getNo());
                    commodity.setComm_num(cart.getComm_num());
                    commodity.setCommodity_id(cart.getCommodity_id());
                    commodity.setCommodity_name(cart.getCommodity_name());
                    commodity.setCommodity_price(Float.parseFloat(cart.getCommodity_price()));
                    commodity.setSum_price(cart.getSum_price());
                    commodity.setOrder_id(t_order.getOrder_id());
                    commodity.setImg_src(cart.getImg_src());
                    commodityList.add(commodity);
                    //sum_price+=cart.getSum_price();
                }
            }
            /*T_base t_base=tBaseService.getBaseById(t_order.getStore_id());
            if(t_base!=null){//包装费和配送费
                price=price.add(new BigDecimal(String.valueOf(t_base.getDistribution_price())));
                price=price.add(new BigDecimal(String.valueOf(t_base.getPacking_price())));
            }*/
            t_order.setPay_price(price.floatValue());
            /**
             * 这里要判断是否存在优惠劵
             */
            //int totle_price=(int)(price.floatValue()*100);//将金额单位转化为分
            t_order.setReal_price(price.floatValue());
            t_order.setPay_type(0);//在线支付
            t_order.setStatus(0);//待支付

            // t_order.setC_user_id(c_user_id);
            // t_order.setStore_id(store_id);
            //t_order.setEat_type(0);//0，快递，1到店
            t_order.setOrder_time(new Date());
            t_orderMapper.insertSelective(t_order);//新增订单
            shoppingCartService.deleteCartByUserId(t_order.getC_user_id());//删除购物车商品
            t_order_commodityMapper.insertOrderCommodityList(commodityList);//添加至订单商品表中
            if(t_order.getEat_type()==1){
                T_order_arrive t_order_arrive=new T_order_arrive();
                t_order_arrive.setId(AttaUtils.getNo());
                t_order_arrive.setOrder_id(t_order.getOrder_id());
                t_order_arrive.setPhone(phone);
                t_order_arrive.setArrive_time(date);
                orderArriveService.addOrderArrive(t_order_arrive);
            }
        }catch (Exception e){
            e.printStackTrace();
            code=502;
            throw new RuntimeException();
        }
        return code;
    }


    /**
     * 执行下单操作
     * @param t_order
     * @param address_id
     * @return
     */
    @Transactional
    public Integer toShopOrder(T_order t_order,String address_id,String coupons_id){
        Integer code=200;

        try {
            float sum_price=0.0f;
            BigDecimal price = new BigDecimal(String.valueOf(sum_price));
            T_shopping_cart shopping_cart=new T_shopping_cart();
            shopping_cart.setC_user_id(t_order.getC_user_id());
            //获取购物车商品
            List<T_shopping_cart> shopCartList=shoppingCartService.getCartList(shopping_cart);
            List<T_order_commodity> commodityList=new ArrayList<>();
            if(shopCartList!=null&&shopCartList.size()>0){//计算所有商品总价
                for (T_shopping_cart cart:shopCartList){
                    T_order_commodity commodity=new T_order_commodity();//将购物车对象加入订单商品中
                    price=price.add(new BigDecimal(String.valueOf(cart.getSum_price())));
                    commodity.setId(AttaUtils.getNo());
                    commodity.setComm_num(cart.getComm_num());
                    commodity.setCommodity_id(cart.getCommodity_id());
                    commodity.setCommodity_name(cart.getCommodity_name());
                    commodity.setCommodity_price(Float.parseFloat(cart.getCommodity_price()));
                    commodity.setSum_price(cart.getSum_price());
                    commodity.setOrder_id(t_order.getOrder_id());
                    commodity.setImg_src(cart.getImg_src());
                    commodityList.add(commodity);
                    //sum_price+=cart.getSum_price();
                }
            }
            //T_base t_base=tBaseService.getBaseById(t_order.getStore_id());
            /*if(t_base!=null){//包装费和配送费
                price=price.add(new BigDecimal(String.valueOf(t_base.getDistribution_price())));
                price=price.add(new BigDecimal(String.valueOf(t_base.getPacking_price())));
            }*/
            t_order.setPay_price(price.floatValue());
            /**
             * 这里要判断是否存在优惠劵
             */

            T_coupons t_coupons=couponsService.getCouponsById(coupons_id);
            if(t_coupons!=null){
                price=price.subtract(new BigDecimal(String.valueOf(t_coupons.getCoupons_price())));
                T_user_coupons t_user_coupons=new T_user_coupons();
                t_user_coupons.setC_user_id(t_order.getC_user_id());
                t_user_coupons.setCoupons_id(coupons_id);
                t_user_coupons.setStatus(1);
                userCouponsService.updateUserCouspons(t_user_coupons);
            }

            //int totle_price=(int)(price.floatValue()*100);//将金额单位转化为分
            t_order.setReal_price(price.floatValue());
            t_order.setPay_type(0);//在线支付
            t_order.setStatus(0);//待支付

            // t_order.setC_user_id(c_user_id);
            // t_order.setStore_id(store_id);
            //t_order.setEat_type(0);//0，快递，1到店
            t_order.setOrder_time(new Date());
            t_orderMapper.insertSelective(t_order);//新增订单
            shoppingCartService.deleteCartByUserId(t_order.getC_user_id());//删除购物车商品
            t_order_commodityMapper.insertOrderCommodityList(commodityList);//添加至订单商品表中
            if(t_order.getEat_type()==3){
                //查询用户收件地址
                C_user_address user_address=userAddressService.getAddreddById(address_id);
                T_order_shop_address order_address=new T_order_shop_address();
                order_address.setAddress_id(user_address.getAddress_id());
                order_address.setCity(user_address.getCity());
                order_address.setCounty(user_address.getCounty());
                order_address.setDetails_addr(user_address.getDetails_addr());
                order_address.setName(user_address.getName());
                order_address.setOrder_address_id(AttaUtils.getNo());
                order_address.setOrder_id(t_order.getOrder_id());
                order_address.setPhone(user_address.getPhone());
                order_address.setProvince(user_address.getProvince());
                //order_address.setPostage();//邮费
                //t_order_addressMapper.insertSelective(order_address);
                t_order_shop_addressMapper.insertSelective(order_address);
            }

        }catch (Exception e){
            e.printStackTrace();
            code=502;
            throw new RuntimeException();
        }
        return code;
    }

    public Integer toDnOrder(T_order t_order,T_order_scavenging t_order_scavenging){
        Integer code=200;

        try {
            float sum_price=0.0f;
            BigDecimal price = new BigDecimal(String.valueOf(sum_price));
            T_shopping_cart shopping_cart=new T_shopping_cart();
            shopping_cart.setC_user_id(t_order.getC_user_id());
            //获取购物车商品
            List<T_shopping_cart> shopCartList=shoppingCartService.getCartList(shopping_cart);
            List<T_order_commodity> commodityList=new ArrayList<>();
            if(shopCartList!=null&&shopCartList.size()>0){//计算所有商品总价
                for (T_shopping_cart cart:shopCartList){
                    T_order_commodity commodity=new T_order_commodity();//将购物车对象加入订单商品中
                    price=price.add(new BigDecimal(String.valueOf(cart.getSum_price())));
                    commodity.setId(AttaUtils.getNo());
                    commodity.setComm_num(cart.getComm_num());
                    commodity.setCommodity_id(cart.getCommodity_id());
                    commodity.setCommodity_name(cart.getCommodity_name());
                    commodity.setCommodity_price(Float.parseFloat(cart.getCommodity_price()));
                    commodity.setSum_price(cart.getSum_price());
                    commodity.setOrder_id(t_order.getOrder_id());
                    commodity.setImg_src(cart.getImg_src());
                    commodityList.add(commodity);
                }
            }

            t_order.setPay_price(price.floatValue());
            /**
             * 这里要判断是否存在优惠劵
             */
            //int totle_price=(int)(price.floatValue()*100);//将金额单位转化为分
            t_order.setReal_price(price.floatValue());
            t_order.setPay_type(0);//在线支付
            t_order.setStatus(0);//待支付

            t_order.setOrder_time(new Date());
            t_orderMapper.insertSelective(t_order);//新增订单
            shoppingCartService.deleteCartByUserId(t_order.getC_user_id());//删除购物车商品
            t_order_commodityMapper.insertOrderCommodityList(commodityList);//添加至订单商品表中
            if(t_order.getEat_type()==2){
                t_order_scavenging.setId(AttaUtils.getNo());
                t_order_scavenging.setOrder_id(t_order.getOrder_id());
                orderScavengingService.insertSelective(t_order_scavenging);
            }
        }catch (Exception e){
            e.printStackTrace();
            code=502;
            throw new RuntimeException();
        }
        return code;
    }

    public Page<T_order> getUserOrderList(T_order t_order){
        String orderStandesc=t_order.getSidx()+" "+t_order.getSord();
        Page<T_order> page=PageHelper.startPage(t_order.getPage(),t_order.getRows(),orderStandesc);
        t_orderMapper.getUserOrderList(t_order);
        return page;
    }

    public Integer getToDayOrderNum(){
        return t_orderMapper.getToDayOrderNum();
    }

    public Map<String,Object> confirmOrder(String user_id,String store_id){
        Map<String,Object> map=new HashMap<>();
        T_shopping_cart shopping_cart=new T_shopping_cart();
        shopping_cart.setC_user_id(user_id);

        List<T_shopping_cart> list=shoppingCartService.getCartList(shopping_cart);
        BigDecimal sum_price=new BigDecimal("0.0");
        if(list!=null&&list.size()>0){
            for (T_shopping_cart cart:list){
                sum_price=sum_price.add(new BigDecimal(cart.getSum_price()));
            }
        }
        map.put("cartList",list);
        C_user_address user_address=userAddressService.getMrAdderss(user_id);
        map.put("userAddress",user_address);

        T_base t_base=tBaseService.getBaseById(store_id);
        if(t_base!=null){
            sum_price=sum_price.add(new BigDecimal(t_base.getPacking_price()));
            sum_price=sum_price.add(new BigDecimal(t_base.getDistribution_price()));
        }
        map.put("packing_price",t_base.getPacking_price());
        map.put("distribution_price",t_base.getDistribution_price());
        DecimalFormat df = new DecimalFormat("#0.00");//保留两位小数
        map.put("sum_price",df.format(sum_price.floatValue()));
        T_user_coupons user_coupons=new T_user_coupons();
        user_coupons.setC_user_id(user_id);
        user_coupons.setSum_price(sum_price.floatValue());
        T_coupons t_coupons=userCouponsService.getMaxCoupons(user_coupons);
        map.put("t_coupons",t_coupons);
        return map;
    }

    public Map<String,Object> confirmShopOrder(String user_id){
        Map<String,Object> map=new HashMap<>();
        T_shopping_cart shopping_cart=new T_shopping_cart();
        shopping_cart.setC_user_id(user_id);

        List<T_shopping_cart> list=shoppingCartService.getCartList(shopping_cart);
        BigDecimal sum_price=new BigDecimal("0.0");
        if(list!=null&&list.size()>0){
            for (T_shopping_cart cart:list){
                sum_price=sum_price.add(new BigDecimal(cart.getSum_price()));
            }
        }
        map.put("cartList",list);
        C_user_address user_address=userAddressService.getMrAdderss(user_id);
        map.put("userAddress",user_address);

        /*T_base t_base=tBaseService.getBaseById(store_id);
        if(t_base!=null){
            sum_price=sum_price.add(new BigDecimal(t_base.getPacking_price()));
            sum_price=sum_price.add(new BigDecimal(t_base.getDistribution_price()));
        }*/
        /*map.put("packing_price",t_base.getPacking_price());
        map.put("distribution_price",t_base.getDistribution_price());*/

        T_user_coupons user_coupons=new T_user_coupons();
        user_coupons.setC_user_id(user_id);
        user_coupons.setSum_price(sum_price.floatValue());
        T_coupons t_coupons=userCouponsService.getMaxCoupons(user_coupons);
        if(t_coupons!=null){
            Integer price=t_coupons.getCoupons_price();
            sum_price=sum_price.subtract(new BigDecimal(price));
        }
        DecimalFormat df = new DecimalFormat("#0.00");//保留两位小数
        map.put("sum_price",df.format(sum_price.floatValue()));
        map.put("t_coupons",t_coupons);
        return map;
    }

    public Map<String,Object> ddconfirmOrder(String user_id,String store_id){
        Map<String,Object> map=new HashMap<>();
        T_shopping_cart shopping_cart=new T_shopping_cart();
        shopping_cart.setC_user_id(user_id);

        List<T_shopping_cart> list=shoppingCartService.getCartList(shopping_cart);
        BigDecimal sum_price=new BigDecimal("0.0");
        if(list!=null&&list.size()>0){
            for (T_shopping_cart cart:list){
                sum_price=sum_price.add(new BigDecimal(cart.getSum_price()));
            }
        }
        map.put("cartList",list);

       /* T_base t_base=tBaseService.getBaseById(store_id);
        if(t_base!=null){
            sum_price=sum_price.add(new BigDecimal(t_base.getPacking_price()));
            sum_price=sum_price.add(new BigDecimal(t_base.getDistribution_price()));
        }*/
        //map.put("packing_price",t_base.getPacking_price());
        //map.put("distribution_price",t_base.getDistribution_price());
        DecimalFormat df = new DecimalFormat("#0.00");//保留两位小数
        map.put("sum_price",df.format(sum_price.floatValue()));
        T_store_info t_store_info=storeInfoService.getStoreInfoById(store_id);
        map.put("t_store_info",t_store_info);
        T_user_coupons user_coupons=new T_user_coupons();
        user_coupons.setC_user_id(user_id);
        user_coupons.setSum_price(sum_price.floatValue());
        T_coupons t_coupons=userCouponsService.getMaxCoupons(user_coupons);
        map.put("t_coupons",t_coupons);
        return map;
    }

    /**
     * 获取商铺信息
     * @param id
     * @return
     */
    public T_store_info getStoreInfoById(String id){
        return storeInfoService.getStoreInfoById(id);
    }

    /**
     * 获取所有物流公司
     * @return
     */
    public List<Express> getAllExpress(){
        return t_orderMapper.getAllExpress();
    }

    /**
     * 确认物流信息
     * @return
     */
    @Transactional
    public String sureLogistics(T_order_express t_order_express){
        String res="0";
        try {
            T_order t_order=new T_order();
            t_order.setOrder_id(t_order_express.getOrder_id());
            t_order.setStatus(3);
            updateStatus(t_order);
            t_order_express.setExpress_id(AttaUtils.getNo());
            t_order_expressMapper.insertSelective(t_order_express);
        }catch (Exception e){
            e.printStackTrace();
            res="1";
            throw  new RuntimeException();
        }
        return res;
    }

    public T_order_express getLogisticsInfo(String order_id){
        return t_order_expressMapper.getOneByOrderId(order_id);
    }
}

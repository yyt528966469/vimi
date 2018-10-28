package com.wm.edu.wechat.controller.commodity;

import com.github.pagehelper.Page;
import com.wm.edu.model.adver.T_adver;
import com.wm.edu.model.commodity.T_commodity_info;
import com.wm.edu.model.commodity.T_commodity_type;
import com.wm.edu.model.shop.T_cart_info;
import com.wm.edu.model.shop.T_shopping_cart;
import com.wm.edu.model.store.T_base;
import com.wm.edu.service.adver.AdverService;
import com.wm.edu.service.commodity.CommodityInfoService;
import com.wm.edu.service.commodity.CommodityTypeService;
import com.wm.edu.service.store.StoreInfoService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.common.ResultBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wechat/commodity")
public class WechatCommodityController {

    @Autowired
    private AdverService adverService;
    @Autowired
    private CommodityTypeService commodityTypeService;
    @Autowired
    private CommodityInfoService commodityInfoService;
    @Autowired
    private StoreInfoService storeInfoService;

    /**
     * 获取广告
     * @return
     */
    @RequestMapping("/getAdver")
    public ResultBody getAdver(){
        ResultBody body=new ResultBody();
        T_adver t_adver=new T_adver();
        t_adver.setStatus(1);
        List<T_adver> list=adverService.getAllAdverList(t_adver);
        body.setData(list);
        return body;
    }

    /**
     * 获取推荐商品
     * @return
     */
    @RequestMapping("/getRecommendComm")
    public ResultBody getRecommendComm(){
        ResultBody body=new ResultBody();
        List<T_commodity_info> list=commodityInfoService.getRecommendComm();
        body.setData(list);
        return body;
    }

    @RequestMapping("/getInfoByCommNum")
    public ResultBody getInfoByCommNum(){
        ResultBody body=new ResultBody();
        List<T_commodity_info> list=commodityInfoService.getInfoByComm_num();
        body.setData(list);
        return body;
    }

    /**
     * 获取所有的商品类型
     * @return
     */
    @RequestMapping("/getCommodityType")
    public ResultBody getCommodityType(){
        ResultBody body=new ResultBody();
        List<T_commodity_type> list=commodityTypeService.getAllType();
        body.setData(list);
        return body;
    }

    /**
     * 根据类型获取商品列表
     * @param
     * @return
     */
    @RequestMapping("/getCommodityByType")
    public ResultBody getCommodityByType(String c_user_id,String type_id,T_commodity_info commodity_info){
        ResultBody body=new ResultBody();
        //String c_user_id="123";
        commodity_info.setCommodity_type(type_id);
        //commodity_info.setStatus(1);
        commodity_info.setReserved2(c_user_id);
        commodity_info.setSidx("create_time");
        commodity_info.setSord("asc");
        Page<T_commodity_info> page=commodityInfoService.getCommListByType(commodity_info);
        body.setData(page.getResult());
        return body;
    }

    /**
     * 获取商品详情
     * @param commodity_id
     * @return
     */
    @RequestMapping("/getCommodityById")
    public ResultBody getCommodityById(String commodity_id,String c_user_id){
        ResultBody body=new ResultBody();
        //String user_id="123";
        T_commodity_info commodity_info=commodityInfoService.getCommoDetail(commodity_id,c_user_id);
        body.setData(commodity_info);
        return body;
    }

    /**
     * 添加，减少购物车
     * @param commodity_id
     * @param type 0为-，1为+
     * @return
     */
    @RequestMapping("/addCart")
    public ResultBody addCart(String commodity_id,String type,String c_user_id){
        ResultBody body=new ResultBody();
        //String user_id="123";
        int code=200;
        if("0".equals(type)){
            code=commodityInfoService.deleteCart(commodity_id,c_user_id);
        }else {
            code=commodityInfoService.addCart(commodity_id,c_user_id);
        }
        body.setCode(code);
        return body;
    }
    @RequestMapping("/deleteCart")
    public ResultBody deleteCart(String c_user_id){
        ResultBody body=new ResultBody();
        //String user_id="123";
        Integer code=commodityInfoService.deleteCartByUserId(c_user_id);
        body.setCode(code);
        return body;
    }


    /**
     * 获取购物车列表
     * @return
     */
    @RequestMapping("/getCartList")
    public ResultBody getCartList(String c_user_id){
        ResultBody body=new ResultBody();
        Map<String,Object> map=new HashMap<>();
        //String user_id="123";
        T_shopping_cart shopping_cart=new T_shopping_cart();
        shopping_cart.setC_user_id(c_user_id);
        List<T_shopping_cart> list=commodityInfoService.getCartList(shopping_cart);
        map.put("cartList",list);
        T_base t_base=storeInfoService.getBaseInfo();
        map.put("packing_price",t_base.getPacking_price());
        body.setData(map);
        return body;
    }

    /**
     * 获取电商购物车列表
     * @param c_user_id
     * @return
     */
    @RequestMapping("/getShopCartList")
    public ResultBody getShopCartList(String c_user_id){
        ResultBody body=new ResultBody();
        Map<String,Object> map=new HashMap<>();
        //String user_id="123";
        T_shopping_cart shopping_cart=new T_shopping_cart();
        shopping_cart.setC_user_id(c_user_id);
        List<T_shopping_cart> list=commodityInfoService.getCartList(shopping_cart);
        map.put("cartList",list);
        //T_base t_base=storeInfoService.getBaseInfo();
        //map.put("packing_price",t_base.getPacking_price());
        body.setData(map);
        return body;
    }

    /**
     *
     * @return
     */
    @RequestMapping("/getUserCartSum")
    public ResultBody getUserCartSum(String c_user_id){
        ResultBody body=new ResultBody();
        //String user_id="123";
        T_cart_info t_cart_info=commodityInfoService.getUserCartSum(c_user_id);
        T_base t_base=storeInfoService.getBaseInfo();

        if(t_cart_info!=null){
            String sum=t_cart_info.getC_price_sum();
            BigDecimal sum_price=new BigDecimal(sum);
            BigDecimal packing_price=new BigDecimal(String.valueOf(t_base.getPacking_price()));
            sum_price=sum_price.add(packing_price);
            t_cart_info.setC_price_sum(sum_price.floatValue());
        }else {
            t_cart_info=new T_cart_info();
        }
        body.setData(t_cart_info);
        return body;
    }

    /**
     * 电商
     * @param c_user_id
     * @return
     */
    @RequestMapping("/getUserShopCartSum")
    public ResultBody getUserShopCartSum(String c_user_id){
        ResultBody body=new ResultBody();
        //String user_id="123";
        T_cart_info t_cart_info=commodityInfoService.getUserCartSum(c_user_id);
        //T_base t_base=storeInfoService.getBaseInfo();

        if(t_cart_info!=null){
            String sum=t_cart_info.getC_price_sum();
            BigDecimal sum_price=new BigDecimal(sum);
            //BigDecimal packing_price=new BigDecimal(String.valueOf(t_base.getPacking_price()));
            //sum_price=sum_price.add(packing_price);
            t_cart_info.setC_price_sum(sum_price.floatValue());
        }else {
            t_cart_info=new T_cart_info();
        }
        body.setData(t_cart_info);
        return body;
    }

}

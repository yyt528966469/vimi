package com.wm.edu.service.commodity;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.commodity.T_commodity_browseMapper;
import com.wm.edu.mapper.commodity.T_commodity_infoMapper;
import com.wm.edu.mapper.commodity.T_commodity_photoMapper;
import com.wm.edu.model.commodity.T_commodity_browse;
import com.wm.edu.model.commodity.T_commodity_info;
import com.wm.edu.model.commodity.T_commodity_photo;
import com.wm.edu.model.commodity.T_commodity_type;
import com.wm.edu.model.shop.T_cart_info;
import com.wm.edu.model.shop.T_shopping_cart;
import com.wm.edu.service.shop.ShoppingCartService;
import com.wm.edu.utils.common.AttaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommodityInfoService {
    @Autowired
    private T_commodity_infoMapper t_commodity_infoMapper;
    @Autowired
    private CommodityTypeService commodityTypeService;
    @Autowired
    private T_commodity_photoMapper t_commodity_photoMapper;

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private T_commodity_browseMapper t_commodity_browseMapper;

    public Page<T_commodity_info> getCommodityList(T_commodity_info t_commodity_info){
        String orderStandesc=t_commodity_info.getSidx()+" "+t_commodity_info.getSord();
        Page<T_commodity_info> page=PageHelper.startPage(t_commodity_info.getPage(),t_commodity_info.getRows(),orderStandesc);
        t_commodity_infoMapper.getCommList(t_commodity_info);
        return page;
    }

    public List<T_commodity_type> getAllType(){
        return commodityTypeService.getAllType();
    }

    public T_commodity_info getCommById(String id){

        return t_commodity_infoMapper.selectByPrimaryKey(id);
    }

    /**
     *
     * @param id
     * @param c_user_id
     * @return
     */
    public T_commodity_info getCommoDetail(String id,String c_user_id){
        T_commodity_browse commodity_browse=t_commodity_browseMapper.getBrowseByCommIdAndUserId(id,c_user_id);
        if(commodity_browse==null){
            commodity_browse=new T_commodity_browse();
            commodity_browse.setId(AttaUtils.getNo());
            commodity_browse.setCommodity_id(id);
            commodity_browse.setC_user_id(c_user_id);
            commodity_browse.setCreate_time(new Date());
            commodity_browse.setUpdate_time(new Date());
            t_commodity_browseMapper.insertSelective(commodity_browse);
        }else {
            commodity_browse.setUpdate_time(new Date());
            t_commodity_browseMapper.updateByPrimaryKeySelective(commodity_browse);
        }

        return t_commodity_infoMapper.getCommodityDetails(id,c_user_id);
    }

    @Transactional
    public String saveCommInfo(T_commodity_info t_commodity_info, String[] head_name, String[] text_name, HttpServletRequest request){
        String result="0";
        try {
            if (StringUtils.isNotEmpty(t_commodity_info.getCommodity_id())){

                t_commodity_infoMapper.updateByPrimaryKeySelective(t_commodity_info);
                if(head_name!=null&&head_name.length!=0){
                    t_commodity_photoMapper.deletePhoto(t_commodity_info.getCommodity_id(),0);
                }
                if(text_name!=null&&text_name.length!=0){
                    t_commodity_photoMapper.deletePhoto(t_commodity_info.getCommodity_id(),1);
                }
                List<T_commodity_photo> list=getPhotoList(head_name,text_name,t_commodity_info.getCommodity_id(),request);
                if(list.size()>0){
                    t_commodity_photoMapper.insertPhotoList(list);
                }

            }else {
                t_commodity_info.setCommodity_id(AttaUtils.getNo());
                t_commodity_info.setCreate_time(new Date());
                t_commodity_info.setStatus(0);
                t_commodity_infoMapper.insertSelective(t_commodity_info);
                List<T_commodity_photo> list=getPhotoList(head_name,text_name,t_commodity_info.getCommodity_id(),request);
                if(list.size()>0){
                    t_commodity_photoMapper.insertPhotoList(list);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            result="1";
            throw new RuntimeException();
        }

        return result;
    }

    private List<T_commodity_photo> getPhotoList(String[] head_name,String[] text_name,String commodity_id,HttpServletRequest request){
        List<T_commodity_photo> photoList=new ArrayList<>();
        if(head_name!=null&&head_name.length!=0){
            for (String head:head_name){
                T_commodity_photo photo=new T_commodity_photo();
                photo.setPhoto_id(AttaUtils.getNo());
                photo.setCommodity_id(commodity_id);
                photo.setImg_type(0);
                String[] arr=head.split(",");
                photo.setImg_url(AttaUtils.getAllUrl(request,arr[0]));
                photo.setSort(Integer.parseInt(arr[1]));
                photoList.add(photo);
            }
        }

        if (text_name!=null&&text_name.length!=0){
            for (String text:text_name){
                T_commodity_photo photo=new T_commodity_photo();
                photo.setPhoto_id(AttaUtils.getNo());
                photo.setCommodity_id(commodity_id);
                photo.setImg_type(1);
                String[] arr=text.split(",");
                photo.setImg_url(AttaUtils.getAllUrl(request,arr[0]));
                photo.setSort(Integer.parseInt(arr[1]));
                photoList.add(photo);
            }
        }
        return photoList;
    }


    @Transactional
    public String deleteCommodity(String commodity_id){
        String result="0";
        try {
            t_commodity_infoMapper.deleteByPrimaryKey(commodity_id);
            t_commodity_photoMapper.deletePhoto(commodity_id,0);
            t_commodity_photoMapper.deletePhoto(commodity_id,1);
        }catch (Exception e){
            e.printStackTrace();
            result="1";
            throw new RuntimeException();
        }
        return result;
    }

    public String updateStatus(T_commodity_info t_commodity_info){
        String result="0";
        try {
            t_commodity_infoMapper.updateByPrimaryKeySelective(t_commodity_info);
        }catch (Exception e){
            e.printStackTrace();
            result="1";
        }
        return result;
    }

    public Integer addCart(String commodity_id,String c_user_id){
        Integer code=200;
        try {
            T_shopping_cart shopping_cart=shoppingCartService.getCartByUserIdAndCommId(c_user_id,commodity_id);
            if(shopping_cart!=null){//判断该商品在不在购物车
                shopping_cart.setComm_num(shopping_cart.getComm_num()+1);
                shopping_cart.setSum_price(shopping_cart.getSum_price()+Float.parseFloat(shopping_cart.getCommodity_price()));
                shoppingCartService.updateCart(shopping_cart);
            }else {
                //获取商品信息
                T_commodity_info commodity_info=t_commodity_infoMapper.selectByPrimaryKey(commodity_id);
                if(commodity_info!=null){//
                    shopping_cart=new T_shopping_cart();
                    shopping_cart.setCart_id(AttaUtils.getNo());
                    shopping_cart.setCommodity_id(commodity_id);
                    shopping_cart.setC_user_id(c_user_id);
                    shopping_cart.setSum_price(Float.parseFloat(commodity_info.getPrice()));
                    shopping_cart.setComm_num(1);
                    shopping_cart.setCommodity_price(Float.parseFloat(commodity_info.getPrice()));
                    shopping_cart.setCommodity_name(commodity_info.getCommodity_name());
                    shopping_cart.setImg_src(commodity_info.getImg_src());
                    shoppingCartService.insertCart(shopping_cart);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            code=502;
        }
        return code;
    }

    public Integer deleteCart(String commodity_id,String c_user_id){
        Integer code=200;
        try {
            T_shopping_cart shopping_cart=shoppingCartService.getCartByUserIdAndCommId(c_user_id,commodity_id);
            if(shopping_cart!=null){
                Integer comm_num=shopping_cart.getComm_num();
                if(comm_num>1){
                    comm_num--;
                    shopping_cart.setComm_num(comm_num);
                    shopping_cart.setSum_price(shopping_cart.getSum_price()-Float.parseFloat(shopping_cart.getCommodity_price()));
                    shoppingCartService.updateCart(shopping_cart);
                }else {
                    shoppingCartService.deleteCart(shopping_cart.getCart_id());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            code=502;
        }

        return code;
    }

    public List<T_shopping_cart> getCartList(T_shopping_cart shopping_cart){
        return shoppingCartService.getCartList(shopping_cart);
    }

    /**
     *
     * @return
     */
    public List<T_commodity_info > getRecommendComm(){
        return t_commodity_infoMapper.getRecommendComm();
    }


    public List<T_commodity_info> getInfoByComm_num(){
        return t_commodity_infoMapper.getInfoByComm_num();
    }


    public Page<T_commodity_info> getCommListByType(T_commodity_info t_commodity_info){
        String orderStandesc=t_commodity_info.getSidx()+" "+t_commodity_info.getSord();
        Page<T_commodity_info> page=PageHelper.startPage(t_commodity_info.getPage(),t_commodity_info.getRows(),orderStandesc);
        t_commodity_infoMapper.getCommListByType(t_commodity_info);
        return page;
    }


    public T_cart_info getUserCartSum(String c_user_id){
        return shoppingCartService.getUserCartSum(c_user_id);
    }

    public Integer deleteCartByUserId(String c_user_id){
        Integer code=200;
        try {
            shoppingCartService.deleteCartByUserId(c_user_id);
        }catch (Exception e){
            e.printStackTrace();
            code=502;
        }
        return code;

    }
}

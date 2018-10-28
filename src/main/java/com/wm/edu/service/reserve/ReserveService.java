package com.wm.edu.service.reserve;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.reserve.T_reserveMapper;
import com.wm.edu.model.reserve.T_reserve;
import com.wm.edu.model.store.T_store_info;
import com.wm.edu.service.store.StoreInfoService;
import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.http.SmsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class ReserveService {

    @Autowired
    private T_reserveMapper t_reserveMapper;
    @Autowired
    private StoreInfoService storeInfoService;

    public Page<T_reserve> getReservePage(T_reserve t_reserve){
        String orderStandesc=t_reserve.getSidx()+" "+t_reserve.getSord();
        Page<T_reserve> page=PageHelper.startPage(t_reserve.getPage(),t_reserve.getRows(),orderStandesc);
        t_reserveMapper.getReserveList(t_reserve);
        return page;
    }

    public T_reserve getReserveById(String id){
        return t_reserveMapper.selectByPrimaryKey(id);
    }

    public Integer insertReserve(T_reserve t_reserve){
        Integer code=200;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy.MM.dd HH:mm");
            if(StringUtils.isNotEmpty(t_reserve.getR_date())&&StringUtils.isNotEmpty(t_reserve.getR_time())){
                String time=t_reserve.getR_date()+" "+t_reserve.getR_time();//10.18 20:00
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                time=year+"."+time;
                Date date=sdf.parse(time);
                t_reserve.setReserve_time(date);
            }

            t_reserveMapper.insertSelective(t_reserve);
        }catch (Exception e){
            e.printStackTrace();
            code=502;
        }
        return code;
    }

    /*public T_reserve getReserveById(String id){
        return t_reserveMapper.selectByPrimaryKey(id);
    }*/

    public Integer updateStatus(T_reserve t_reserve){
        Integer code=200;
        try {

            t_reserveMapper.updateByPrimaryKeySelective(t_reserve);

        }catch (Exception e){
            e.printStackTrace();
            code=502;
        }
        return code;
    }

    public List<T_reserve> getUserReserveList(String user_id){
        return t_reserveMapper.getUserReserveList(user_id);
    }

}

package com.wm.edu.service.task;


import com.wm.edu.model.base.T_city;
import com.wm.edu.service.cuser.UserAddressService;
import com.wm.edu.service.redis.RedisService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
@Service
@Transactional
public class ScheduledTaskService {

    /*@Autowired
    private UserAddressService userAddressService;
    @Autowired
    private RedisService redisService;*/

    @Scheduled(fixedRate = 1000*5)
    public void printCurrentTime() throws InterruptedException {//定时任务，五个小时执行一次

    }
    @Scheduled(cron = "00 28 10 * * *")
    public void currentTime(){

        /*List<T_city> list=userAddressService.getAllCityInfo();
        if(list!=null){
            for (T_city t_city:list){
                String key=t_city.getId()+"_"+t_city.getParid()+"_"+t_city.getType();
                JSONObject jsonObject=new JSONObject(t_city);
                redisService.setStr(key,jsonObject.toString());
            }
        }*/
        System.out.println(new Date());
    }
}

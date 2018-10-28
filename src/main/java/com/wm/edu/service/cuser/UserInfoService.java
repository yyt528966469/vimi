package com.wm.edu.service.cuser;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.cuser.C_user_infoMapper;
import com.wm.edu.model.base.Express;
import com.wm.edu.model.cuser.C_user_info;
import com.wm.edu.utils.util.Test;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class UserInfoService {

    @Autowired
    private C_user_infoMapper c_user_infoMapper;

    public Page<C_user_info> getUserPage(C_user_info c_user_info){
        String orderStandesc=c_user_info.getSidx()+" "+c_user_info.getSord();
        Page<C_user_info> page=PageHelper.startPage(c_user_info.getPage(),c_user_info.getRows(),orderStandesc);
        c_user_infoMapper.getUserList(c_user_info);
        return page;
    }

    public Integer getAllUserNum(){
        return c_user_infoMapper.getAllUserNum();
    }

    public Integer insertUserInfo(C_user_info c_user_info){
        Integer code=200;
        try {
            c_user_infoMapper.insertSelective(c_user_info);
        }catch (Exception e){
            e.printStackTrace();
            code=502;
        }

        return code;
    }

    public C_user_info getUserByOpenid(String openid){
        return c_user_infoMapper.getUserByOpenid(openid);
    }

    public Integer updateUserInfo(C_user_info c_user_info){
        Integer code=200;
        try {
            c_user_infoMapper.updateByPrimaryKeySelective(c_user_info);
        }catch (Exception e){
            e.printStackTrace();
            code=502;
        }

        return code;
    }


    public void add() throws URISyntaxException, IOException {
        List<Express> list=new ArrayList<>();
        URL res=Test.class.getClassLoader().getResource("123.json");
        String input=FileUtils.readFileToString(new File(res.toURI().getPath()),"UTF-8");
        //System.out.println(input);
        JSONArray jsonArray=JSONArray.fromObject(input);
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            String expressname=jsonObject.getString("expressname");
            String expresskey=jsonObject.getString("expresskey");
            Express express=new Express();
            express.setName(expressname);
            express.setId(expresskey);
            list.add(express);
        }

        System.out.println(list.size());
        c_user_infoMapper.addEx(list);

    }
}

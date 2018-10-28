package com.wm.edu.service.adver;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.adver.T_adverMapper;
import com.wm.edu.model.adver.T_adver;
import com.wm.edu.utils.common.AttaUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdverService {

    @Autowired
    private T_adverMapper t_adverMapper;

    public Page<T_adver> getAdverList(T_adver t_adver){

        Page<T_adver> page=PageHelper.startPage(t_adver.getPage(),t_adver.getRows());
        t_adverMapper.getAdverList(t_adver);
        return page;
    }

    public T_adver getAdverById(String adver_id){
        return t_adverMapper.selectByPrimaryKey(adver_id);
    }

    public String saveAdver(T_adver t_adver){
        String result="0";
        try {
            if(StringUtils.isNotEmpty(t_adver.getAdver_id())){
                t_adverMapper.updateByPrimaryKeySelective(t_adver);
            }else {
                t_adver.setAdver_id(AttaUtils.getNo());
                t_adver.setCreate_time(new Date());
                t_adver.setStatus(0);
                t_adverMapper.insertSelective(t_adver);
            }

        }catch (Exception e){
            result="1";
            e.printStackTrace();
            throw new RuntimeException();
        }
        return result;
    }

    public String updateStatus(T_adver t_adver){
        String result="0";
        try {
            t_adverMapper.updateByPrimaryKeySelective(t_adver);
        }catch (Exception e){
            e.printStackTrace();
            result="1";
        }

        return result;
    }

    /**
     * 删除广告
     * @param adver_id
     * @return
     */
    public String deleteAdver(String adver_id){
        String result="0";
        try {
            t_adverMapper.deleteByPrimaryKey(adver_id);
        }catch (Exception e){
            e.printStackTrace();
            result="1";
        }
        return result;
    }


    public List<T_adver> getAllAdverList(T_adver t_adver){
        return t_adverMapper.getAdverList(t_adver);
    }

}

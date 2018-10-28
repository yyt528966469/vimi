package com.wm.edu.service.security;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wm.edu.mapper.security.SysResourcesMapper;
import com.wm.edu.model.security.SysResources;
import com.wm.edu.utils.common.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sysResourcesService")
public class SysResourcesService {

    @Autowired
    private SysResourcesMapper sysResourcesMapper;

    public Page<SysResources> getResList(SysResources resources){
        Page<SysResources> page=PageHelper.startPage(resources.getPage(),resources.getRows());
       /* page.setOrderBy(resources.getSidx());
        page.setOrderByOnly(true);*/
        sysResourcesMapper.getResList(resources);
        return page;
    }

    public SysResources getResInfoById(Integer res_id){
        return sysResourcesMapper.selectByPrimaryKey(res_id);
    }

    public SysResources getInfoByMap(Map<String,Object> map){
        return sysResourcesMapper.getInfoByMap(map);
    }

    public ResultBody saveResInfo(SysResources resources){
        ResultBody resultBody=new ResultBody();
        int result=200;
        try{
            if(resources.getRes_type()==0){
                resources.setParent_id(0);
            }
            if(resources.getRes_id()!=null){//编辑
                sysResourcesMapper.updateByPrimaryKeySelective(resources);
            }else {//新增
                resources.setRes_name(resources.getName());
                sysResourcesMapper.insertSelective(resources);
            }
        }catch (Exception e){
            e.printStackTrace();
            result=502;
        }
        resultBody.setCode(result);
        return resultBody;
    }

    public void insertSelective(SysResources sysResources){
        sysResourcesMapper.insertSelective(sysResources);
    }


    public List<SysResources> getParentRes(SysResources sysResources){
        return sysResourcesMapper.getResList(sysResources);
    }

    public List<SysResources> findAll(){
        return sysResourcesMapper.findAll();
    }

    public List<SysResources> findByAdminUserId(Integer user_id){
        return sysResourcesMapper.findByAdminUserId(user_id);
    }

    public List<SysResources> getParByUserId(Integer user_id){
        return sysResourcesMapper.getParByUserId(user_id);
    }


    public List<SysResources> getResListByBean(SysResources sysResources){
        return sysResourcesMapper.getResListByBean(sysResources);
    }

    /**
     * 获取用户下的功能权限
     * @param user_id
     * @return
     */
    public List<SysResources> getButtonResByUser(Integer user_id){
        return sysResourcesMapper.getButtonResByUser(user_id);
    }

    /**
     * 根据ID删除菜单
     * @param id
     * @return
     */
    public Integer deleteRes(Integer id){
        Integer code=200;
        try {
            sysResourcesMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            code=502;
            throw new RuntimeException();
        }
        return code;
    }
}

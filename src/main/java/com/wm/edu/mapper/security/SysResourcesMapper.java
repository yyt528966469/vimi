package com.wm.edu.mapper.security;


import com.wm.edu.model.security.SysResources;

import java.util.List;
import java.util.Map;

public interface SysResourcesMapper {

    List<SysResources> findAll();

    List<SysResources> findByAdminUserId(Integer user_id);

    List<SysResources> getParByUserId(Integer user_id);

    List<SysResources> getButtonResByUser(Integer user_id);
    /**
     * 分页获取菜单列表
     * @param sysResources
     * @return
     */
    List<SysResources> getResList(SysResources sysResources);
    int deleteByPrimaryKey(Integer res_id);

    int insert(SysResources record);

    int insertSelective(SysResources record);

    SysResources selectByPrimaryKey(Integer res_id);

    int updateByPrimaryKeySelective(SysResources record);

    int updateByPrimaryKey(SysResources record);

    SysResources getInfoByMap(Map<String, Object> map);


    List<SysResources> getResListByBean(SysResources sysResources);
}
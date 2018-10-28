package com.wm.edu.utils.util;

import com.wm.edu.model.admin.Sys_user;
import com.wm.edu.model.security.SysResources;
import com.wm.edu.model.security.Sys_role;
import com.wm.edu.service.security.SysResourcesService;
import com.wm.edu.service.security.Sys_roleService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MenuUtil {
	
	
	private static SysResourcesService sysResourceService;
	
	private static Sys_roleService sysRoleService;
	
	//加载所有菜单
	private static List<SysResources> sysResources;
	
	//加载所有角色，以及角色已授权菜单
	private static List<Sys_role> sysRoles;
	
	public static void init(SysResourcesService sysResService,Sys_roleService sysRoService){
		if(sysResourceService==null){
			sysResourceService=sysResService;
		}
		if(sysRoleService==null){
			sysRoleService=sysRoService;
		}
		//reLoadMenu();
	}
	
	public static void reLoadMenu(){
		reLoadResource();
		reLoadRoles();
	}
	
	public static void reLoadResource(){
		SysResources resources=new SysResources();
		sysResources= sysResourceService.getParentRes(resources);
	}
	
	public static void reLoadRoles(){
		Sys_role sys_role=new Sys_role();
		sysRoles=sysRoleService.getSysRole(sys_role);
	}
	
	public static void setSysResources(List<SysResources> sysResource) {
		sysResources = sysResource;
	}
	
	public static List<SysResources> getSysResources() {
		return sysResources;
	}
	
	public static void setSysRoles(List<Sys_role> sysRole) {
		sysRoles = sysRole;
	}
	
	//添加菜单
	public static void addSysResource(SysResources sysResource)
	{
		sysResources.add(sysResource);
	}
	
	//添加角色
	public static void addSysRoles(Sys_role sysRole) {
		sysRoles.add(sysRole);
	}
	
	public static void deleteSysRole(Integer roleId)
	{
		for (int i=0;i<sysRoles.size();i++) {
			if(sysRoles.get(i).getId()==roleId)
			{
				sysRoles.remove(i);
				break;
			}
		}
	}
	
	public static void deleteSysResource(Integer sysResId)
	{
		for (int i=0;i<sysResources.size();i++) {
			if(sysResources.get(i).getRes_id()==sysResId)
			{
				sysResources.remove(i);
				break;
			}
		}
	}
	/**
	 * 添加角色权限
	 * @param roleId 角色Id
	 * @param  sysResourceList 授权菜单组
	 */
	public static void addSysRoleRes(Integer roleId,List<SysResources> sysResourceList)
	{
		for (int i = 0; i < sysRoles.size(); i++) {
			if(roleId==sysRoles.get(i).getId())
			{
				sysRoles.get(i).setSysResList(sysResourceList);
				break;
			}
		}
	}
	
	//获取角色全部权限
	public static List<SysResources> getAllSysResourcesByRoles(List<Sys_role> roleList)
	{
		List<SysResources> sysResourceList=new ArrayList<SysResources>();
	
		List<SysResources> roleResources=null;
		if(roleList!=null&&roleList.size()>0)
		{
			for (Sys_role r : roleList) {
				Sys_role role=getSysRoleById(String.valueOf(r.getId()));
				if(roleResources==null)
				{
					roleResources=role.getSysResList();
				}else
				{
					roleResources.addAll(role.getSysResList());
				}
				
			}
			
			if(roleResources!=null&&roleResources.size()>0)
			{
				for (int i = roleResources.size()-1; i >-1; i--) {
					for (int j = i-1; j >-1; j--) {
						if(roleResources.get(i).getRes_id()==roleResources.get(j).getRes_id())
						{
							roleResources.remove(i);
							break;
						}
					}
				}
			}			
			
		}
		
		if(roleList==null)
		{
			sysResourceList=sysResources;
		}else if(roleResources!=null&&roleResources.size()>0)
		{
			for (SysResources sysResource : roleResources) {
				for (SysResources resource :sysResources) {
					if(sysResource.getRes_id()==resource.getRes_id())
					{
						sysResourceList.add(resource);						
					}
				}
			}
		}
		
		return sysResourceList;
	}
	
	//获取角色权限,返回父子菜单结构
	public static List<SysResources> getSysResourcesByRoles(List<Sys_role> roleList)
	{
		List<SysResources> sysResourceList=new ArrayList<SysResources>();
		
		List<SysResources> roleResources=new ArrayList<SysResources>();
		if(roleList!=null&&roleList.size()>0)
		{
			for (Sys_role r : roleList) {
				Sys_role role=getSysRoleById(String.valueOf(r.getId()));
				if(roleResources==null)
				{
					roleResources=role.getSysResList();
				}else
				{
					roleResources.addAll(role.getSysResList());
				}
				
			}
			
			if(roleResources!=null&&roleResources.size()>0)
			{
				for (int i = roleResources.size()-1; i >-1; i--) {
					for (int j = i-1; j >-1; j--) {
						if(roleResources.get(i).getRes_id()==roleResources.get(j).getRes_id())
						{
							roleResources.remove(i);
							break;
						}
					}
				}
			}
		}
		if(roleList==null)
		{
			roleResources=null;
		}
		sysResourceList=getSysRoleRes(roleResources);
		return sysResourceList;
	}
		
	public static Sys_role getSysRoleById(String roleId)
	{
		
		if(roleId==null||roleId.trim().length()==0) return null;

		Sys_role sysRole=null;
		
		for (Sys_role role:sysRoles) {
			if(role.getId()==Integer.parseInt(roleId))
			{
				sysRole=role;
				break;
			}
		}
		
		return sysRole;
	}

	public static List<SysResources> getSysRoleRes(List<SysResources> roleResources)
	{
		List<SysResources> sysResourceList=new ArrayList<SysResources>();
		
		//List<SysResource> roleResources=role==null?null:role.getSysResources();
		//获取一级目录
		for (SysResources sysResource : sysResources) {
			if(roleResources!=null)
			{
				for (SysResources resource : roleResources) {
					if(resource!=null)
					{
						if(sysResource.getParent_id()==0&&sysResource.getRes_id()==resource.getRes_id())
						{
							sysResourceList.add(sysResource);
						}
					}					
				}
			}else
			{
				if(sysResource.getParent_id()==0)
				{
					sysResourceList.add(sysResource);
				}
			}
			
		}
		
		HashMap<Integer,List<SysResources>> childHashMap=getSysResourceChild(sysResourceList,roleResources);
		
		if(childHashMap!=null)
		{
			for (int i = 0; i < sysResourceList.size(); i++) {
				for (Integer resId : childHashMap.keySet()) {
					if(sysResourceList.get(i).getRes_id()==resId)
					{
						sysResourceList.get(i).setChildren(childHashMap.get(resId));
						break;
					}
				}
			}
		}
		
		return sysResourceList;
	}

	public static HashMap<Integer, List<SysResources>> getSysResourceChild(List<SysResources> sysResourceList,List<SysResources> roleResourceList)
	{
		HashMap<Integer,List<SysResources>> hashMap=null;
		
		for (SysResources sysResource : sysResourceList) {
			List<SysResources> list=new ArrayList<SysResources>();
			for (SysResources resource : sysResources) {
				if(roleResourceList!=null)
				{
					for (SysResources res : roleResourceList) {
						/* System.out.println("sysResource.getId()---"+sysResource.getId()+"---resource.getParentId()-----"+resource.getParentId()+"----resource.getId()----"+resource.getId()+"---res.getId()-----"+res.getId());*/
						if((sysResource.getRes_id().intValue()==resource.getParent_id().intValue())&&resource.getRes_id().intValue()==res.getRes_id().intValue())
						{
							if(hashMap==null)
							{
								hashMap=new HashMap<Integer, List<SysResources>>();
							}
							
							list.add(resource);
						}
					}
				}else{
					if(sysResource.getRes_id().intValue()==resource.getParent_id().intValue())
					{
						if(hashMap==null)
						{
							hashMap=new HashMap<Integer, List<SysResources>>();
						}
						
						list.add(resource);
					}
				}
				
			}
			if(list.size()>0)
			{
				HashMap<Integer,List<SysResources>> childHashMap=getSysResourceChild(list,roleResourceList);
				if(childHashMap!=null)
				{
					for (int i = 0; i < list.size(); i++) {
						for (Integer resId : childHashMap.keySet()) {
							if(list.get(i).getRes_id()==resId)
							{
								list.get(i).setChildren(childHashMap.get(resId));
								break;
							}
						}
					}
				}
				hashMap.put(sysResource.getRes_id(), list);
			}
			
			
		}
		
		return hashMap;
	}
    /**
     * 菜单加载主方法
     * @param request
     * @return
     */
	public static String getSysResourcesHtml(HttpServletRequest request,Sys_user sys_user)
	{
		reLoadMenu();
		List<Sys_role> roleList=sysRoleService.getSysRoleByUserId(sys_user.getUser_id());
		List<SysResources> rs = null;
		//如果管理员是admin 获取所有的资源数据
		if (sys_user.getRank()==9)
		{
		    rs =MenuUtil.getSysResourcesByRoles(null);
		}
		else
		{
			 try {
				 rs =MenuUtil.getSysResourcesByRoles(roleList);
			} catch (Exception e) {
					throw new RuntimeException(e);
			}

		}
		/*SysResources sysResources=new SysResources();
		sysResources.setRes_type(0);
		rs=sysResourceService.getResList(sysResources);*/
		String ctx=request.getContextPath();
		StringBuffer strHtml=new StringBuffer();
		
		for (SysResources sysResource : rs) {
			//菜单html格式输出
			strHtml.append(getSysResourceHtml(sysResource,ctx,0));
		}
		
		return strHtml.toString();
	}


	public static List<SysResources> getRes(HttpServletRequest request,Sys_user sys_user){
		reLoadMenu();
		List<Sys_role> roleList=sysRoleService.getSysRoleByUserId(sys_user.getUser_id());
		List<SysResources> rs = null;
		//如果管理员是admin 获取所有的资源数据
		rs =MenuUtil.getSysResourcesByRoles(roleList);
		/*if (sys_user.getRank()==9)
		{
			rs =MenuUtil.getSysResourcesByRoles(null);
		}
		else
		{
			try {
				rs =MenuUtil.getSysResourcesByRoles(roleList);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}*/

		return rs;
	}
	/**
	 * 菜单html拼接
	 * @param sysResource
	 * @param ctx
	 * @param flag
	 * @return
	 */
	public static String getSysResourceHtml(SysResources sysResource,String ctx,int flag)
	{
		StringBuffer strHtml=new StringBuffer();
		
		if(0==sysResource.getRes_type())
		{			
			strHtml.append("< li>");
			strHtml.append("<a href='#'>");
			if(sysResource.getImg()!=null&&sysResource.getImg().trim().length()>0)
			{
				strHtml.append("<i class='"+sysResource.getImg()+"'></i>");
			}
			strHtml.append("<span class='nav-label'>"+sysResource.getName()+"</span><span class='fa arrow'></span></a>");
			
			if(sysResource.getChildren()!=null&&sysResource.getChildren().size()>0)
			{
				if(flag==0)
				{
					strHtml.append("<ul class='nav nav-second-level'>");
				}else
				{
					strHtml.append("<ul class='nav nav-third-level'>");
				}
				
				for (SysResources resource : sysResource.getChildren()) {
					strHtml.append(getSysResourceHtml(resource,ctx,1));
				}
				strHtml.append("</ul>");
			}
			strHtml.append("</li>");
		}else if (1==sysResource.getRes_type()) {
			strHtml.append("<li>");
			strHtml.append("<a class='J_menuItem' href='"+ctx+sysResource.getRes_url()+"'>");
			if(sysResource.getImg()!=null&&sysResource.getImg().trim().length()>0)
			{
				strHtml.append("<i class='"+sysResource.getImg()+"'></i>");				
			}
			strHtml.append("<span class='nav-label'>"+sysResource.getName()+"</span></a>");			 
			strHtml.append("</li>");
		}
		
		return strHtml.toString();
	}
	
	
	public static String getMenuChildBut(List<Sys_role> roleList,String requestUrl)
	{
		String childButHtml="";
		List<SysResources> sysResourceList=getAllSysResourcesByRoles(roleList);
		if(requestUrl.indexOf("?")>-1){
			requestUrl=requestUrl.substring(0,requestUrl.indexOf("?"));
		}
		
		int menuId=0;
		
		for (SysResources sysResource : sysResourceList) {
			if(requestUrl.equals(sysResource.getRes_url()))
			{
				if("2".equals(sysResource.getRes_type()))
				{
					menuId=sysResource.getRes_id();
				}
				break;
			}
		}
		if(menuId!=0)
		{
			List<SysResources> childBut=new ArrayList<SysResources>();
			for (SysResources sysResource : sysResourceList) {
				if(sysResource.getParent_id()==menuId&&"3".equals(sysResource.getRes_type()))
				{
					childBut.add(sysResource);
				}
			}
			
			childButHtml=getButHtmlBySysResources(childBut);
			
		}
		return childButHtml;
	}
	
	
	public static String getButHtmlBySysResources(List<SysResources> butSysResources)
	{
		StringBuffer butHtml=new StringBuffer();
		for (SysResources sysResource : butSysResources) {
			butHtml.append("<button class='btn btn-success btn-outline' type='button'  fromUrl='"+sysResource.getRes_url()+"'>");
			if(sysResource.getImg()!=null&&sysResource.getImg().trim().length()>0)
			{
				butHtml.append("<i class='"+sysResource.getImg()+"'></i>");
			}
			butHtml.append(" "+sysResource.getName());
			butHtml.append("</button> ");			
		}
		return butHtml.toString();
	}
	
}
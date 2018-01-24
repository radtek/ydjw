package com.ehootu.sys.service;

import com.ehootu.sys.entity.SysRoleMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色与菜单对应关系
 * 
 * @author zhangyong
 * @email zhangyong@ehootu.com
 * @date 2018-01-24 15:10:15
 */
public interface SysRoleMenuService {
	
	SysRoleMenuEntity queryObject(Long id);
	
	List<SysRoleMenuEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleMenuEntity sysRoleMenu);
	
	void update(SysRoleMenuEntity sysRoleMenu);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}

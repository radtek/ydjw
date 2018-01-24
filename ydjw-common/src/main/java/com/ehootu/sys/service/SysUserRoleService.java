package com.ehootu.sys.service;

import com.ehootu.sys.entity.SysUserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * 
 * @author zhangyong
 * @email zhangyong@ehootu.com
 * @date 2018-01-24 15:10:15
 */
public interface SysUserRoleService {
	
	SysUserRoleEntity queryObject(Long id);
	
	List<SysUserRoleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysUserRoleEntity sysUserRole);
	
	void update(SysUserRoleEntity sysUserRole);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}

package com.ehootu.sys.service;

import com.ehootu.sys.entity.SysRoleDeptEntity;

import java.util.List;
import java.util.Map;

/**
 * 角色与部门对应关系
 * 
 * @author zhangyong
 * @email zhangyong@ehootu.com
 * @date 2018-01-24 15:10:14
 */
public interface SysRoleDeptService {
	
	SysRoleDeptEntity queryObject(Long id);
	
	List<SysRoleDeptEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleDeptEntity sysRoleDept);
	
	void update(SysRoleDeptEntity sysRoleDept);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}

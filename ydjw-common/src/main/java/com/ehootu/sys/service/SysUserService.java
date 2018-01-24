package com.ehootu.sys.service;

import com.ehootu.sys.entity.SysUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author zhangyong
 * @email zhangyong@ehootu.com
 * @date 2018-01-24 15:10:14
 */
public interface SysUserService {
	
	SysUserEntity queryObject(Long userId);
	
	List<SysUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysUserEntity sysUser);
	
	void update(SysUserEntity sysUser);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);
}

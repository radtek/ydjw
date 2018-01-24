package com.ehootu.sys.service.impl;

import com.ehootu.sys.dao.SysUserDao;
import com.ehootu.sys.entity.SysUserEntity;
import com.ehootu.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	public SysUserEntity queryObject(Long userId){
		return sysUserDao.queryObject(userId);
	}
	
	@Override
	public List<SysUserEntity> queryList(Map<String, Object> map){
		return sysUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysUserDao.queryTotal(map);
	}
	
	@Override
	public void save(SysUserEntity sysUser){
		sysUserDao.save(sysUser);
	}
	
	@Override
	public void update(SysUserEntity sysUser){
		sysUserDao.update(sysUser);
	}
	
	@Override
	public void delete(Long userId){
		sysUserDao.delete(userId);
	}
	
	@Override
	public void deleteBatch(Long[] userIds){
		sysUserDao.deleteBatch(userIds);
	}
	
}

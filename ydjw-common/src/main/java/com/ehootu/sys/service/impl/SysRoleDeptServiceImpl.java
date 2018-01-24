package com.ehootu.sys.service.impl;

import com.ehootu.sys.dao.SysRoleDeptDao;
import com.ehootu.sys.entity.SysRoleDeptEntity;
import com.ehootu.sys.service.SysRoleDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("sysRoleDeptService")
public class SysRoleDeptServiceImpl implements SysRoleDeptService {
	@Autowired
	private SysRoleDeptDao sysRoleDeptDao;
	
	@Override
	public SysRoleDeptEntity queryObject(Long id){
		return sysRoleDeptDao.queryObject(id);
	}
	
	@Override
	public List<SysRoleDeptEntity> queryList(Map<String, Object> map){
		return sysRoleDeptDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return sysRoleDeptDao.queryTotal(map);
	}
	
	@Override
	public void save(SysRoleDeptEntity sysRoleDept){
		sysRoleDeptDao.save(sysRoleDept);
	}
	
	@Override
	public void update(SysRoleDeptEntity sysRoleDept){
		sysRoleDeptDao.update(sysRoleDept);
	}
	
	@Override
	public void delete(Long id){
		sysRoleDeptDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		sysRoleDeptDao.deleteBatch(ids);
	}
	
}

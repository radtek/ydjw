package com.ehootu.sys.controller;

import com.ehootu.core.util.PageUtils;
import com.ehootu.core.util.Query;
import com.ehootu.core.util.R;
import com.ehootu.sys.entity.SysRoleEntity;
import com.ehootu.sys.service.SysRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 角色
 * 
 * @author zhangyong
 * @email zhangyong@ehootu.com
 * @date 2018-01-24 15:10:14
 */
@RestController
@RequestMapping("/sys/sysrole")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:sysrole:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysRoleEntity> sysRoleList = sysRoleService.queryList(query);
		int total = sysRoleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysRoleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:sysrole:info")
	public R info(@PathVariable("roleId") Long roleId){
		SysRoleEntity sysRole = sysRoleService.queryObject(roleId);
		
		return R.ok().put("sysRole", sysRole);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:sysrole:save")
	public R save(@RequestBody SysRoleEntity sysRole){
		sysRoleService.save(sysRole);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:sysrole:update")
	public R update(@RequestBody SysRoleEntity sysRole){
		sysRoleService.update(sysRole);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:sysrole:delete")
	public R delete(@RequestBody Long[] roleIds){
		sysRoleService.deleteBatch(roleIds);
		
		return R.ok();
	}
	
}

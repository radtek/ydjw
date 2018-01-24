package com.ehootu.sys.controller;

import com.ehootu.core.util.PageUtils;
import com.ehootu.core.util.Query;
import com.ehootu.core.util.R;
import com.ehootu.sys.entity.SysRoleMenuEntity;
import com.ehootu.sys.service.SysRoleMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 角色与菜单对应关系
 * 
 * @author zhangyong
 * @email zhangyong@ehootu.com
 * @date 2018-01-24 15:10:15
 */
@RestController
@RequestMapping("/sys/sysrolemenu")
public class SysRoleMenuController {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:sysrolemenu:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysRoleMenuEntity> sysRoleMenuList = sysRoleMenuService.queryList(query);
		int total = sysRoleMenuService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysRoleMenuList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:sysrolemenu:info")
	public R info(@PathVariable("id") Long id){
		SysRoleMenuEntity sysRoleMenu = sysRoleMenuService.queryObject(id);
		
		return R.ok().put("sysRoleMenu", sysRoleMenu);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:sysrolemenu:save")
	public R save(@RequestBody SysRoleMenuEntity sysRoleMenu){
		sysRoleMenuService.save(sysRoleMenu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:sysrolemenu:update")
	public R update(@RequestBody SysRoleMenuEntity sysRoleMenu){
		sysRoleMenuService.update(sysRoleMenu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:sysrolemenu:delete")
	public R delete(@RequestBody Long[] ids){
		sysRoleMenuService.deleteBatch(ids);
		
		return R.ok();
	}
	
}

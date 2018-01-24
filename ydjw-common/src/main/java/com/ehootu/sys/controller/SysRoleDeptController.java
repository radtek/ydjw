package com.ehootu.sys.controller;

import com.ehootu.core.util.PageUtils;
import com.ehootu.core.util.Query;
import com.ehootu.core.util.R;
import com.ehootu.sys.entity.SysRoleDeptEntity;
import com.ehootu.sys.service.SysRoleDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 角色与部门对应关系
 * 
 * @author zhangyong
 * @email zhangyong@ehootu.com
 * @date 2018-01-24 15:10:14
 */
@RestController
@RequestMapping("/sys/sysroledept")
public class SysRoleDeptController {
	@Autowired
	private SysRoleDeptService sysRoleDeptService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:sysroledept:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysRoleDeptEntity> sysRoleDeptList = sysRoleDeptService.queryList(query);
		int total = sysRoleDeptService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysRoleDeptList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:sysroledept:info")
	public R info(@PathVariable("id") Long id){
		SysRoleDeptEntity sysRoleDept = sysRoleDeptService.queryObject(id);
		
		return R.ok().put("sysRoleDept", sysRoleDept);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:sysroledept:save")
	public R save(@RequestBody SysRoleDeptEntity sysRoleDept){
		sysRoleDeptService.save(sysRoleDept);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:sysroledept:update")
	public R update(@RequestBody SysRoleDeptEntity sysRoleDept){
		sysRoleDeptService.update(sysRoleDept);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:sysroledept:delete")
	public R delete(@RequestBody Long[] ids){
		sysRoleDeptService.deleteBatch(ids);
		
		return R.ok();
	}
	
}

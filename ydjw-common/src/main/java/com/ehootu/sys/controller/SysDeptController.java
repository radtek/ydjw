package com.ehootu.sys.controller;

import com.ehootu.core.util.PageUtils;
import com.ehootu.core.util.Query;
import com.ehootu.core.util.R;
import com.ehootu.sys.entity.SysDeptEntity;
import com.ehootu.sys.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 部门管理
 * 
 * @author zhangyong
 * @email zhangyong@ehootu.com
 * @date 2018-01-24 15:10:15
 */
@RestController
@RequestMapping("/sys/sysdept")
public class SysDeptController {
	@Autowired
	private SysDeptService sysDeptService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:sysdept:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysDeptEntity> sysDeptList = sysDeptService.queryList(query);
		int total = sysDeptService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysDeptList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{deptId}")
	@RequiresPermissions("sys:sysdept:info")
	public R info(@PathVariable("deptId") Long deptId){
		SysDeptEntity sysDept = sysDeptService.queryObject(deptId);
		
		return R.ok().put("sysDept", sysDept);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:sysdept:save")
	public R save(@RequestBody SysDeptEntity sysDept){
		sysDeptService.save(sysDept);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:sysdept:update")
	public R update(@RequestBody SysDeptEntity sysDept){
		sysDeptService.update(sysDept);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:sysdept:delete")
	public R delete(@RequestBody Long[] deptIds){
		sysDeptService.deleteBatch(deptIds);
		
		return R.ok();
	}
	
}

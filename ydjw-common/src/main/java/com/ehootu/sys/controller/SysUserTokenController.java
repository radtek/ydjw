package com.ehootu.sys.controller;

import com.ehootu.core.util.PageUtils;
import com.ehootu.core.util.Query;
import com.ehootu.core.util.R;
import com.ehootu.sys.entity.SysUserTokenEntity;
import com.ehootu.sys.service.SysUserTokenService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 系统用户Token
 * 
 * @author zhangyong
 * @email zhangyong@ehootu.com
 * @date 2018-01-24 15:10:15
 */
@RestController
@RequestMapping("/sys/sysusertoken")
public class SysUserTokenController {
	@Autowired
	private SysUserTokenService sysUserTokenService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:sysusertoken:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysUserTokenEntity> sysUserTokenList = sysUserTokenService.queryList(query);
		int total = sysUserTokenService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysUserTokenList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:sysusertoken:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserTokenEntity sysUserToken = sysUserTokenService.queryObject(userId);
		
		return R.ok().put("sysUserToken", sysUserToken);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:sysusertoken:save")
	public R save(@RequestBody SysUserTokenEntity sysUserToken){
		sysUserTokenService.save(sysUserToken);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:sysusertoken:update")
	public R update(@RequestBody SysUserTokenEntity sysUserToken){
		sysUserTokenService.update(sysUserToken);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:sysusertoken:delete")
	public R delete(@RequestBody Long[] userIds){
		sysUserTokenService.deleteBatch(userIds);
		
		return R.ok();
	}
	
}

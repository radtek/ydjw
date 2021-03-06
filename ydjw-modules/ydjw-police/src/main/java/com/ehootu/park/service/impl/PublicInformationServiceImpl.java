package com.ehootu.park.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ehootu.core.util.HttpRequest;
import com.ehootu.park.dao.PublicInformationDao;
import com.ehootu.park.model.PublicInformationEntity;
import com.ehootu.park.service.PublicInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("publicInformationService")
public class PublicInformationServiceImpl implements PublicInformationService {

	private static Logger log = LoggerFactory.getLogger(PublicInformationServiceImpl.class);
	
	@Autowired
	private PublicInformationDao publicInformationDao;

	@Override
	public int save(PublicInformationEntity publicInformation) {
		return publicInformationDao.save(publicInformation);
	}

	@Override
	public List<PublicInformationEntity> selectOne(String dizhibianma) {
		dizhibianma = convertDzbm(dizhibianma);
		return publicInformationDao.selectOne(dizhibianma);
	}

	@Override
	public List<PublicInformationEntity> selectAddress(String enterprise_address) {
		return publicInformationDao.selectAddress(enterprise_address);
	}

	@Override
	public PublicInformationEntity select(String dizhibianma) {
		return publicInformationDao.select(dizhibianma);
	}

	@Override
	public void update(PublicInformationEntity PublicInformation) {
		publicInformationDao.update(PublicInformation);
		
	}

	@Override
	public int saveUpdate(PublicInformationEntity publicEntity) {
		// 将数据库中存在同样地址的信息改为弃用
		publicEntity.setSign(0);
		publicInformationDao.updateSign(publicEntity);
		// 然后重新添加最新的一条数据
		publicEntity.setDate(new Date());
		publicEntity.setSign(1);
		save(publicEntity);
		// 最后返回刚刚插入数据的主键 id
		return publicEntity.getId();
	}

	/**
	 * 如果结尾是六个0（楼栋号编码），则去掉后六个零，方便模糊查询
	 * @param diZhiBianMa 标准地址编码
	 * @return
	 */
	private static String convertDzbm(String diZhiBianMa){
		// 如果结尾是六个0（楼栋号编码），则去掉后六个零，方便模糊查询
		while (diZhiBianMa.endsWith("000000")) {
			diZhiBianMa = diZhiBianMa.substring(0, diZhiBianMa.length() - 6);
		}
		return diZhiBianMa;
	}

	@Override
	public List parkErcodeScan(String diZhiBianMa) {
		diZhiBianMa = convertDzbm(diZhiBianMa);
		// 查询公共表已登记企业
		List<PublicInformationEntity> publicInformationEntityList = selectOne(diZhiBianMa);
		// 调用基库地址
		String param = "dizhibianma="+diZhiBianMa;
		List<PublicInformationEntity> list = selectDmdzFromBaseDB(HttpRequest.GET_URL_DZ_DZXX_BM, param, "get", publicInformationEntityList);
		publicInformationEntityList.addAll(list);
		return publicInformationEntityList;
	}

	private static String encode(String str){
		try {
			return URLEncoder.encode(str,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List parkErCodeSearch(String xzqhmc, String jlxmc, String mphmc, String mphhz, String lphmc, String lphhz) {
		StringBuilder public_param = new StringBuilder();
		String dmdz_param;
		if(lphmc.equals("0")){
			System.out.println("没有楼牌号");
			public_param.append(xzqhmc).append(jlxmc).append(mphmc).append(mphhz);
			dmdz_param = "xzqhmc="+encode(xzqhmc)+"&jlxmc="+encode(jlxmc)+"&mphmc="+encode(mphmc)+"&mphhz="+encode(mphhz);
		}else{
			System.out.println("存在楼牌号");
			public_param.append(xzqhmc).append(jlxmc).append(mphmc).append(mphhz).append(lphmc).append(lphhz);
			dmdz_param = "xzqhmc="+encode(xzqhmc)+"&jlxmc="+encode(jlxmc)+"&mphmc="+encode(mphmc)+"&mphhz="+encode(mphhz)+"&lphmc="+encode(lphmc)+"&lphhz="+encode(lphhz);
		}

		// 查询公共表已登记企业
		List<PublicInformationEntity> publicInformationEntityList = selectAddress(public_param.toString());
		// 调用基库地址
		List<PublicInformationEntity> list = selectDmdzFromBaseDB(HttpRequest.POST_URL_DZ_DZXX_MC, dmdz_param, "post", publicInformationEntityList);
		publicInformationEntityList.addAll(list);
		return publicInformationEntityList;
	}

	/**
	 * 调用基库接口，查询地名地址相关信息
	 * @param param post 类型时的参数
	 * @param type BM 通过地址编码查询；MC 通过地址名称查询
	 * @return List<PublicInformationEntity>
	 */
	public List<PublicInformationEntity> selectDmdzFromBaseDB(String url, String param, String type, List<PublicInformationEntity> publicList) {
		List<PublicInformationEntity> list = new ArrayList<PublicInformationEntity>();
		String data_flag = null;
		try {
			String result = null;
			if(type.toLowerCase().equals("get")){
				// 调用基库地址，返回地址信息
				result = HttpRequest.sendGet(url, param);
				data_flag = "data";
			}
			else if(type.toLowerCase().equals("post")){
				result = HttpRequest.sendPost(url, param);
				data_flag = "page";
			}
			// json字符串转换成jsonobject对象
			JSONObject jsonObject = JSON.parseObject(result);
			JSONArray jsarr = jsonObject.getJSONArray(data_flag);
			//遍历数据获取地址全称
			if (jsarr == null){
				return list;
			}
			boolean flag = false;
			for(int i=0;i<jsarr.size();i++){
				JSONObject ao = jsarr.getJSONObject(i);
				String dizhibianma = (String)ao.get("dizhibianma");//地址编码
				// 判断该地址编码是否已经注册了公司企业信息
				flag = false;
				for (PublicInformationEntity entity: publicList){
					if (dizhibianma.equals(entity.getDiZhiBianMa())){
						flag = true;
						break;
					}
				}
				if(flag){
					continue;
				}

				String qhmc = (String) ao.get("xingzhengquhuamingcheng");// 区划名称
				String jdmc = (String)ao.get("jiedaoxiangmingcheng");// 街道名称
				String mpmc = (String)ao.get("menpaihaomingcheng");//门牌名称
				String mphz = (String)ao.get("menpaihaohouzhui");//门牌后缀
				String ldhmc = (String)ao.get("loudonghaomingcheng");//楼栋号名称
				String ldhhz = (String)ao.get("loudonghaohouzhui");//楼栋号后缀
				String dyhmc = (String)ao.get("danyuanhaomingcheng");//单元号名称
				String dyhhz = (String)ao.get("danyuanhaohouzhui");//单元号后缀
				String chmc = (String)ao.get("cenghaomingcheng");//层号名称
				String chhz = (String)ao.get("cenghaohouzhui");// 层号后缀
				String hhmc =(String) ao.get("huhaomingcheng");//户号名称
				String hhhz = (String)ao.get("huhaohouzhui");//户号后缀
				String diZhiJingDu = (String)ao.get("dizhijingdu");//地址经度
				String diZhiWeiDu = (String)ao.get("dizhiweidu");//地址纬度
				String enterpriseAddress = null; // 企业地址
				if(ldhmc ==null){
					enterpriseAddress = qhmc + jdmc + mpmc + mphz;
				}else{
					enterpriseAddress = qhmc + jdmc + mpmc + mphz + ldhmc + ldhhz + dyhmc + dyhhz + chmc + chhz + hhmc + hhhz;
				}
				// 封装到公共表实体类中
				PublicInformationEntity public_info = new PublicInformationEntity();
				public_info.setDiZhiBianMa(dizhibianma);
				public_info.setEnterpriseName("");
				public_info.setEnterpriseAddress(enterpriseAddress);
				public_info.setDiZhiJingDu(diZhiJingDu);
				public_info.setDiZhiWeiDu(diZhiWeiDu);
				list.add(public_info);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}

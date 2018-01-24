package com.ehootu.notice.service.impl;


import com.ehootu.core.generic.GenericDao;
import com.ehootu.core.generic.GenericServiceImpl;

import com.ehootu.notice.dao.NoticePoliceMapper;
import com.ehootu.notice.model.NoticePolice;
import com.ehootu.notice.service.NoticePoliceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 通知公告Service实现类
 */
@Service
public class NoticePoliceServiceImpl extends GenericServiceImpl<NoticePolice, Integer> implements NoticePoliceService {

	@Autowired
	private NoticePoliceMapper noticePoliceMapper;


	@Override
	public GenericDao<NoticePolice, Integer> getDao() {
		return noticePoliceMapper;
	}

}

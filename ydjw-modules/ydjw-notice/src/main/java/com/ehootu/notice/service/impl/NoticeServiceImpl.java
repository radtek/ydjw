package com.ehootu.notice.service.impl;

import com.ehootu.core.generic.GenericDao;
import com.ehootu.core.generic.GenericServiceImpl;
import com.ehootu.core.util.DateUtils;
import com.ehootu.notice.dao.NoticeMapper;
import com.ehootu.notice.dao.NoticePoliceMapper;
import com.ehootu.notice.model.Notice;
import com.ehootu.notice.model.NoticePolice;
import com.ehootu.notice.model.NoticePoliceExample;
import com.ehootu.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通知公告Service实现类
 */
@Service
public class NoticeServiceImpl extends GenericServiceImpl<Notice, Integer> implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Autowired
	private NoticePoliceMapper noticePoliceMapper;

	@Override
	public GenericDao<Notice, Integer> getDao() {
		return noticeMapper;
	}

	@Override
	public List<Notice> queryListByPolice(Integer id) {
		List<Notice> noticeList = new ArrayList<>();
		NoticePoliceExample noticePoliceExample = new NoticePoliceExample();
		noticePoliceExample.createCriteria().andPoliceIdEqualTo(id);
		List<NoticePolice> noticePoliceList = noticePoliceMapper.selectByExample(noticePoliceExample);
		for (NoticePolice noticePolice:noticePoliceList){
			Notice notice = noticeMapper.selectByPrimaryKey(noticePolice.getNoticeId());
			if (notice!=null){
				Date createTime = notice.getCreateTime();
				if (createTime!=null){
					String createDate = DateUtils.getString(createTime);
					notice.setCreateTime(null);
					notice.setCreateDate(createDate);
				}
				notice.setNoticePolice(noticePolice);
				noticeList.add(notice);
			}
		}
		return noticeList;
	}
}

package com.ehootu.notice.controller;

import org.springframework.stereotype.Controller;

import com.ehootu.core.generic.BaseController;
import java.util.List;

import com.ehootu.notice.model.Notice;
import com.ehootu.notice.model.NoticePolice;
import com.ehootu.notice.service.NoticePoliceService;
import com.ehootu.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 通知公告控制器
 * 
 * @author KongXiaoPing
 *
 *         2017年6月13日
 */
@Controller
@RequestMapping(value = "/notice")
public class NoticeController extends BaseController {
	
	private final static Logger log = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private NoticePoliceService noticePoliceService;

	@RequestMapping(value = "/list")
	public void list(Integer id){
		List<Notice> list = noticeService.queryListByPolice(id);
		resultSuccess(list);
	}

	@RequestMapping(value = "/info")
	public void info(NoticePolice noticePolice){
		noticePolice.setSeeFlag(1);
		noticePoliceService.update(noticePolice);
		Notice notice = noticeService.selectById(noticePolice.getNoticeId());
		resultSuccess(notice);
	}

}

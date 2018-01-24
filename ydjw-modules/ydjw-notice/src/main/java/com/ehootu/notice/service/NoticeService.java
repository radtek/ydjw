package com.ehootu.notice.service;


import com.ehootu.core.generic.GenericService;
import com.ehootu.notice.model.Notice;

import java.util.List;


/**
 * 通知公告 接口
 **/
public interface NoticeService extends GenericService<Notice, Integer> {

    /**
     * @param id
     * @return
     */
    List<Notice> queryListByPolice(Integer id);

}

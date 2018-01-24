package com.ehootu.notice.dao;

import com.ehootu.core.generic.GenericDao;
import java.util.List;

import com.ehootu.notice.model.NoticePolice;
import com.ehootu.notice.model.NoticePoliceExample;
import org.apache.ibatis.annotations.Param;

public interface NoticePoliceMapper extends GenericDao<NoticePolice, Integer> {
    long countByExample(NoticePoliceExample example);

    int deleteByExample(NoticePoliceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(NoticePolice record);

    int insertSelective(NoticePolice record);

    List<NoticePolice> selectByExample(NoticePoliceExample example);

    NoticePolice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") NoticePolice record, @Param("example") NoticePoliceExample example);

    int updateByExample(@Param("record") NoticePolice record, @Param("example") NoticePoliceExample example);

    int updateByPrimaryKeySelective(NoticePolice record);

    int updateByPrimaryKey(NoticePolice record);
}
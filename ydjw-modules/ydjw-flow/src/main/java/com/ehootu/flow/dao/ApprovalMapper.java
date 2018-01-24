package com.ehootu.flow.dao;

import com.ehootu.core.generic.GenericDao;
import com.ehootu.flow.model.Approval;
import com.ehootu.flow.model.ApprovalExample;
import com.ehootu.flow.model.OutPersonFlowApproval;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApprovalMapper extends GenericDao<Approval, Integer> {
    long countByExample(ApprovalExample example);

    int deleteByExample(ApprovalExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Approval record);

    int insertSelective(Approval record);

    List<Approval> selectByExample(ApprovalExample example);

    Approval selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Approval record, @Param("example") ApprovalExample example);

    int updateByExample(@Param("record") Approval record, @Param("example") ApprovalExample example);

    int updateByPrimaryKeySelective(Approval record);

    int updateByPrimaryKey(Approval record);
    /**
     * 根据用户id查询审核结果 按登记时间降序排列
     * @param userId 用户id
     */
    List<OutPersonFlowApproval> queryApprovalByUserId(Integer userId);
    /**
     * 根据流动人口id查询审核对象
     * @param id
     * @return
     */
    Approval selectByPersonFlowId(Integer id);
}
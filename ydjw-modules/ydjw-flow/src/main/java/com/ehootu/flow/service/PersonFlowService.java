package com.ehootu.flow.service;

import com.ehootu.core.generic.GenericService;
import com.ehootu.flow.model.InputPersonFlow;
import com.ehootu.flow.model.OutApprovalList;
import com.ehootu.flow.model.OutPersonFlowApproval;
import com.ehootu.flow.model.OutResultList;
import com.ehootu.flow.model.PersonFlow;
import com.ehootu.user.model.Police;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Title:PersonFlowService
 * @Author: KongXiaoPing
 * @Date: 2017-09-21 16:02
 * @Description: 流动人口业务接口
 */
@Service
public interface PersonFlowService extends GenericService<PersonFlow, Integer> {
    /**
     * 根据所属派出所查询流动人口列表
     * @param inputPersonFlow 查询参数
     * @return 流动人口列表
     */
    List<OutPersonFlowApproval> queryPersonFlow(InputPersonFlow inputPersonFlow);

    /**
     * 添加流动人口
     * @param bean 流动人口输入参数实体
     */
    void addPersonFlow(InputPersonFlow inputPersonFlow)throws Exception;

    /**
     * 根据流动人口id查询
     * @param id
     * @return
     */
    OutPersonFlowApproval selectByPersonFlowId(Integer id);

    /**
     * 根据警察id查询警员信息
     * @param policeId
     * @return
     */
    Police selectByPoliceId(Integer policeId);

    /**
     * 根据警察id查询流动人口列表 分页
     * @param policeId
     * @return
     */
    List<OutPersonFlowApproval> appQueryPersonFlow(Map<String, Object> map);

    /**
     * 查询 审核列表 排序
     * @param params
     * @return
     */
    List<OutApprovalList> queryApprovalList(Map<String, Object> params);

    /**
     * 根据用户登录id查询办事结果 列表
     * @param params
     * @return
     */
    List<OutResultList> queryResultByUserId(Map<String, Object> params);



    /**
     * 根据业务id查询办事详情
     * @param outResultList
     * @return
     */
    OutResultList queryResultById(OutResultList outResultList);
}

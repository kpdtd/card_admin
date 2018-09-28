package com.anl.card.service;

import com.anl.card.persistence.po.UserFlowUsedDay;
import com.anl.card.vo.UserFlowUsedDayExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: UserFlowUsedDayService
 * 创建日期: 
 * 功能描述: 
 */
public interface UserFlowUsedDayService extends BaseService<UserFlowUsedDay> {
    List<UserFlowUsedDayExt> getExtListByMap(Map<String, Object> model);
    int getExtListCount (Map<String, Object> model);
}
package com.anl.card.persistence.mapper;

import com.anl.card.persistence.mapper.BaseMapper;
import com.anl.card.persistence.po.UserFlowUsedDay;
import com.anl.card.vo.UserFlowUsedDayExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: UserFlowUsedDay
 * 创建日期: 
 * 功能描述: 
 */
public interface UserFlowUsedDayMapper extends BaseMapper<UserFlowUsedDay> {

    List<UserFlowUsedDayExt> getExtListByMap(Map<String, Object> model);
    int getExtListCount (Map<String, Object> model);
 }
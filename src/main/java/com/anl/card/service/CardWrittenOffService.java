package com.anl.card.service;

import com.anl.card.persistence.po.CardWrittenOff;
import com.anl.card.vo.CardWrittenOffExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: CardWrittenOffService
 * 创建日期: 
 * 功能描述: 
 */
public interface CardWrittenOffService extends BaseService<CardWrittenOff> {
    List<CardWrittenOffExt> getListByCondition(Map<String, Object> model);
}
package com.anl.card.persistence.mapper;

import com.anl.card.persistence.mapper.BaseMapper;
import com.anl.card.persistence.po.CardWrittenOff;
import com.anl.card.vo.CardWrittenOffExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: CardWrittenOff
 * 创建日期: 
 * 功能描述: 
 */
public interface CardWrittenOffMapper extends BaseMapper<CardWrittenOff> {
    List<CardWrittenOffExt> getListByCondition(Map<String, Object> model);
}
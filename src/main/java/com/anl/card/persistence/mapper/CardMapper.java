package com.anl.card.persistence.mapper;

import java.util.List;
import java.util.Map;

import com.anl.card.persistence.po.Card;
import com.anl.card.vo.CardExt;
/** 
 * 类名: Card
 * 创建日期: 
 * 功能描述: 
 */
public interface CardMapper extends BaseMapper<Card> {
	List<CardExt> getListByCondition(Map<String, Object> model);
}
package com.anl.card.service;

import java.util.List;
import java.util.Map;

import com.anl.card.persistence.po.Card;
import com.anl.card.vo.CardExt;
/** 
 * 类名: CardService
 * 创建日期: 
 * 功能描述: 
 */
public interface CardService extends BaseService<Card> {
	List<CardExt> getListByCondition(Map<String, Object> model);
}
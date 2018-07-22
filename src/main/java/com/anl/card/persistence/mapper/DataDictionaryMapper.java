package com.anl.card.persistence.mapper;


import com.anl.card.persistence.po.DataDictionary;

/**
 * 类名: DataDictionary
 * 创建日期: 
 * 功能描述: 
 */
public interface DataDictionaryMapper extends BaseMapper<DataDictionary> {

	DataDictionary getValueByKey(String name);

}
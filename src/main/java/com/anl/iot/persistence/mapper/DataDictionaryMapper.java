package com.anl.iot.persistence.mapper;


import com.anl.iot.persistence.po.DataDictionary;

/**
 * 类名: DataDictionary
 * 创建日期: 
 * 功能描述: 
 */
public interface DataDictionaryMapper extends BaseMapper<DataDictionary> {

	DataDictionary getValueByKey(String name);

}
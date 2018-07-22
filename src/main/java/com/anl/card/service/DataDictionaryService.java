package com.anl.card.service;

import com.anl.card.persistence.po.DataDictionary;
import com.anl.card.persistence.po.SelectGroup;

import java.util.List;


/** 
 * 类名: DataDictionaryService
 * 创建日期: 
 * 功能描述: 
 */
public interface DataDictionaryService extends BaseService<DataDictionary> {

	List<SelectGroup> getValueListByKey(String string);

	DataDictionary getDicByKey(String key);

}
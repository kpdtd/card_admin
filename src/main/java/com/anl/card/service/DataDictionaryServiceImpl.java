
package com.anl.card.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.anl.card.persistence.mapper.DataDictionaryMapper;
import com.anl.card.persistence.po.DataDictionary;
import com.anl.card.persistence.po.SelectGroup;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

	@Autowired
	DataDictionaryMapper dataDictionaryMapper;
	
	@Override
	public int insert(DataDictionary record) throws SQLException {
		return dataDictionaryMapper.insert(record);
	}

	@Override
	public int update(DataDictionary record) throws SQLException {
		return dataDictionaryMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return dataDictionaryMapper.deleteById(id);
	}

	@Override
	public DataDictionary getById(Integer id) throws SQLException {
		return dataDictionaryMapper.getById(id);
	}

	@Override
	public List<DataDictionary> getListByMap(Map<String, Object> condition) throws SQLException {
		return dataDictionaryMapper.getListByMap(condition);
	}
	
	@Override
	public List<DataDictionary> getListByPo(DataDictionary record) throws SQLException {
		return dataDictionaryMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return dataDictionaryMapper.count(condition);
	}

	@Override
	public List<SelectGroup> getValueListByKey(String key) {
		DataDictionary dictionary = dataDictionaryMapper.getValueByKey(key);
		String value = dictionary.getValue();
		List<SelectGroup> groupList = new ArrayList<SelectGroup>();
		if (StringUtils.isNotBlank(value)) {
			String[] content = value.split(";");
			for (String con : content) {
				SelectGroup group = new SelectGroup();
				String[] kv = con.split("=");
				group.setId(kv[0]);
				group.setName(kv[1]);
				groupList.add(group);
			}
		}
		return groupList;
	}

	@Override
	public DataDictionary getDicByKey(String key) {
		return dataDictionaryMapper.getValueByKey(key);
	}
	
}


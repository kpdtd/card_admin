
package com.anl.card.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.anl.card.vo.UserFlowUsedDayExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.card.persistence.mapper.UserFlowUsedDayMapper;
import com.anl.card.persistence.po.UserFlowUsedDay;

@Service
public class UserFlowUsedDayServiceImpl implements UserFlowUsedDayService {

	@Autowired
	UserFlowUsedDayMapper userFlowUsedDayMapper;
	
	@Override
	public int insert(UserFlowUsedDay record) throws SQLException {
		return userFlowUsedDayMapper.insert(record);
	}

	@Override
	public int update(UserFlowUsedDay record) throws SQLException {
		return userFlowUsedDayMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userFlowUsedDayMapper.deleteById(id);
	}

	@Override
	public UserFlowUsedDay getById(Integer id) throws SQLException {
		return userFlowUsedDayMapper.getById(id);
	}

	@Override
	public List<UserFlowUsedDay> getListByMap(Map<String, Object> condition) throws SQLException {
		return userFlowUsedDayMapper.getListByMap(condition);
	}
	
	@Override
	public List<UserFlowUsedDay> getListByPo(UserFlowUsedDay record) throws SQLException {
		return userFlowUsedDayMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userFlowUsedDayMapper.count(condition);
	}

	@Override
	public List<UserFlowUsedDayExt> getExtListByMap(Map<String, Object> model) {
		return userFlowUsedDayMapper.getExtListByMap(model);
	}

	@Override
	public int getExtListCount(Map<String, Object> model) {
		return userFlowUsedDayMapper.getExtListCount(model);
	}
}


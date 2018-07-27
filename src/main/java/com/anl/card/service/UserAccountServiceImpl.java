
package com.anl.card.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.anl.card.vo.UserAccountExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.card.persistence.mapper.UserAccountMapper;
import com.anl.card.persistence.po.UserAccount;

@Service
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	UserAccountMapper userAccountMapper;
	
	@Override
	public int insert(UserAccount record) throws SQLException {
		return userAccountMapper.insert(record);
	}

	@Override
	public int update(UserAccount record) throws SQLException {
		return userAccountMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return userAccountMapper.deleteById(id);
	}

	@Override
	public UserAccount getById(Integer id) throws SQLException {
		return userAccountMapper.getById(id);
	}

	@Override
	public List<UserAccount> getListByMap(Map<String, Object> condition) throws SQLException {
		return userAccountMapper.getListByMap(condition);
	}
	
	@Override
	public List<UserAccount> getListByPo(UserAccount record) throws SQLException {
		return userAccountMapper.getListByPo(record);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return userAccountMapper.count(condition);
	}

	@Override
	public List<UserAccountExt> getListByCondition(Map<String, Object> model) {
		return userAccountMapper.getListByCondition(model);
	}
}


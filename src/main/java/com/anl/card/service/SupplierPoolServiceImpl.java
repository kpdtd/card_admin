
package com.anl.card.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.anl.card.persistence.po.SupplierPool;
import com.anl.card.vo.SupplierPoolExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anl.card.persistence.mapper.SupplierPoolMapper;

@Service
public class SupplierPoolServiceImpl implements SupplierPoolService {

	@Autowired
	SupplierPoolMapper supplierPoolMapper;
	
	@Override
	public int insert(SupplierPool record) throws SQLException {
		return supplierPoolMapper.insert(record);
	}

	@Override
	public int update(SupplierPool record) throws SQLException {
		return supplierPoolMapper.update(record);
	}

	@Override
	public int deleteById(Integer id) throws SQLException {
		return supplierPoolMapper.deleteById(id);
	}

	@Override
	public SupplierPool getById(Integer id) throws SQLException {
		return supplierPoolMapper.getById(id);
	}

	@Override
	public List<SupplierPool> getListByPo(SupplierPool record) throws SQLException {
		return null;
	}

	@Override
	public List<SupplierPool> getListByMap(Map<String, Object> condition) throws SQLException {
		return supplierPoolMapper.getListByMap(condition);
	}

	@Override
	public int count(Map<String, Object> condition) throws SQLException {
		return supplierPoolMapper.count(condition);
	}

	@Override
	public List<SupplierPoolExt> getListByCondition(Map<String, Object> model) {
		return supplierPoolMapper.getListByCondition(model);
	}
}


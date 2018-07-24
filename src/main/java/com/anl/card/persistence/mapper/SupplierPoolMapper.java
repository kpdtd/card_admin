package com.anl.card.persistence.mapper;

import com.anl.card.persistence.mapper.BaseMapper;
import com.anl.card.persistence.po.SupplierPool;
import com.anl.card.vo.SupplierPoolExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: SupplierPool
 * 创建日期: 
 * 功能描述: 
 */
public interface SupplierPoolMapper extends BaseMapper<SupplierPool> {

    List<SupplierPoolExt> getListByCondition(Map<String, Object> model);
}
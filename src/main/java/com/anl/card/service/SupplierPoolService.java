package com.anl.card.service;

import com.anl.card.persistence.po.SupplierPool;
import com.anl.card.vo.SupplierPoolExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: SupplierPoolService
 * 创建日期: 
 * 功能描述: 
 */
public interface SupplierPoolService extends BaseService<SupplierPool> {
    List<SupplierPoolExt> getListByCondition(Map<String, Object> model);
}
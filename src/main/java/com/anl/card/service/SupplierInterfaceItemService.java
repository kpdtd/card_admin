package com.anl.card.service;

import com.anl.card.persistence.po.SupplierInterfaceItem;
import com.anl.card.vo.SupplierInterfaceItemExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: SupplierInterfaceItemService
 * 创建日期: 
 * 功能描述: 
 */
public interface SupplierInterfaceItemService extends BaseService<SupplierInterfaceItem> {
    List<SupplierInterfaceItemExt> getListByCondition(Map<String, Object> model);
}
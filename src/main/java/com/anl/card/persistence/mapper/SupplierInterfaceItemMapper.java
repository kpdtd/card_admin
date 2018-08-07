package com.anl.card.persistence.mapper;

import com.anl.card.persistence.mapper.BaseMapper;
import com.anl.card.persistence.po.SupplierInterfaceItem;
import com.anl.card.vo.SupplierInterfaceItemExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: SupplierInterfaceItem
 * 创建日期: 
 * 功能描述: 
 */
public interface SupplierInterfaceItemMapper extends BaseMapper<SupplierInterfaceItem> {
    List<SupplierInterfaceItemExt> getListByCondition(Map<String, Object> model);
}
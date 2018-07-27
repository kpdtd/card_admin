package com.anl.card.service;

import com.anl.card.persistence.po.User;
import com.anl.card.vo.SupplierPoolExt;
import com.anl.card.vo.UserExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: UserService
 * 创建日期: 
 * 功能描述: 
 */
public interface UserService extends BaseService<User> {
    List<UserExt> getListByCondition(Map<String, Object> model);
}
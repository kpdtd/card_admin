package com.anl.card.service;

import com.anl.card.persistence.po.UserAccount;
import com.anl.card.vo.UserAccountExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: UserAccountService
 * 创建日期: 
 * 功能描述: 
 */
public interface UserAccountService extends BaseService<UserAccount> {
    List<UserAccountExt> getListByCondition(Map<String, Object> model);
}
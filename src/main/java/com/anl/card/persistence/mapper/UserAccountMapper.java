package com.anl.card.persistence.mapper;

import com.anl.card.persistence.mapper.BaseMapper;
import com.anl.card.persistence.po.UserAccount;
import com.anl.card.vo.UserAccountExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: UserAccount
 * 创建日期: 
 * 功能描述: 
 */
public interface UserAccountMapper extends BaseMapper<UserAccount> {
    List<UserAccountExt> getListByCondition(Map<String, Object> model);
}
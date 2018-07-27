package com.anl.card.persistence.mapper;

import com.anl.card.persistence.mapper.BaseMapper;
import com.anl.card.persistence.po.User;
import com.anl.card.vo.UserExt;

import java.util.List;
import java.util.Map;

/**
 * 类名: User
 * 创建日期: 
 * 功能描述: 
 */
public interface UserMapper extends BaseMapper<User> {
    List<UserExt> getListByCondition(Map<String, Object> model);
}
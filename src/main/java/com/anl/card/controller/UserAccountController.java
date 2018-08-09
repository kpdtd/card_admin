package com.anl.card.controller;

import java.util.*;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.UserAccountChangeHistory;
import com.anl.card.service.UserAccountChangeHistoryService;
import com.anl.card.vo.UserAccountExt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.UserAccount;
import com.anl.card.service.UserAccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: UserAccountController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/userAccount")
public class UserAccountController extends BaseController {
	@Autowired
	UserAccountService userAccountService;
	@Autowired
	UserAccountChangeHistoryService userAccountChangeHistoryService;
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("menu", Constant.MENU_USER_ACCOUNT);
		return "userAccount/userAccount";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		//获取查询条件过来的参数
		String username = request.getParameter("username");
		String phone = request.getParameter("phone");
		String openId = request.getParameter("openId");
		String iccid = request.getParameter("iccid");
		if (StringUtils.isNotBlank(username)) {
			model.put("username", username);
		}
		if (StringUtils.isNotBlank(phone)) {
			model.put("phone", phone);
		}
		if (StringUtils.isNotBlank(openId)) {
			model.put("wxOpenid", openId);
		}
		if (StringUtils.isNotBlank(iccid)) {
			model.put("iccid", iccid);
		}
		pageProperties(request,response,model);
		int count = userAccountService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<UserAccountExt> data = userAccountService.getListByCondition(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	@RequestMapping("detailPage")
	public String detailPage(Integer id, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("userId",id);
		return "userAccount/userAccountDetails";
	}
	@RequestMapping("getUserAccountChangeList")
	public void getUserAccountChangeList( HttpServletRequest request, HttpServletResponse response)throws Exception {
		String userId=request.getParameter("userId");
		int count=0;
		List<UserAccountChangeHistory> data=new ArrayList<>();
		if(StringUtils.isNotBlank(userId)){
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("userId",Integer.parseInt(userId));
			data=userAccountChangeHistoryService.getListByMap(model);
			pageProperties(request,response,model);
			 count = userAccountChangeHistoryService.count(model);
		}
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
}
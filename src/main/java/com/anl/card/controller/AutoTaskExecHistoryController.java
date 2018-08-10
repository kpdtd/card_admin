package com.anl.card.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.AutoTaskDefinition;
import com.anl.card.persistence.po.AutoTaskExecHistory;
import com.anl.card.service.AutoTaskDefinitionService;
import com.anl.card.service.AutoTaskExecHistoryService;

/** 
 * 类名: AutoTaskExecHistoryController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/autoTaskExecHistory")
public class AutoTaskExecHistoryController extends BaseController {
	@Autowired
	AutoTaskExecHistoryService autoTaskExecHistoryService;
	@Autowired
	AutoTaskDefinitionService autoTaskDefinitionService;
	
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(Constant.MENU_STRING, Constant.MENU_AUTO_TASK_EXEC_HISTORY);
		List<AutoTaskDefinition> autoTaskList = autoTaskDefinitionService.getListByMap(new HashMap<>());
		request.setAttribute("autoTaskList",autoTaskList);
		return "autoTaskExecHistory/autoTaskExecHistory";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		String autoTaskId = request.getParameter("autoTaskId");
		String returnResult = request.getParameter("returnResult");
		if(StringUtils.isNotBlank(autoTaskId)){
			model.put("taskId",autoTaskId);
		}
		if(StringUtils.isNotBlank(returnResult)){
			model.put("returnResult",returnResult);
		}
		pageProperties(request, response, model);
		int count = autoTaskExecHistoryService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<AutoTaskExecHistory> data = autoTaskExecHistoryService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data, response);
	}
	
	/**
	 * 表进行了任务名称的冗余，不需要做连表查询了
	 */
//	@RequestMapping("getListByCondition")
//	@ResponseBody
//	public void getListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		Map<String,Object> model = new HashMap<String,Object>();
//		String autoTaskId = request.getParameter("autoTaskId");
//		if(StringUtils.isNotBlank(autoTaskId)){
//			model.put("taskId",autoTaskId);
//		}
//		pageProperties(request, response, model);
//		int count = autoTaskExecHistoryService.count(model);
//		recordsTotal = count;
//		// 分页显示上面查询出的数据结果
//		List<AutoTaskExecHistory> data = autoTaskExecHistoryService.getListByCondition(model);
//		recordsFiltered = recordsTotal;
//		recordsDisplay = data.size();
//		this.writerToClient(data);
//	}
}
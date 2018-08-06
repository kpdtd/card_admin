package com.anl.card.controller;

import java.util.*;

import com.anl.card.persistence.po.SelectGroup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.FlowPacketDefinition;
import com.anl.card.service.FlowPacketDefinitionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: FlowPacketDefinitionController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/flowPacketDefinition")
public class FlowPacketDefinitionController extends BaseController {
	@Autowired
	FlowPacketDefinitionService flowPacketDefinitionService;
	
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "flowPacketDefinition/flowPacketDefinition";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(request,response,model);
		int count = flowPacketDefinitionService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<FlowPacketDefinition> data = flowPacketDefinitionService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			FlowPacketDefinition flowPacketDefinition = flowPacketDefinitionService.getById(Integer.parseInt(id));
			request.setAttribute("flowPacketDefinition", flowPacketDefinition);
		}
		return "flowPacketDefinition/detail";
	}*/
	
	@RequestMapping("add")
	public String add(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			List<SelectGroup> stateList = dataDictionaryService.getValueListByKey("PUBLISH_STATE");
			request.setAttribute("stateList",stateList);
			List<SelectGroup> flowStartTime = dataDictionaryService.getValueListByKey("FLOW_START_TIME");
			request.setAttribute("flowStartTime",flowStartTime);
			if (Objects.isNull(id)) {
				return "flowPacketDefinition/flowPacketDefinitionAdd";
			}
			FlowPacketDefinition flowPacketDefinition = flowPacketDefinitionService.getById(id);
			request.setAttribute("flowPacketDefinition", flowPacketDefinition);
			return "flowPacketDefinition/flowPacketDefinitionAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "flowPacketDefinition/flowPacketDefinitionAdd";
		}
	}
	@RequestMapping("addFlowPacketDefinition")
	public void addFlowPacketDefinition(FlowPacketDefinition flowPacketDefinition,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			flowPacketDefinition.setCreator(getUsername());
			if (Objects.isNull(flowPacketDefinition.getId())) {
				flowPacketDefinition.setCreateTime(new Date());
				flowPacketDefinitionService.insert(flowPacketDefinition);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				flowPacketDefinitionService.update(flowPacketDefinition);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(FlowPacketDefinition flowPacketDefinition) throws Exception{
//		try {
//			flowPacketDefinitionService.update(flowPacketDefinition);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
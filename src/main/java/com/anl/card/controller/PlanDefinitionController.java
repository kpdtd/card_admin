package com.anl.card.controller;

import java.util.*;

import com.anl.card.persistence.po.SelectGroup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.PlanDefinition;
import com.anl.card.service.PlanDefinitionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: PlanDefinitionController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/planDefinition")
public class PlanDefinitionController extends BaseController {
	@Autowired
	PlanDefinitionService planDefinitionService;
	
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "planDefinition/planDefinition";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(request,response,model);
		int count = planDefinitionService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<PlanDefinition> data = planDefinitionService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			PlanDefinition planDefinition = planDefinitionService.getById(Integer.parseInt(id));
			request.setAttribute("planDefinition", planDefinition);
		}
		return "planDefinition/detail";
	}*/
	
	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response,Integer id) throws Exception{
		try {
			List<SelectGroup> stateList = dataDictionaryService.getValueListByKey("PUBLISH_STATE");
			request.setAttribute("stateList", stateList);

			List<SelectGroup> effectiveTimeList = dataDictionaryService.getValueListByKey("EFFCTIVE_TIME");//effective_time
			request.setAttribute("effectiveTimeList", effectiveTimeList);
			if (Objects.isNull(id)) {
				return "planDefinition/planDefinitionAdd";
			}
			PlanDefinition planDefinition = planDefinitionService.getById(id);
			request.setAttribute("planDefinition", planDefinition);
			return "planDefinition/planDefinitionAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "planDefinition/planDefinitionAdd";
		}
	}
	@RequestMapping("addPlanDefinition")
	public void addPlanDefinition(HttpServletRequest request, HttpServletResponse response,PlanDefinition planDefinition) throws Exception{
		try {
			if (Objects.isNull(planDefinition.getId())) {
				planDefinition.setType(1);//预留1
				planDefinition.setCreateTime(new Date());
				planDefinition.setUpdateTime(new Date());
				planDefinitionService.insert(planDefinition);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				planDefinition.setUpdateTime(new Date());
				planDefinitionService.update(planDefinition);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(PlanDefinition planDefinition) throws Exception{
//		try {
//			planDefinitionService.update(planDefinition);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
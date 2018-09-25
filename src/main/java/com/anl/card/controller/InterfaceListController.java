package com.anl.card.controller;

import java.util.*;

import com.anl.card.constant.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.InterfaceList;
import com.anl.card.service.InterfaceListService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: InterfaceListController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/interfaceList")
public class InterfaceListController extends BaseController {
	@Autowired
	InterfaceListService interfaceListService;
	
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request) throws Exception {
		request.setAttribute(Constant.MENU_STRING, Constant.MENU_INTERFACE_LIST);
		return "interfaceList/interfaceList";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(request,response,model);
		int count = interfaceListService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<InterfaceList> data = interfaceListService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			InterfaceList interfaceList = interfaceListService.getById(Integer.parseInt(id));
			request.setAttribute("interfaceList", interfaceList);
		}
		return "interfaceList/detail";
	}*/
	
	@RequestMapping("add")
	public String add(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			if (Objects.isNull(id)) {
				return "interfaceList/interfaceListAdd";
			}
			InterfaceList interfaceList = interfaceListService.getById(id);
			request.setAttribute("interfaceList", interfaceList);
			return "interfaceList/interfaceListAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "interfaceList/interfaceListAdd";
		}
	}
	@RequestMapping("addInterfaceList")
	public void addInterfaceList(InterfaceList interfaceList,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			if (Objects.isNull(interfaceList.getId())) {
				interfaceList.setCreateTime(new Date());
				interfaceListService.insert(interfaceList);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				interfaceListService.update(interfaceList);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(InterfaceList interfaceList) throws Exception{
//		try {
//			interfaceListService.update(interfaceList);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
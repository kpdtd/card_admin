package com.anl.card.controller;

import java.util.*;

import com.anl.card.constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.ChargeList;
import com.anl.card.service.ChargeListService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: ChargeListController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/chargeList")
public class ChargeListController extends BaseController {
	@Autowired
	ChargeListService chargeListService;
	
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request) throws Exception {
		request.setAttribute(Constant.MENU_STRING, Constant.MENU_CHARGELIST);
		return "chargeList/chargeList";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(request,response,model);
		int count = chargeListService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<ChargeList> data = chargeListService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			ChargeList chargeList = chargeListService.getById(Integer.parseInt(id));
			request.setAttribute("chargeList", chargeList);
		}
		return "chargeList/detail";
	}*/
	
	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response,Integer id) throws Exception{
		try {
			if (Objects.isNull(id)) {
				return "chargeList/chargeListAdd";
			}
			ChargeList chargeList = chargeListService.getById(id);
			request.setAttribute("chargeList", chargeList);
			return "chargeList/chargeListAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "chargeList/chargeListAdd";
		}
	}
	@RequestMapping("addChargeList")
	public void addChargeList(HttpServletRequest request, HttpServletResponse response,ChargeList chargeList) throws Exception{
		try {
			if (Objects.isNull(chargeList.getId())) {
				chargeList.setCreateTime(new Date());
				chargeList.setUpdateTime(new Date());
				chargeListService.insert(chargeList);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				chargeList.setUpdateTime(new Date());
				chargeListService.update(chargeList);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(ChargeList chargeList) throws Exception{
//		try {
//			chargeListService.update(chargeList);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
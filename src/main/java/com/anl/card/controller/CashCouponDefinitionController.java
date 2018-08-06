package com.anl.card.controller;

import java.util.*;

import com.anl.card.persistence.po.SelectGroup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.CashCouponDefinition;
import com.anl.card.service.CashCouponDefinitionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: CashCouponDefinitionController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/cashCouponDefinition")
public class CashCouponDefinitionController extends BaseController {
	@Autowired
	CashCouponDefinitionService cashCouponDefinitionService;
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SelectGroup> ruleList = dataDictionaryService.getValueListByKey("CASH_COUPON_RULE");
		request.setAttribute("ruleList",ruleList);
		List<SelectGroup> typeList = dataDictionaryService.getValueListByKey("CASH_COUPON_TYPE");
		request.setAttribute("typeList",typeList);
		return "cashCouponDefinition/cashCouponDefinition";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(request,response,model);
		int count = cashCouponDefinitionService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<CashCouponDefinition> data = cashCouponDefinitionService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			CashCouponDefinition cashCouponDefinition = cashCouponDefinitionService.getById(Integer.parseInt(id));
			request.setAttribute("cashCouponDefinition", cashCouponDefinition);
		}
		return "cashCouponDefinition/detail";
	}*/
	
	@RequestMapping("add")
	public String add(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<SelectGroup> ruleList = dataDictionaryService.getValueListByKey("CASH_COUPON_RULE");
		request.setAttribute("ruleList",ruleList);
		List<SelectGroup> typeList = dataDictionaryService.getValueListByKey("CASH_COUPON_TYPE");
		request.setAttribute("typeList",typeList);
		try {
			if (Objects.isNull(id)) {
				return "cashCouponDefinition/cashCouponDefinitionAdd";
			}
			CashCouponDefinition cashCouponDefinition = cashCouponDefinitionService.getById(id);
			request.setAttribute("cashCouponDefinition", cashCouponDefinition);
			return "cashCouponDefinition/cashCouponDefinitionAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "cashCouponDefinition/cashCouponDefinitionAdd";
		}
	}
	@RequestMapping("addCashCouponDefinition")
	public void addCashCouponDefinition(CashCouponDefinition cashCouponDefinition,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			if (Objects.isNull(cashCouponDefinition.getId())) {
				cashCouponDefinition.setCreateTime(new Date());
				cashCouponDefinition.setUpdateTime(new Date());
				cashCouponDefinitionService.insert(cashCouponDefinition);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				cashCouponDefinition.setUpdateTime(new Date());
				cashCouponDefinitionService.update(cashCouponDefinition);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}

}
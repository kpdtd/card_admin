package com.anl.card.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.anl.card.persistence.po.SelectGroup;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.CardOwner;
import com.anl.card.service.CardOwnerService;

/** 
 * 类名: CardOwnerController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/cardOwner")
public class CardOwnerController extends BaseController {
	@Autowired
	CardOwnerService cardOwnerService;
	
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SelectGroup> stateList = dataDictionaryService.getValueListByKey("PUBLISH_STATE");
		request.setAttribute("stateList",stateList);
		return "cardOwner/cardOwner";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		String state=request.getParameter("state");
		String company=request.getParameter("company");
		if(StringUtils.isNotBlank(state)){
			model.put("state",state);
		}
		if(StringUtils.isNotBlank(company)){
			model.put("company",company);
		}
		pageProperties(request, response, model);
		int count = cardOwnerService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<CardOwner> data = cardOwnerService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data, response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			CardOwner cardOwner = cardOwnerService.getById(Integer.parseInt(id));
			request.setAttribute("cardOwner", cardOwner);
		}
		return "cardOwner/detail";
	}*/
	
	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse respons, Integer id) throws Exception{
		try {
			if (id!=null) {
			CardOwner cardOwner = cardOwnerService.getById(id);
			request.setAttribute("cardOwner", cardOwner);
			}
			List<SelectGroup> cardOwnerTypeList = dataDictionaryService.getValueListByKey("CARDOWNER_TYPE");
			request.setAttribute("cardOwnerTypeList", cardOwnerTypeList);
			List<SelectGroup> stateList = dataDictionaryService.getValueListByKey("PUBLISH_STATE");
			request.setAttribute("stateList", stateList);
			return "cardOwner/cardOwnerAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "cardOwner/cardOwnerAdd";
		}
	}
	@RequestMapping("addCardOwner")
	public void addCardOwner(HttpServletRequest request, HttpServletResponse response, CardOwner cardOwner) throws Exception{
		try {
			cardOwner.setCreator(getUsername());
			if (Objects.isNull(cardOwner.getId())) {
				cardOwner.setCreateTime(new Date());
				cardOwnerService.insert(cardOwner);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				cardOwnerService.update(cardOwner);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(CardOwner cardOwner) throws Exception{
//		try {
//			cardOwnerService.update(cardOwner);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
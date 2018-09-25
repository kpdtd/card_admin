package com.anl.card.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.Card;
import com.anl.card.persistence.po.CardOwner;
import com.anl.card.persistence.po.SelectGroup;
import com.anl.card.persistence.po.Supplier;
import com.anl.card.persistence.po.SupplierPool;
import com.anl.card.service.CardOwnerService;
import com.anl.card.service.CardService;
import com.anl.card.service.DataDictionaryService;
import com.anl.card.service.SupplierPoolService;
import com.anl.card.service.SupplierService;
import com.anl.card.util.ExcelHelper;
import com.anl.card.util.FileUtils;
import com.anl.card.vo.CardExt;

/**
 * 类名: CardController
 * 创建日期:
 * 功能描述:
 */
@Controller
@RequestMapping("/card")
public class CardController extends BaseController {
	@Autowired
	CardService cardService;
	
	@Autowired
	SupplierService supplierService;

	@Autowired
	CardOwnerService cardOwnerService;

	@Autowired
	SupplierPoolService supplierPoolService;

	@Autowired
	DataDictionaryService dataDictionaryService;

	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request) throws Exception {
		request.setAttribute(Constant.MENU_STRING, Constant.MENU_CARD);
		// 获取运营商列表
		List<Supplier> supplierList = supplierService.getListByMap(new HashMap<String, Object>());
		request.setAttribute("supplierList", supplierList);
		// 获取归属方列表
		List<CardOwner> cardOwnerList = cardOwnerService.getListByMap(new HashMap<String, Object>());
		request.setAttribute("cardOwnerList", cardOwnerList);
		// 获取套餐列表
		List<SupplierPool> supplierPoolList = supplierPoolService.getListByMap(new HashMap<String, Object>());
		request.setAttribute("supplierPoolList", supplierPoolList);
		// 获取卡状态列表
		List<SelectGroup> cardStateList = dataDictionaryService.getValueListByKey("CARD_STATE_LIST");
		request.setAttribute("cardStateList", cardStateList);
		return "card/card";
	}

	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		pageProperties(request, response, model);
		int count = cardService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<Card> data = cardService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data, response);
	}

	/**
	 * 根据条件查询列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getListByCondition")
	public void getListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String iccid = request.getParameter("iccid");
		String supplierId = request.getParameter("supplierId");
		String cardOwnerId = request.getParameter("cardOwnerId");
		String cardState = request.getParameter("cardState");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Map<String, Object> model = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(iccid)) {
			if(iccid.length() == 20){
				model.put("iccid", iccid);
			}else if(iccid.length() == 15 && iccid.startsWith("46")){
				model.put("imsi", iccid);//46开头的说明是imsi
			}else if(iccid.length() == 11 || iccid.length() == 13){
				model.put("msisdn", iccid);//46开头的说明是msisdn
			}
		}
		if(StringUtils.isNotBlank(supplierId)) {
			model.put("supplierId", supplierId);
		}
		if(StringUtils.isNotBlank(cardOwnerId)) {
			model.put("cardOwnerId", cardOwnerId);
		}
		if(StringUtils.isNotBlank(cardState)) {
			model.put("cardState", cardState);
		}
		if(StringUtils.isNotBlank(startTime)) {
			model.put("startTime", startTime);
		}
		if(StringUtils.isNotBlank(endTime)) {
			model.put("endTime", endTime);
		}
		pageProperties(request, response, model);
		int count = cardService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<CardExt> data = cardService.getListByCondition(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data, response);
	}
	
	/**
	 * 下载查询的结果集
	 */
	@RequestMapping(value = "/exportResult", method = RequestMethod.GET)
	public void exportResult(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String supplierId = request.getParameter("supplierId");
		String cardOwnerId = request.getParameter("cardOwnerId");
		String cardState = request.getParameter("cardState");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Map<String,Object> model = new HashMap<>();
		if(StringUtils.isNotBlank(supplierId)) {
			model.put("supplierId", supplierId);
		}
		if(StringUtils.isNotBlank(cardOwnerId)) {
			model.put("cardOwnerId", cardOwnerId);
		}
		if(StringUtils.isNotBlank(cardState)) {
			model.put("cardState", cardState);
		}
		if(StringUtils.isNotBlank(startTime)) {
			model.put("startTime", startTime);
		}
		if(StringUtils.isNotBlank(endTime)) {
			model.put("endTime", endTime);
		}
		List<Card> listByMap = cardService.getListByMap(model);
		ExcelHelper eh = new ExcelHelper();
		eh.getxlsxByList(request, response, listByMap);//导出查询卡列表
	}
	
	/**
	 * 导出select框选中的项，暂时不在页面提供此功能
	 */
	@RequestMapping(value = "/exportSelect",method = RequestMethod.GET)
	public void exportSelect(HttpServletRequest request, HttpServletResponse response, Integer[] checkboxes_choose) throws Exception {
		if(checkboxes_choose != null ) {
			List<Card> dataList = new ArrayList<Card>();
			for(Integer id : checkboxes_choose) {
				Card card = cardService.getById(id);
				dataList.add(card);
			}
			ExcelHelper eh = new ExcelHelper();
			eh.getxlsxByList(null, null, dataList);
		}
	}
//
//	@RequestMapping("add")
//	public String add(HttpServletRequest request, HttpServletResponse response, Integer id) throws Exception {
//		try {
//			if(Objects.isNull(id)) {
//				return "card/cardAdd";
//			}
//			Card card = cardService.getById(id);
//			request.setAttribute("card", card);
//			return "card/cardAdd";
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return "card/cardAdd";
//		}
//	}
//
//	@RequestMapping("addCard")
//	public void addCard(HttpServletRequest request, HttpServletResponse response, Card card) throws Exception {
//		try {
//			if(Objects.isNull(card.getId())) {
//				cardService.insert(card);
//				setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//			}
//			else {
//				cardService.update(card);
//				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//			}
//
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			setJsonFail(response, null, 1100, "操作失败！");
//		}
//	}


}
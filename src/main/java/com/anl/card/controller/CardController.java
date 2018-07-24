package com.anl.card.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.Card;
import com.anl.card.service.CardService;

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
	
	@RequestMapping("getPage")
	public String getPage() throws Exception {
		return "card/card";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(request, response, model);
		int count = cardService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<Card> data = cardService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data, response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			Card card = cardService.getById(Integer.parseInt(id));
			request.setAttribute("card", card);
		}
		return "card/detail";
	}*/
	
	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response, Integer id) throws Exception{
		try {
			if (Objects.isNull(id)) {
				return "card/cardAdd";
			}
			Card card = cardService.getById(id);
			request.setAttribute("card", card);
			return "card/cardAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "card/cardAdd";
		}
	}
	@RequestMapping("addCard")
	public void addCard(HttpServletRequest request, HttpServletResponse response, Card card) throws Exception{
		try {
			if (Objects.isNull(card.getId())) {
				cardService.insert(card);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				cardService.update(card);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(Card card) throws Exception{
//		try {
//			cardService.update(card);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
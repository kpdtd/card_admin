package com.anl.card.controller;

import java.util.*;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.SelectGroup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.GoodsActivity;
import com.anl.card.service.GoodsActivityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: GoodsActivityController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/goodsActivity")
public class GoodsActivityController extends BaseController {
	@Autowired
	GoodsActivityService goodsActivityService;
	
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(Constant.MENU_STRING, Constant.MENU_GOOD_ACTIVITY);
		return "goodsActivity/goodsActivity";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(request,response,model);
		int count = goodsActivityService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<GoodsActivity> data = goodsActivityService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			GoodsActivity goodsActivity = goodsActivityService.getById(Integer.parseInt(id));
			request.setAttribute("goodsActivity", goodsActivity);
		}
		return "goodsActivity/detail";
	}*/
	
	@RequestMapping("add")
	public String add(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			List<SelectGroup> stateList = dataDictionaryService.getValueListByKey("PUBLISH_STATE");
			request.setAttribute("stateList",stateList);
			List<SelectGroup> providerType = dataDictionaryService.getValueListByKey("PROVIDER_TYPE");
			request.setAttribute("providerType",providerType);
			List<SelectGroup> activityType = dataDictionaryService.getValueListByKey("ACTIVITY_TYPE");
			request.setAttribute("activityType",activityType);
			if (Objects.isNull(id)) {
				return "goodsActivity/goodsActivityAdd";
			}
			GoodsActivity goodsActivity = goodsActivityService.getById(id);
			request.setAttribute("goodsActivity", goodsActivity);
			return "goodsActivity/goodsActivityAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "goodsActivity/goodsActivityAdd";
		}
	}
	@RequestMapping("addGoodsActivity")
	public void addGoodsActivity(GoodsActivity goodsActivity,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			goodsActivity.setUpdateTime(new Date());
			if (Objects.isNull(goodsActivity.getId())) {
				goodsActivity.setCreateTime(new Date());
				goodsActivityService.insert(goodsActivity);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				goodsActivityService.update(goodsActivity);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(GoodsActivity goodsActivity) throws Exception{
//		try {
//			goodsActivityService.update(goodsActivity);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
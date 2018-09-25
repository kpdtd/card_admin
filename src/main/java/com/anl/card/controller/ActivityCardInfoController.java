package com.anl.card.controller;

import java.util.*;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.SelectGroup;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.ActivityCardInfo;
import com.anl.card.service.ActivityCardInfoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: ActivityCardInfoController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/activityCardInfo")
public class ActivityCardInfoController extends BaseController {
	@Autowired
	ActivityCardInfoService activityCardInfoService;
	
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(Constant.MENU_STRING, Constant.MENU_ACTIVITY_CARDINFO);
		return "activityCardInfo/activityCardInfo";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(request,response,model);
		int count = activityCardInfoService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<ActivityCardInfo> data = activityCardInfoService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			ActivityCardInfo activityCardInfo = activityCardInfoService.getById(Integer.parseInt(id));
			request.setAttribute("activityCardInfo", activityCardInfo);
		}
		return "activityCardInfo/detail";
	}*/
	
	@RequestMapping("add")
	public String add(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			List<SelectGroup> cardOrderStateList = dataDictionaryService.getValueListByKey("CARD_ORDER_STATE");//卡订单状态
			request.setAttribute("cardOrderStateList", cardOrderStateList);
			List<SelectGroup> cardStateList = dataDictionaryService.getValueListByKey("CARD_STATE_LIST");//卡状态
			request.setAttribute("cardStateList", cardStateList);

			if (Objects.isNull(id)) {
				return "activityCardInfo/activityCardInfoAdd";
			}
			ActivityCardInfo activityCardInfo = activityCardInfoService.getById(id);
			request.setAttribute("activityCardInfo", activityCardInfo);
			return "activityCardInfo/activityCardInfoAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "activityCardInfo/activityCardInfoAdd";
		}
	}
	@RequestMapping("addActivityCardInfo")
	public void addActivityCardInfo(ActivityCardInfo activityCardInfo,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			activityCardInfo.setUpdateTime(new Date());
			if (Objects.isNull(activityCardInfo.getId())) {
				activityCardInfo.setCreateTime(new Date());
				activityCardInfoService.insert(activityCardInfo);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				activityCardInfoService.update(activityCardInfo);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(ActivityCardInfo activityCardInfo) throws Exception{
//		try {
//			activityCardInfoService.update(activityCardInfo);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
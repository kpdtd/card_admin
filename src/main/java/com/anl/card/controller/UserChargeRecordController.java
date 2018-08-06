package com.anl.card.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.UserChargeRecord;
import com.anl.card.service.UserChargeRecordService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: UserChargeRecordController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/userChargeRecord")
public class UserChargeRecordController extends BaseController {
	@Autowired
	UserChargeRecordService userChargeRecordService;
	
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "userChargeRecord/userChargeRecord";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		//获取查询条件过来的参数
		String outTradeNo = request.getParameter("outTradeNo");
		String tradeNo = request.getParameter("tradeNo");
		String phone = request.getParameter("phone");
		String openId = request.getParameter("openId");
		String iccid = request.getParameter("iccid");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if (StringUtils.isNotBlank(outTradeNo)) {
			model.put("outTradeNo", outTradeNo);
		}
		if (StringUtils.isNotBlank(tradeNo)) {
			model.put("tradeNo", tradeNo);
		}
		if (StringUtils.isNotBlank(phone)) {
			model.put("phone", phone);
		}
		if (StringUtils.isNotBlank(openId)) {
			model.put("openId", openId);
		}
		if (StringUtils.isNotBlank(iccid)) {
			model.put("iccid", iccid);
		}
		if (StringUtils.isNotBlank(startTime)) {
			model.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			model.put("endTime", endTime);
		}
		pageProperties(request,response,model);
		int count = userChargeRecordService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<UserChargeRecord> data = userChargeRecordService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			UserChargeRecord userChargeRecord = userChargeRecordService.getById(Integer.parseInt(id));
			request.setAttribute("userChargeRecord", userChargeRecord);
		}
		return "userChargeRecord/detail";
	}*/
	
	@RequestMapping("add")
	public String add(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			if (Objects.isNull(id)) {
				return "userChargeRecord/userChargeRecordAdd";
			}
			UserChargeRecord userChargeRecord = userChargeRecordService.getById(id);
			request.setAttribute("userChargeRecord", userChargeRecord);
			return "userChargeRecord/userChargeRecordAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "userChargeRecord/userChargeRecordAdd";
		}
	}
	@RequestMapping("addUserChargeRecord")
	public void addUserChargeRecord(UserChargeRecord userChargeRecord,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			if (Objects.isNull(userChargeRecord.getId())) {
				userChargeRecordService.insert(userChargeRecord);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				userChargeRecordService.update(userChargeRecord);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(UserChargeRecord userChargeRecord) throws Exception{
//		try {
//			userChargeRecordService.update(userChargeRecord);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
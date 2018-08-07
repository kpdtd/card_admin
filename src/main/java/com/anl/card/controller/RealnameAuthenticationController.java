package com.anl.card.controller;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.RealnameAuthentication;
import com.anl.card.service.RealnameAuthenticationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: RealnameAuthenticationController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/realnameAuthentication")
public class RealnameAuthenticationController extends BaseController {
	@Autowired
	RealnameAuthenticationService realnameAuthenticationService;
	
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "realnameAuthentication/realnameAuthentication";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		String phone = request.getParameter("phone");
		String iccid = request.getParameter("iccid");
		if (StringUtils.isNotBlank(iccid)) {
			model.put("iccid", iccid);
		}
		if (StringUtils.isNotBlank(phone)) {
			model.put("phone", phone);
		}
		pageProperties(request,response,model);
		int count = realnameAuthenticationService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<RealnameAuthentication> data = realnameAuthenticationService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			RealnameAuthentication realnameAuthentication = realnameAuthenticationService.getById(Integer.parseInt(id));
			request.setAttribute("realnameAuthentication", realnameAuthentication);
		}
		return "realnameAuthentication/detail";
	}*/
	
	@RequestMapping("add")
	public String add(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			if (Objects.isNull(id)) {
				return "realnameAuthentication/realnameAuthenticationAdd";
			}
			RealnameAuthentication realnameAuthentication = realnameAuthenticationService.getById(id);
			request.setAttribute("realnameAuthentication", realnameAuthentication);
			return "realnameAuthentication/realnameAuthenticationAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "realnameAuthentication/realnameAuthenticationAdd";
		}
	}
	@RequestMapping("addRealnameAuthentication")
	public void addRealnameAuthentication(RealnameAuthentication realnameAuthentication,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			if (Objects.isNull(realnameAuthentication.getId())) {
				realnameAuthenticationService.insert(realnameAuthentication);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				realnameAuthentication.setUpdateTime(new Date());
				realnameAuthenticationService.update(realnameAuthentication);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(RealnameAuthentication realnameAuthentication) throws Exception{
//		try {
//			realnameAuthenticationService.update(realnameAuthentication);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
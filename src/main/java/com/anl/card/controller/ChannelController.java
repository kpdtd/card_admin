package com.anl.card.controller;

import java.util.*;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.SelectGroup;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.Channel;
import com.anl.card.service.ChannelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: ChannelController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/channel")
public class ChannelController extends BaseController {
	@Autowired
	ChannelService channelService;
	
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(Constant.MENU_STRING, Constant.MENU_CHANNEL);
		return "channel/channel";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(request,response,model);
		int count = channelService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<Channel> data = channelService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			Channel channel = channelService.getById(Integer.parseInt(id));
			request.setAttribute("channel", channel);
		}
		return "channel/detail";
	}*/
	
	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response,Integer id) throws Exception{
		try {
			List<SelectGroup> categoryList = dataDictionaryService.getValueListByKey("CHANNEL_CATEGORY");
			request.setAttribute("categoryList", categoryList);
			List<SelectGroup> stateList = dataDictionaryService.getValueListByKey("PUBLISH_STATE");
			request.setAttribute("stateList", stateList);
			if (Objects.isNull(id)) {
				return "channel/channelAdd";
			}
			Channel channel = channelService.getById(id);
			request.setAttribute("channel", channel);
			return "channel/channelAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "channel/channelAdd";
		}
	}
	@RequestMapping("addChannel")
	public void addChannel(HttpServletRequest request, HttpServletResponse response,Channel channel) throws Exception{
		try {
			if (Objects.isNull(channel.getId())) {
				channel.setCreateTime(new Date());
				channel.setUpdateTime(new Date());
				channelService.insert(channel);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				channel.setUpdateTime(new Date());
				channelService.update(channel);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(Channel channel) throws Exception{
//		try {
//			channelService.update(channel);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
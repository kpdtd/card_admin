package com.anl.card.controller;

import com.anl.card.constant.Constant;
import com.anl.card.service.UserFlowUsedDayService;
import com.anl.card.vo.UserFlowUsedDayExt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名: 日使用量
 * 创建日期:
 * 功能描述:
 */
@Controller
@RequestMapping("/userFlowUsedDay")
public class UserFlowUsedDayController extends BaseController {
    @Autowired
    UserFlowUsedDayService userFlowUsedDayService;

    @RequestMapping("getPage")
    public String getPage(HttpServletRequest request) throws Exception {
        request.setAttribute(Constant.MENU_STRING, Constant.MENU_USER_FLOW_USED_DAY);
        return "userFlowUsedDay/userFlowUsedDay";
    }

    @RequestMapping("getList")
    @ResponseBody
    public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String iccid = request.getParameter("iccid");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");

        Map<String, Object> model = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(iccid)) {
            model.put("iccid", iccid);
        }
        if (StringUtils.isNotBlank(startTime)) {
            model.put("startTime", startTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            model.put("endTime", endTime);
        }
        pageProperties(request, response, model);
        int count = userFlowUsedDayService.getExtListCount(model);
        recordsTotal = count;
        // 分页显示上面查询出的数据结果
        List<UserFlowUsedDayExt> data = userFlowUsedDayService.getExtListByMap(model);
        recordsFiltered = recordsTotal;
        recordsDisplay = data.size();
        this.writerToClient(data, response);
    }
}
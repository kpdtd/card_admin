package com.anl.card.controller;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.*;
import com.anl.card.service.*;
import com.anl.card.vo.UserExt;

import org.apache.commons.collections.CollectionUtils;
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
 * 类名: UserController
 * 创建日期:
 * 功能描述:
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    UserService userService;
    @Autowired
    ChannelService channelService;
    @Autowired
    CardService cardService;
    @Autowired
    CardOwnerService cardOwnerService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    UserPlanService userPlanService;
    @Autowired
    PlanDefinitionService planDefinitionService;
    @Autowired
    UserAccountService userAccountService;

    @RequestMapping("getPage")
    public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	request.setAttribute("menu", Constant.MENU_USER);
        //准备查询条件,渠道list
        List<Channel> channelList = channelService.getListByMap(new HashMap<>());
        request.setAttribute("channelList", channelList);
        return "user/user";
    }

    @RequestMapping("getList")
    @ResponseBody
    public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //准备查询条件,渠道list
        List<Channel> channelList = channelService.getListByMap(new HashMap<>());
        request.setAttribute("channelList", channelList);

        //获取查询条件过来的参数
        String channelId = request.getParameter("channelId");
        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String openId = request.getParameter("openId");
        String iccid = request.getParameter("iccid");
        Map<String, Object> model = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(channelId)) {
            model.put("channelId", Integer.parseInt(channelId));
        }
        if (StringUtils.isNotBlank(username)) {
            model.put("username", username);
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
        pageProperties(request, response, model);
        int count = userService.count(model);
        recordsTotal = count;
        // 分页显示上面查询出的数据结果
        List<UserExt> data = userService.getListByCondition(model);
        recordsFiltered = recordsTotal;
        recordsDisplay = data.size();
        this.writerToClient(data, response);
    }


    /**
     * 用户详情:用户基本信息,套餐信息(更改记录),流量卡信息(使用情况)
     *
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("detailPage")
    public String detailPage(Integer id, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (id != null && id > 0) {
                //用户基本信息
                Map<String, Object> model = new HashMap<>();
                model.put("id", id);
                List<UserExt> data = userService.getListByCondition(model);
                request.setAttribute("user", data.get(0));
                //用户关联的卡信息
                Card card = cardService.getById(data.get(0).getCardId());
                request.setAttribute("card", card);
                //用户套餐
                Map<String, Object> userPlanMap = new HashMap<>();
                userPlanMap.put("userId", data.get(0).getId());
                List<UserPlan> userPlanList = userPlanService.getListByMap(userPlanMap);
                if (CollectionUtils.isNotEmpty(userPlanList)) {
                    request.setAttribute("userPlan", userPlanList.get(0));
                    //套餐定义信息
                    PlanDefinition using = planDefinitionService.getById(userPlanList.get(0).getPlanId());//正使用
                    PlanDefinition next = planDefinitionService.getById(userPlanList.get(0).getNewPlanId());//下个月生效
                    request.setAttribute("using", using);
                    request.setAttribute("next", next);
                }
                //运营商
                Supplier supplier = supplierService.getById(card.getSupplierId());
                request.setAttribute("supplier", supplier);
                //归属方
                CardOwner cardOwner = cardOwnerService.getById(card.getCardOwnerId());
                request.setAttribute("cardOwner", cardOwner);
                //流量使用情况
                //账户使用情况
                Map<String,Object> uaMap=new HashMap<>();
                uaMap.put("userId",data.get(0).getId());
                List<UserAccount> userAccountList=userAccountService.getListByMap(uaMap);
                if(CollectionUtils.isNotEmpty(userAccountList)){
                    request.setAttribute("userAccount",userAccountList.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user/userDetails";
    }
}
package com.anl.card.controller;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.AutoTaskDefinition;
import com.anl.card.service.AutoTaskDefinitionService;

/** 
 * 类名: AutoTaskDefinitionController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/autoTaskDefinition")
public class AutoTaskDefinitionController extends BaseController {
	@Autowired
	AutoTaskDefinitionService autoTaskDefinitionService;
//	@Autowired
//	private SchedulerFactoryBean schedulerFactoryBean;

	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(Constant.MENU_STRING, Constant.MENU_AUTO_TASK_DEFINITION);
		return "autoTaskDefinition/autoTaskDefinition";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setAttribute("menu", Constant.MENU_AUTO_TASK_DEFINITION);
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(request, response, model);
		int count = autoTaskDefinitionService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<AutoTaskDefinition> data = autoTaskDefinitionService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data, response);
	}

	@RequestMapping("add")
	public String add(HttpServletRequest request, HttpServletResponse response, Integer id) throws Exception{
		try {
			if (Objects.isNull(id)) {
				return "autoTaskDefinition/autoTaskDefinitionAdd";//如果id为空，说明是新增
			}
			AutoTaskDefinition autoTaskDefinition = autoTaskDefinitionService.getById(id);
			request.setAttribute("autoTaskDefinition", autoTaskDefinition);
			return "autoTaskDefinition/autoTaskDefinitionAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
	@RequestMapping("addAutoTaskDefinition")
	public void addAutoTaskDefinition(HttpServletRequest request, HttpServletResponse response, AutoTaskDefinition autoTaskDefinition) throws Exception{
		try {
			if (Objects.isNull(autoTaskDefinition.getId())) {
				autoTaskDefinition.setCreateTime(new Date());
				autoTaskDefinition.setUpdateTime(new Date());
				autoTaskDefinition.setExecuteState(1);
				autoTaskDefinition.setState(0);
				autoTaskDefinitionService.insert(autoTaskDefinition);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				autoTaskDefinitionService.update(autoTaskDefinition);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}

	@RequestMapping("updateState")
	public void updateState(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String state = request.getParameter("state");
		AutoTaskDefinition autoTaskDefinition = null;
		if(StringUtils.isNotBlank(id)){
			autoTaskDefinition = autoTaskDefinitionService.getById(Integer.parseInt(id));
		}
		if(StringUtils.isNotBlank(state)){
			autoTaskDefinition.setState(Integer.parseInt(state));
			if(Integer.parseInt(state)==0){
				upatePauseTask(autoTaskDefinition);
			}else if(Integer.parseInt(state)==1){
				resumeJob(autoTaskDefinition);
			}
		}
		autoTaskDefinitionService.update(autoTaskDefinition);
		setJsonSuccess(response, null, "状态更改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
	}


	/**
	 * 暂停一个任务
	 *
	 * @throws SQLException
	 */
	public void upatePauseTask(AutoTaskDefinition autoTaskDefinition) throws SchedulerException, SQLException {
//		autoTaskDefinition.setState(0);
//		autoTaskDefinitionService.update(autoTaskDefinition);
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		JobKey jobKey = JobKey.jobKey(autoTaskDefinition.getTaskExecLogic());
//		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个task
	 *
	 * @throws SQLException
	 */

	public void resumeJob(AutoTaskDefinition autoTaskDefinition) throws SchedulerException, SQLException {
//
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//		// Trigger已存在，那么更新相应的定时设置
//		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(autoTaskDefinition.getTaskTimeCron());
//		TriggerKey triggerKey = TriggerKey.triggerKey(autoTaskDefinition.getTaskExecLogic());
//		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//		if(trigger==null){
//			// 根据任务的执行状态判断该任务应该走哪个JOB
//			Class<? extends Job> clazz = QuartzTaskFactory.class;
//			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(autoTaskDefinition.getTaskExecLogic()).build();
//			jobDetail.getJobDataMap().put("autoTaskDefinition", autoTaskDefinition);
//			// 按新的cronExpression表达式重新构建trigger
//			trigger = TriggerBuilder.newTrigger().withIdentity(autoTaskDefinition.getTaskExecLogic()).withSchedule(scheduleBuilder)
//					.build();
//			scheduler.scheduleJob(jobDetail, trigger);
//		}else {
//			// 按新的trigger重新设置job执行
//			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
//			scheduler.rescheduleJob(triggerKey, trigger);
//		}
//		autoTaskDefinition.setState(1);
//		autoTaskDefinitionService.update(autoTaskDefinition);
	}
}
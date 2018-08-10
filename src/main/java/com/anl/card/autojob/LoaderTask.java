//package com.anl.card.autojob;
//
//import com.csxm.iot.po.AutoTaskDefinition;
//import com.csxm.iot.service.AutoTaskDefinitionService;
//import com.csxm.iot.util.LogFactory;
//import org.quartz.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//import org.springframework.stereotype.Service;
//
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class LoaderTask implements ApplicationListener<ContextRefreshedEvent> {
//
//	private static final Integer TASK_STATE_RUNNING = 1; // 任务是否正在执行
//
//	@Autowired
//	private SchedulerFactoryBean schedulerFactoryBean;
//	@Autowired
//	private AutoTaskDefinitionService autoTaskDefinitionService;
//
//	public void initTask() throws Exception {
//
//		LogFactory.getInstance().getSysInfoLogger().info("System Load quartz task.");
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		dataMap.put("state",1);
//		// 可执行的任务列表
//		List<AutoTaskDefinition> taskList = autoTaskDefinitionService.getListByMap(dataMap);
//		for (AutoTaskDefinition task : taskList) {
//			LogFactory.getInstance().getSysInfoLogger().info("Load task 【" + task.getTaskName() + "】");
//			addTask(task);
//		}
//	}
//
//	private void addTask(AutoTaskDefinition task) throws SchedulerException, SQLException {
//		Scheduler scheduler = schedulerFactoryBean.getScheduler();
//
//		TriggerKey triggerKey = TriggerKey.triggerKey(task.getTaskExecLogic());
//		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
//
//		if (trigger == null) {
//			// 根据任务的执行状态判断该任务应该走哪个JOB
//			Class<? extends Job> clazz = QuartzTaskFactory.class;
//			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(task.getTaskExecLogic()).build();
//			jobDetail.getJobDataMap().put("autoTaskDefinition", task);
//
//			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getTaskTimeCron());
//			trigger = TriggerBuilder.newTrigger().withIdentity(task.getTaskExecLogic()).withSchedule(scheduleBuilder)
//					.build();
//			scheduler.scheduleJob(jobDetail, trigger);
//			task.setCreateTime(new Date());
//			task.setExecuteState(TASK_STATE_RUNNING);
//			autoTaskDefinitionService.update(task);
//		} else {
//			// Trigger已存在，那么更新相应的定时设置
//			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(task.getTaskTimeCron());
//
//			// 按新的cronExpression表达式重新构建trigger
//			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
//
//			// 按新的trigger重新设置job执行
//			scheduler.rescheduleJob(triggerKey, trigger);
//		}
//	}
//
//	@Override
//	public void onApplicationEvent(ContextRefreshedEvent event) {
//		try {
//			// 防止onApplicationEvent方法被执行两次
//			if (event.getApplicationContext().getParent() == null) {
//				TaskManager.applicationContext = event.getApplicationContext();
//				initTask();
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
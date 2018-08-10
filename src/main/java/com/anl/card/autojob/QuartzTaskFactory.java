//package com.anl.card.autojob;
//
//import com.csxm.iot.po.AutoTaskDefinition;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//
//public class QuartzTaskFactory extends TaskManager implements Job {
//
//    @Override
//    public void execute(JobExecutionContext context) throws JobExecutionException {
//        AutoTaskDefinition autoTaskDefinition = (AutoTaskDefinition) context.getMergedJobDataMap().get("autoTaskDefinition");
//        invokMethod(autoTaskDefinition);
//    }
//
//}

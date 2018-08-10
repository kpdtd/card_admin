//package com.anl.card.autojob;
//
//import com.csxm.iot.po.AutoTaskDefinition;
//import org.springframework.context.ApplicationContext;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//
//public class TaskManager {
//
//	public static ApplicationContext applicationContext;
//
//	public void invokMethod(AutoTaskDefinition autoTaskDefinition) {
//		Class<?> clazz;
//		try {
//			Object object = applicationContext.getBean(autoTaskDefinition.getTaskExecLogic());
//
//			if (object == null) {
//				return;
//			}
//			clazz = object.getClass();
//			Method method = clazz.getMethod("start", AutoTaskDefinition.class);
//			if (method != null) {
//				method.invoke(object, autoTaskDefinition);
//			}
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchMethodException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalArgumentException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}

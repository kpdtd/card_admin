//package com.anl.card.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.anl.card.constant.Constant;
//
//@Controller
//@RequestMapping("/")
//public class IndexController extends BaseController {
//
//	@RequestMapping("index")
//	public String getPage() throws Exception {
//		request.setAttribute(Constant.MENU_STRING, Constant.MENU_INDEX);
//		System.out.println("#####@@@");
////		List<SelectGroup> mounthList = dataDictionaryService.getValueListByKey("MOUNTH_TYPE");
////		request.setAttribute("mounthList", mounthList);
////		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
////		String nowMounth = dateFormat.format(new Date());
////		int parseInt = Integer.parseInt(nowMounth);
////		request.setAttribute("nowMounth", nowMounth);
////		if(parseInt==1){
////			request.setAttribute("ComparedMounth", parseInt+1);
////		}else{
////			request.setAttribute("ComparedMounth", parseInt-1);
////		}
//		return Constant.MENU_INDEX;
//	}
//	
//	/**
//	 * 
//	 * 原来的目的：想让用户输入http://localhost:8080/***_admin/index.jsp的时候，能看到index.jsp页面
//	 * 发现，输入后404，无法打印出@@@@@@@@@ ，说明spring的serveltDespatch解析根本不认这个映射；
//	 * 源码无法加载，暂时不管，以后处理
//	 */
////	@RequestMapping("index.jsp")
////	public String getDefultPage() throws Exception {
////		System.out.println("@@@@@@@@@");
////		return getPage();
////	}
//
//	public void logout() {
//
//	}
//}

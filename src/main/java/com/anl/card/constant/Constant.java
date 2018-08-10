package com.anl.card.constant;

public class Constant {

	public static final String MENU_INDEX = "index";// 首页
	public static final String LOGIN_URL = "/login.jsp";
	public static final String UNAUTHORIZED = "/common/403.jsp";
	public static final String IMG_SAVE_PATH = "IMG_SAVE_PATH"; // 图片存放地址路径
	public static final String IMG_SAVE_URL = "D_IMG_HOST"; // 图片存放地址路径
	public static final String PUBLISH_STATE = "PUBLISH_STATE"; // 发布状态
	public static final String EXCEL_SAVE_PATH = "EXCEL_SAVE_PATH"; // 图片存放地址路径
	
	public static final String C_APPSTARTPAGE="C_APPSTARTPAGE";//APP启动页面配置
	public static final String C_FLOW_FIRST_PAGE="C_FLOW_FIRST_PAGE";//流量商城首页列表

	public static final String nbsp = "&nbsp;";

	// 数据字典配置常量信息
	public static final String PROVIDER_TYPE = "PROVIDER_TYPE"; // 供应商类型

	public static final String CASH_COUPON_RULE="CASH_COUPON_RULE";//优惠券规则定义

	 /*********菜单定义管理*************/
	public static final String MENU_STRING = "menu";// 控制左侧菜单显示效果
	public static final String MENU_CARD = "card";// 控制左侧菜单显示效果
	public static final String MENU_CARD_STOCK = "cardStock";//卡库存管理
	public static final String MENU_SUPPLIER = "supplier";//上游运营商--卡提供商
	public static final String MENU_SUPPLIER_POOL = "supplierPool";//卡套餐/流量池
	public static final String MENU_CARD_OWNER = "cardOwner";//卡归属方
	public static final String MENU_CHANNEL = "channel";//卡渠道管理
	public static final String MENU_WRITTEN_OFF = "cardWrittenOff";//卡注销管理
	public static final String MENU_CARD_BATCH_PROCESS = "cardBatchProcess";//卡注销管理
	public static final String MENU_USER = "user";//用户信息
	public static final String MENU_USER_ACCOUNT = "userAccount";//用户信息
	public static final String MENU_AUTO_TASK_DEFINITION = "autoTaskDefinition";//自动任务配置（定义）
	public static final String MENU_AUTO_TASK_EXEC_HISTORY = "autoTaskExecHistory";//自动任务执行结果历史
	public static final String MENU_DICTIONARY = "dictionary";
	
	/***********  批量动作定义  ***********/
	public static final String BATCH_CARD_STOCKIN = "新卡入库";
	public static final String BATCH_CARD_OWNER_CHANGE = "卡归属方变更";
	public static final String BATCH_CARD_POOL_CHANGE = "流量池变更";
	public static final String BATCH_CARD_WRITTEN_OFF = "卡销号";
}

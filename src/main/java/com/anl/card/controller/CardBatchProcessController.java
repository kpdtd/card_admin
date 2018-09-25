package com.anl.card.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.Card;
import com.anl.card.persistence.po.CardBatchProcess;
import com.anl.card.persistence.po.CardOwner;
import com.anl.card.persistence.po.CardOwnerChangeHistory;
import com.anl.card.persistence.po.CardPoolChangeHistory;
import com.anl.card.persistence.po.CardWrittenOff;
import com.anl.card.persistence.po.SelectGroup;
import com.anl.card.persistence.po.Supplier;
import com.anl.card.persistence.po.SupplierPool;
import com.anl.card.service.CardBatchProcessService;
import com.anl.card.service.CardOwnerChangeHistoryService;
import com.anl.card.service.CardOwnerService;
import com.anl.card.service.CardPoolChangeHistoryService;
import com.anl.card.service.CardService;
import com.anl.card.service.CardWrittenOffService;
import com.anl.card.service.DataDictionaryService;
import com.anl.card.service.SupplierPoolService;
import com.anl.card.service.SupplierService;
import com.anl.card.util.DateUtil;
import com.anl.card.util.ExcelHelper;
import com.anl.card.util.ExcelHelper.Entity;
import com.anl.card.util.FileUtils;

/**
 * 类名: CardBatchProcessController
 * 创建日期:
 * 功能描述:
 */
@Controller
@RequestMapping("/cardBatchProcess")
public class CardBatchProcessController extends BaseController {
	@Autowired
	CardService cardService;

	@Autowired
	CardBatchProcessService cardBatchProcessService;

	@Autowired
	SupplierService supplierService;

	@Autowired
	CardOwnerService cardOwnerService;

	@Autowired
	CardOwnerChangeHistoryService cardOwnerChangeHistoryService;
	
	@Autowired
	CardPoolChangeHistoryService cardPoolChangeHistoryService;

	@Autowired
	SupplierPoolService supplierPoolService;
	
	@Autowired
	CardWrittenOffService cardWrittenOffService;

	@Autowired
	DataDictionaryService dataDictionaryService;

	/**
	 * 获取批量管理的主页面，跳转功能
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(Constant.MENU_STRING, Constant.MENU_CARD_BATCH_PROCESS);
		return "cardBatchProcess/cardBatchProcess";
	}

	/**
	 * 在主页面中显示所有批量操作的列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		pageProperties(request, response, model);
		int count = cardBatchProcessService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<CardBatchProcess> data = cardBatchProcessService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data, response);
	}

	/******************************************************************
	 * 批量卡入库相关功能
	 *******************************************************************/

	/*
	 * 点击“批量入库”，加载批量入库页面
	 */
	@RequestMapping("getCardStockInPage")
	public String getCardStockInPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取运营商列表
		List<Supplier> supplierList = supplierService.getListByMap(new HashMap<String, Object>());
		request.setAttribute("supplierList", supplierList);
		// 获取归属方列表
		List<CardOwner> cardOwnerList = cardOwnerService.getListByMap(new HashMap<String, Object>());
		request.setAttribute("cardOwnerList", cardOwnerList);
		// 获取套餐列表
		List<SupplierPool> supplierPoolList = supplierPoolService.getListByMap(new HashMap<String, Object>());
		request.setAttribute("supplierPoolList", supplierPoolList);
		// 获取卡状态列表
		List<SelectGroup> cardStateList = dataDictionaryService.getValueListByKey("CARD_STATE_LIST");
		request.setAttribute("cardStateList", cardStateList);

		return "cardBatchProcess/cardBatchStockInAdd";
	}

	/**
	 * 上传excel，执行卡的批量入库操作
	 */
	@RequestMapping("batchCardStockIn")
	public void batchCardStockIn(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") CommonsMultipartFile file, Integer supplierId, Integer cardOwnerId, Integer supplierPoolId, Integer cardState, String comment) throws Exception {
		try {
			if(file == null || supplierId == null || cardOwnerId == null || supplierPoolId == null || cardState == null) {
				setJsonFail(response, null, 1100, "请求参数错误，必须指定[运营商][归属方][套餐][卡生命周期状态]");
				return;
			}
			ExcelHelper eh = new ExcelHelper();
			List<Entity> list = eh.dealExcel(file);
			// 先插入批次表并显示给运营人员
			CardBatchProcess cbp = new CardBatchProcess();
			cbp.setCode(DateUtil.getCurDateTime(DateUtil.DATE_FORMAT_COMPACTFULL));
			cbp.setAction(Constant.BATCH_CARD_STOCKIN);
			cbp.setNumber(list.size());
			cbp.setOperator("待补充");
			cbp.setComment(comment);
			cbp.setStartTime(new Date());
			cbp.setState(0);
			cardBatchProcessService.insert(cbp);
			setJsonSuccess(response, null, "批量任务开始执行，请在列表中观看执行结果", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			response.getWriter().flush();
			response.getWriter().close();
			// 新卡入卡库表
			for(Entity entity : list) {
				Card<T> card = new Card();
				card.setIccid(entity.getIccid());
				card.setMsisdn(entity.getMsisdn());
				card.setImsi(entity.getImsi());
				card.setSupplierId(supplierId);
				card.setCardOwnerId(cardOwnerId);
				card.setCardState(cardState);
				card.setPoolId(supplierPoolId);
				card.setCreateTime(new Date());
				cardService.insert(card);
			}
			cbp.setEndTime(new Date());
			cbp.setState(1);
			try {
				// 存储excel文件，方便下载查看。出错也没关系
				String name = FileUtils.saveFile(file, cbp.getId() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")), dataDictionaryService.getDicByKey(Constant.EXCEL_SAVE_PATH).getValue());
				cbp.setFilePath(dataDictionaryService.getDicByKey(Constant.EXCEL_SAVE_PATH).getValue() + File.separator + name);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			cardBatchProcessService.update(cbp);
			// setJsonSuccess(response, null, "批量入库成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
		}
		catch(Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1001, "批量入库失败！原因：" + e.getMessage());
		}
	}

	/**
	 * 批量文件的下载
	 * @param request
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response, Integer id) throws Exception {
		CardBatchProcess cbp = cardBatchProcessService.getById(id);
		if(cbp != null && StringUtils.isNotEmpty(cbp.getFilePath())) {
			String path = cbp.getFilePath();
			// Content-Disposition属性有两种类型：inline 和 attachment inline ：将文件内容直接显示在页面 attachment：弹出对话框让用户下载具体例子：
			response.setContentType("application/vnd.ms-excel");
			// response.setContentType("application/force-download");//应用程序强制下载
			response.setHeader("Content-disposition", "attachment;filename=" + id + path.substring(path.indexOf(".")));   // 这个filename就是用户下载后的文件名称
			// response.addHeader("Content-Length", "" + file.length());
			response.setHeader("Pragma", "No-cache");

			InputStream in = null;
			OutputStream out = null;
			// 读取到的数据长度
			try {
				// 输出的文件流保存到本地文件
				in = new FileInputStream(path);
				// 开始读取
				out = response.getOutputStream();
				byte[] b = new byte[1024];
				int len = 0;
				while((len = in.read(b)) != -1) {
					out.write(b, 0, len);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				out.flush();
				out.close();
				in.close();
			}
		}
	}

	/******************************************************************
	 * 批量变更卡的归属人相关功能
	 *******************************************************************/

	/**
	 * 弹出批量变更归属人页面
	 */
	@RequestMapping("getBatchChangeCardOwnerPage")
	public String getBatchChangeCardOwnerPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取归属方列表
		List<CardOwner> cardOwnerList = cardOwnerService.getListByMap(new HashMap<String, Object>());
		request.setAttribute("cardOwnerList", cardOwnerList);
		return "cardBatchProcess/batchChangeCardOwnerAdd";
	}

	/**
	 * 批量变更归属人执行逻辑
	 */
	@RequestMapping("batchChangeCardOwner")
	public void batchChangeCardOwner(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") CommonsMultipartFile file, Integer cardOwnerId, String comment) throws Exception {
		try {
			if(file == null || cardOwnerId == null) {
				setJsonFail(response, null, 1100, "请求参数错误，必须指定[归属方]");
				return;
			}
			ExcelHelper eh = new ExcelHelper();
			List<Entity> list = eh.dealExcel(file);
			// 先插入批次表并显示给运营人员
			CardBatchProcess cbp = new CardBatchProcess();
			cbp.setCode(DateUtil.getCurDateTime(DateUtil.DATE_FORMAT_COMPACTFULL));
			cbp.setAction(Constant.BATCH_CARD_OWNER_CHANGE);
			cbp.setNumber(list.size());
			cbp.setOperator("待补充");
			cbp.setComment(comment);
			cbp.setStartTime(new Date());
			cbp.setState(0);
			cardBatchProcessService.insert(cbp);
			setJsonSuccess(response, null, "批量任务开始执行，请在列表中观看执行结果", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			response.getWriter().flush();
			response.getWriter().close();
			// 更新卡的归属方字段
			for(Entity entity : list) {
				Card<T> card = new Card();
				card.setIccid(entity.getIccid());
				List<Card> cardList = cardService.getListByPo(card);
				if(cardList == null || cardList.size() != 1) {
					throw new Exception("更新ICCID:" + entity.getIccid() + "归属方时失败，没有找到此卡，或者找到重复卡");
				}
				Card temp = cardList.get(0);
				// 先把之前的卡归属人记录到po
				CardOwnerChangeHistory cwch = new CardOwnerChangeHistory();
				cwch.setSupplierId(temp.getSupplierId());
				cwch.setFromOwnerId(temp.getCardOwnerId());
				cwch.setToOwnerId(cardOwnerId);// 要变更的归属人id
				cwch.setBatchNumber(cbp.getCode());
				cwch.setCardId(temp.getId());
				cwch.setCreateTime(new Date());
				// 插入归属人历史变更表，留作记录
				cardOwnerChangeHistoryService.insert(cwch);
				//把卡的归属人进行变更，并修改到card库
				temp.setCardOwnerId(cardOwnerId);
				cardService.update(temp);//更新card表中归属方信息
			}
			cbp.setEndTime(new Date());
			cbp.setState(1);
			try {
				// 存储excel文件，方便下载查看。出错也没关系
				String name = FileUtils.saveFile(file, cbp.getId() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")), dataDictionaryService.getDicByKey(Constant.EXCEL_SAVE_PATH).getValue());
				cbp.setFilePath(dataDictionaryService.getDicByKey(Constant.EXCEL_SAVE_PATH).getValue() + File.separator + name);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			cardBatchProcessService.update(cbp);
			// setJsonSuccess(response, null, "批量入库成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
		}
		catch(Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1001, "批量入库失败！原因：" + e.getMessage());
		}
	}
	
	/******************************************************************
	 * 批量卡流量池套餐变更
	 *******************************************************************/

	/*
	 * 点击“批量套餐变更”，加载批量套餐变更页面
	 */
	@RequestMapping("getCardBatchPoolChangePage")
	public String getCardBatchPoolChangePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获取套餐列表
		List<SupplierPool> supplierPoolList = supplierPoolService.getListByMap(new HashMap<String, Object>());
		request.setAttribute("supplierPoolList", supplierPoolList);
		return "cardBatchProcess/cardBatchPoolChangeAdd";
	}
	

	/**
	 * 批量变更卡套餐执行逻辑
	 */
	@RequestMapping("batchPoolChange")
	public void batchPoolChange(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") CommonsMultipartFile file, Integer supplierPoolId, String comment) throws Exception {
		try {
			if(file == null || supplierPoolId == null) {
				setJsonFail(response, null, 1100, "请求参数错误，必须指定[套餐/流量池ID]");
				return;
			}
			ExcelHelper eh = new ExcelHelper();
			List<Entity> list = eh.dealExcel(file);
			// 先插入批次表并显示给运营人员
			CardBatchProcess cbp = new CardBatchProcess();
			cbp.setCode(DateUtil.getCurDateTime(DateUtil.DATE_FORMAT_COMPACTFULL));
			cbp.setAction(Constant.BATCH_CARD_POOL_CHANGE);
			cbp.setNumber(list.size());
			cbp.setOperator("待补充");
			cbp.setComment(comment);
			cbp.setStartTime(new Date());
			cbp.setState(0);
			cardBatchProcessService.insert(cbp);
			setJsonSuccess(response, null, "批量任务开始执行，请在列表中观看执行结果", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			response.getWriter().flush();
			response.getWriter().close();
			// 更新卡的归属方字段
			for(Entity entity : list) {
				Card<T> card = new Card();
				card.setIccid(entity.getIccid());
				List<Card> cardList = cardService.getListByPo(card);
				if(cardList == null || cardList.size() != 1) {
					throw new Exception("更新ICCID:" + entity.getIccid() + "流量池套餐时失败，没有找到此卡，或者找到重复卡");
				}
				Card temp = cardList.get(0);
				// 先把之前的卡套餐记录到po
				CardPoolChangeHistory cpch = new CardPoolChangeHistory();
				cpch.setCardId(temp.getId());
				cpch.setOriginalPoolId(temp.getPoolId());
				cpch.setFinalPoolId(supplierPoolId);
				cpch.setBatchCode(cbp.getCode());
				cpch.setCreateTime(new Date());
//				cpch.setTriggerPoint("admin批量变更卡套餐");
				// 插入套餐历史变更表，留作记录
				cardPoolChangeHistoryService.insert(cpch);
				//把卡的归属人进行变更，并修改到card库
				temp.setPoolId(supplierPoolId);
				cardService.update(temp);//更新card表中归属方信息
			}
			cbp.setEndTime(new Date());
			cbp.setState(1);
			try {
				// 存储excel文件，方便下载查看。出错也没关系
				String name = FileUtils.saveFile(file, cbp.getId() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")), dataDictionaryService.getDicByKey(Constant.EXCEL_SAVE_PATH).getValue());
				cbp.setFilePath(dataDictionaryService.getDicByKey(Constant.EXCEL_SAVE_PATH).getValue() + File.separator + name);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			cardBatchProcessService.update(cbp);
			// setJsonSuccess(response, null, "批量入库成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
		}
		catch(Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1001, "批量更新卡流量池id失败！原因：" + e.getMessage());
		}
	}
	
	/******************************************************************
	 * 批量销号处理
	 *******************************************************************/
	/*
	 * 点击“批量销号”，加载批量销号页面
	 */
	@RequestMapping("getBatchCardWrittenOffPage")
	public String getBatchCardWrittenOffPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "cardBatchProcess/batchCardWrittenOffAdd";
	}
	
	/**
	 * 批量卡销号执行逻辑
	 */
	@RequestMapping("batchCardWrittenOff")
	public void batchCardWrittenOff(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") CommonsMultipartFile file, String comment) throws Exception {
		try {
			if(file == null) {
				setJsonFail(response, null, 1100, "请求参数错误，文件不能为空");
				return;
			}
			ExcelHelper eh = new ExcelHelper();
			List<Entity> list = eh.dealExcel(file);
			// 先插入批次表并显示给运营人员
			CardBatchProcess cbp = new CardBatchProcess();
			cbp.setCode(DateUtil.getCurDateTime(DateUtil.DATE_FORMAT_COMPACTFULL));
			cbp.setAction(Constant.BATCH_CARD_WRITTEN_OFF);
			cbp.setNumber(list.size());
			cbp.setOperator("待补充");
			cbp.setComment(comment);
			cbp.setStartTime(new Date());
			cbp.setState(0);
			cardBatchProcessService.insert(cbp);
			setJsonSuccess(response, null, "批量任务开始执行，请在列表中观看执行结果", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			response.getWriter().flush();
			response.getWriter().close();
			// 更新卡的归属方字段
			for(Entity entity : list) {
				Card<T> card = new Card();
				card.setIccid(entity.getIccid());
				List<Card> cardList = cardService.getListByPo(card);
				if(cardList == null || cardList.size() != 1) {
					throw new Exception("更新ICCID:" + entity.getIccid() + "流量池套餐时失败，没有找到此卡，或者找到重复卡");
				}
				Card temp = cardList.get(0);
				// 插入卡注销表 
				CardWrittenOff cwo =  new CardWrittenOff(temp);
				cwo.setWrittenOffTime(new Date());
				cardWrittenOffService.insert(cwo);
				// card表数据清理？？？？删除还是改状态？？？
				cardService.deleteById(temp.getId());
			}
			cbp.setEndTime(new Date());
			cbp.setState(1);
			try {
				// 存储excel文件，方便下载查看。出错也没关系
				String name = FileUtils.saveFile(file, cbp.getId() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")), dataDictionaryService.getDicByKey(Constant.EXCEL_SAVE_PATH).getValue());
				cbp.setFilePath(dataDictionaryService.getDicByKey(Constant.EXCEL_SAVE_PATH).getValue() + File.separator + name);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			cardBatchProcessService.update(cbp);
			// setJsonSuccess(response, null, "批量入库成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
		}
		catch(Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1001, "批量注销卡失败！原因：" + e.getMessage());
		}
	}
	
}
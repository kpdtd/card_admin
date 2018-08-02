package com.anl.card.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
import com.anl.card.persistence.po.SelectGroup;
import com.anl.card.persistence.po.Supplier;
import com.anl.card.persistence.po.SupplierPool;
import com.anl.card.service.CardBatchProcessService;
import com.anl.card.service.CardOwnerService;
import com.anl.card.service.CardService;
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
	SupplierPoolService supplierPoolService;

	@Autowired
	DataDictionaryService dataDictionaryService;

	@RequestMapping("getPage")
	public String getPage() throws Exception {
		return "cardBatchProcess/cardBatchProcess";
	}

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

	/*
	 * 加载批量入库页面
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
	 * 批量入库操作
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
			Thread.sleep(20*1000);
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
//			setJsonSuccess(response, null, "批量入库成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
		}
		catch(Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1001, "批量入库失败！原因：" + e.getMessage());
		}
	}

	@RequestMapping("addCardBatchProcess")
	public void addCardBatchProcess(HttpServletRequest request, HttpServletResponse response, CardBatchProcess cardBatchProcess) throws Exception {
		try {
			if(Objects.isNull(cardBatchProcess.getId())) {
				cardBatchProcessService.insert(cardBatchProcess);
				setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			else {
				cardBatchProcessService.update(cardBatchProcess);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}

		}
		catch(Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}

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

}
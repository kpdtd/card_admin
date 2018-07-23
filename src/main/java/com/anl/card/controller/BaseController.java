package com.anl.card.controller;


import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.DataDictionary;
import com.anl.card.service.DataDictionaryService;
import com.anl.card.util.FileUtils;
import com.anl.card.util.JsonHelper;
import com.anl.card.util.LogFactory;
import com.anl.card.util.StringUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController {
	public static final Logger logger = LogFactory.getInstance().getLogger();
	public static final Logger exception = LogFactory.getInstance().getErrorLogger();
	protected int start; // 起始页
	protected int iDisplayLength = 100; // 每页显示的条数
	protected int recordsTotal; // 总记录数
	protected int recordsFiltered; //
	protected int recordsDisplay; //
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	public static final boolean SUCCESS = true;
	public static final boolean FAIL = false;
	@Autowired(required = true)
	protected DataDictionaryService dataDictionaryService;


	protected String getUsername() {
//		Subject subject = SecurityUtils.getSubject();
//		SysUsers user = (SysUsers) subject.getPrincipal();
//		return user.getUserAccount();
		return "test";
	}

	/**
	 * 关闭窗口并且刷新页面
	 */
	protected static final String RESULT_TYPE_CLOSE_BOX_SELFPAGE = "close_box_selfpage";

	/**
	 * 关闭窗口并且执行函数
	 */
	protected static final String RESULT_TYPE_CLOSE_BOX_FUNCTION = "close_box_function";
	/**
	 * 执行传入的函数
	 */
	protected static final String RESULT_TYPE_FUNCTION = "function";

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}
	// 图片存放地址获取
	public String getImgFileURL(){
		DataDictionary dic = dataDictionaryService.getDicByKey(Constant.IMG_SAVE_URL);
		return dic.getValue();
	}
	// 图片存放地址获取
	public String getImgFilePath(){
		DataDictionary dic = dataDictionaryService.getDicByKey(Constant.IMG_SAVE_PATH);
		return dic.getValue();
	}

	/**
	 * 将数据信息写到客户端
	 * 
	 * @param respData
	 */
	protected void writerToClient(String respData) {
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(respData);
			writer.flush();
		} catch (IOException e) {
			exception.error("系统错误：" + e.getMessage());
		}
	}

	protected void writerToClient(List<?> data) {
		Map<String, Object> respData = new HashMap<String, Object>();
		respData.put("data", data);
		respData.put("length", iDisplayLength);
		respData.put("recordsDisplay", data.size());
		respData.put("recordsFiltered", recordsFiltered);
		respData.put("recordsTotal", recordsFiltered);
		respData.put("start", start);
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.write(JsonHelper.toJson(respData));
			writer.flush();
		} catch (IOException e) {
			exception.error("系统错误：" + e.getMessage());
		}
	}

	/*protected String getUsername() {
		Subject subject = SecurityUtils.getSubject();
		SysUsers user = (SysUsers) subject.getPrincipal();
		return user.getUserAccount();
	}*/
	
	protected Map<String, Object> convertToMap(List<?> data, int length, int recordsDisplay, int recordsFiltered,
			int recordsTotal, int start) {
		Map<String, Object> respData = new HashMap<String, Object>();
		respData.put("data", data);
		respData.put("length", length);
		respData.put("recordsDisplay", recordsDisplay);
		respData.put("recordsFiltered", recordsFiltered);
		respData.put("recordsTotal", recordsTotal);
		respData.put("start", start);
		return respData;
	}

	/**
	 * 分页基本属性
	 * 
	 * @param map
	 */
	protected void pageProperties(Map<String, Object> map) {
		map.put("startPage", getStart());
		map.put("pageSize", getIDisplayLength());
	}

	/**
	 * 组合列表的属性排序方式
	 * 
	 * @return
	 */
	protected void groupSortMap(Map<String, Object> map) {
		String sortCol = request.getParameter("iSortCol_0");// 点击排序列的数组序号
		String sSortDir_0 = request.getParameter("sSortDir_0"); // 点击拍序列的排序方式desc
																// asc
		String mDataProp_ = request.getParameter("mDataProp_" + sortCol); // 获取拍序列的属性
		map.put("prop", StringUtil.humpToLine(mDataProp_));
		map.put("sort", sSortDir_0);
		map.put("startPage", getStart());
		map.put("pageSize", getIDisplayLength());
	}
	
	/**
	 * 文件上传
	 * @param serverSavePath
	 * @return
	 * @throws Exception
	 */
	protected Map<String,Object> fileUpload(String serverSavePath,String name) throws Exception {
		Map<String,Object> map = null;
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile orginalFile = (CommonsMultipartFile) multipartRequest.getFile(name);// 表单中对应的文件名；
		if (orginalFile != null && !orginalFile.isEmpty()) {// 如果有文章中带有附件
			map = new HashMap<String,Object>();
			String filename = orginalFile.getOriginalFilename();
			String ext = filename.substring(filename.lastIndexOf(".") + 1); // 得到文件的扩展名(无扩展名时将得到全名)
			String prefix = new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); // 系统生成的文件名称
			String filePath = FileUtils.createFilePath(serverSavePath);
			filename = prefix + "." + ext;
			String fileSavePath = filePath + File.separator + filename;
			DataOutputStream out = new DataOutputStream(new FileOutputStream(serverSavePath + File.separator + fileSavePath));// 存放文件的绝对路径
			InputStream is = null;// 附件输入流
			try {
				is = orginalFile.getInputStream();
				byte[] b = new byte[is.available()];
				is.read(b);
				out.write(b);
				map.put("filePath", fileSavePath);
			} catch (IOException exception) {
				throw new Exception("图片上传失败");
			} finally {
				if (is != null) {
					is.close();
				}
				if (out != null) {
					out.close();
				}
			}
		}
		return map;
	}

	/**
	 * 描述: 设置下行成功JSON <br>
	 *
	 * @param response
	 *            - HttpServletResponse
	 * @param data
	 *            - 数据，可为空
	 * @param msg
	 *            - 描述
	 * @param resultType
	 *            - 返回操作类型
	 */
	protected void setJsonSuccess(HttpServletResponse response, Object data, String msg, String resultType) {
		setJson(response, data, SUCCESS, 0, msg, resultType);
	}

	/**
	 * 描述: 设置下行失败JSON <br>
	 *
	 * @param response
	 *            - HttpServletResponse
	 * @param data
	 *            - 数据，可为空
	 * @param code
	 *            - 信息代码
	 * @param msg
	 *            - 描述
	 * @param resultType
	 *            - 返回操作类型
	 */
	protected void setJsonFail(HttpServletResponse response, Object data, int code, String msg, String resultType) {
		setJson(response, data, FAIL, code, msg, resultType);
	}

	/**
	 * 描述: 设置下行JSON <br>
	 *
	 * @param data
	 *            - 数据，可为空
	 * @param success
	 *            - 成功还是失败
	 * @param code
	 *            - 信息代码
	 * @param msg
	 *            - 描述
	 * @param resultType
	 *            - 返回操作类型
	 */
	protected void setJson(HttpServletResponse response, Object data, boolean success, int code, String msg,
			String resultType) {
		response.setCharacterEncoding("UTF-8");
		try {
			Map<String, Object> respData = new HashMap<String, Object>();
			if (data != null) {
				respData.put("data", data);
			}
			respData.put("success", success);
			respData.put("msg", msg);
			respData.put("code", code);
			respData.put("resultType", resultType);

			response.getWriter().write(JsonHelper.toJson(respData));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 描述: 设置下行JSON <br>
	 *
	 * @param data
	 *            - 数据，可为空
	 * @param success
	 *            - 成功还是失败
	 * @param code
	 *            - 信息代码
	 * @param msg
	 *            - 描述
	 */
	protected void setJson(HttpServletResponse response, Object data, boolean success, int code, String msg) {
		response.setCharacterEncoding("UTF-8");
		try {
			Map<String, Object> respData = new HashMap<String, Object>();
			if (data != null) {
				respData.put("data", data);
			}
			respData.put("success", success);
			respData.put("msg", msg);
			respData.put("code", code);
			response.getWriter().write(JsonHelper.toJson(respData));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 描述: 设置下行成功JSON <br>
	 *
	 * @param response
	 *            - HttpServletResponse
	 * @param data
	 *            - 数据，可为空
	 * @param msg
	 *            - 描述
	 */
	protected void setJsonSuccess(HttpServletResponse response, Object data, String msg) {
		setJson(response, data, SUCCESS, 0, msg);
	}

	/**
	 * 描述: 设置下行失败JSON <br>
	 *
	 * @param response
	 *            - HttpServletResponse
	 * @param data
	 *            - 数据，可为空
	 * @param code
	 *            - 信息代码
	 * @param msg
	 *            - 描述
	 */
	protected void setJsonFail(HttpServletResponse response, Object data, int code, String msg) {
		setJson(response, data, FAIL, code, msg);
	}

	/*protected SysUsers getUser(){
		Subject subject = SecurityUtils.getSubject();
		return (SysUsers) subject.getPrincipal();
	}*/
	
	public int getStart() {
		String iDisplayStart = request.getParameter("iDisplayStart");

		if (StringUtils.isBlank(iDisplayStart)) {
			return start;
		} else {
			return Integer.valueOf(iDisplayStart);
		}
	}

	public int getIDisplayLength() {
		String length = request.getParameter("iDisplayLength");
		if (StringUtils.isBlank(length)) {
			return iDisplayLength;
		} else {
			return Integer.valueOf(length);
		}
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public int getRecordsDisplay() {
		return recordsDisplay;
	}

	public void setRecordsDisplay(int recordsDisplay) {
		this.recordsDisplay = recordsDisplay;
	}
}

package com.anl.iot.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("restriction")
public class FileUtils {

	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * 
	 * @param filename
	 *            下载文件名
	 * @param agent
	 *            客户端浏览器
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public static String encodeDownloadFilename(String filename, String agent) throws IOException {
		if (agent.contains("Firefox")) { // 火狐浏览器
			filename = "=?UTF-8?B?" + new BASE64Encoder().encode(filename.getBytes("utf-8")) + "?=";
			filename = filename.replaceAll("\r\n", "");
		} else { // IE及其他浏览器
			filename = URLEncoder.encode(filename, "utf-8");
			filename = filename.replace("+", " ");
		}
		return filename;
	}

	/**
	 * 
	 * @param systemPath
	 *            系统配置路径
	 * @return
	 * @throws Exception
	 */
	public static String createFilePath(String systemPath) throws Exception {
		if (StringUtils.isBlank(systemPath)) {
			throw new Exception("未配置系统路径");
		}
		String extPrefix = new java.text.SimpleDateFormat("yyyyMMdd").format(new Date());
		File file = new File(systemPath + File.separator + extPrefix);

		if (file != null && (!file.exists())) {
			file.mkdirs();
		}
		return extPrefix;
	}


	public static String downloadXls(List<String> list, HttpServletRequest request, HttpServletResponse response, String fileTitle)throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 在excel中创建一个sheet页
		XSSFSheet sheet = workbook.createSheet("sheet1");
		// 在标签页中创建行
		XSSFRow title = sheet.createRow(0);
		title.setHeight((short) 500);
		// 行中创建列
		for (int i = 0; i <list.size() ; i++) {
			title.createCell(i).setCellValue(list.get(i));
			sheet.setColumnWidth(i, list.get(i).getBytes().length * 2 * 128);
		}
		/*title.createCell(0).setCellValue("供应商id");
		sheet.setColumnWidth(0, "供应商id".getBytes().length * 2 * 128);
		title.createCell(1).setCellValue("iccid");
		sheet.setColumnWidth(1, "iccid".getBytes().length * 2 * 150);
		title.createCell(2).setCellValue("msisdn");
		sheet.setColumnWidth(2, "msisdn".getBytes().length * 2 * 150);
		title.createCell(3).setCellValue("imsi");
		sheet.setColumnWidth(3, "imsi".getBytes().length * 2 * 150);
		title.createCell(4).setCellValue("运营商 1-移动  2-联通  3-电信");
		sheet.setColumnWidth(4, "运营商 1-移动  2-联通  3-电信".getBytes().length * 2 * 128);*/

		// 6、通过输出流写回Excel文件到浏览器,文件下载需要一个流（输出流）、两个头（设置头信息）
		ServletOutputStream outputStream = response.getOutputStream();
		String fileName = fileTitle+".xlsx";
		String agent = request.getHeader("User-Agent");
		fileName = FileUtils.encodeDownloadFilename(fileName, agent);
		String mimeType = request.getSession().getServletContext().getMimeType(fileName);
		// 获取mimeType
		response.setContentType(mimeType);
		response.setHeader("content-disposition", "attachment;filename=" + fileName);
		workbook.write(outputStream);
		outputStream.close();
		return null;
	}


	public static String downloadXls(List<String> list,List<Object> dataList, HttpServletRequest request, HttpServletResponse response, String fileTitle)throws Exception {
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 在excel中创建一个sheet页
		XSSFSheet sheet = workbook.createSheet("sheet1");
		// 在标签页中创建行
		XSSFRow title = sheet.createRow(0);
		title.setHeight((short) 500);
		// 行中创建列
		for (int i = 0; i <list.size() ; i++) {
			title.createCell(i).setCellValue(list.get(i));
			sheet.setColumnWidth(i, list.get(i).getBytes().length * 2 * 128);
		}
		for (int i = 0; i < dataList.size(); i++) {
			XSSFRow title1= sheet.createRow(i+1);
		}
		/*title.createCell(0).setCellValue("供应商id");
		sheet.setColumnWidth(0, "供应商id".getBytes().length * 2 * 128);
		title.createCell(1).setCellValue("iccid");
		sheet.setColumnWidth(1, "iccid".getBytes().length * 2 * 150);
		title.createCell(2).setCellValue("msisdn");
		sheet.setColumnWidth(2, "msisdn".getBytes().length * 2 * 150);
		title.createCell(3).setCellValue("imsi");
		sheet.setColumnWidth(3, "imsi".getBytes().length * 2 * 150);
		title.createCell(4).setCellValue("运营商 1-移动  2-联通  3-电信");
		sheet.setColumnWidth(4, "运营商 1-移动  2-联通  3-电信".getBytes().length * 2 * 128);*/

		// 6、通过输出流写回Excel文件到浏览器,文件下载需要一个流（输出流）、两个头（设置头信息）
		ServletOutputStream outputStream = response.getOutputStream();
		String fileName = fileTitle+".xlsx";
		String agent = request.getHeader("User-Agent");
		fileName = FileUtils.encodeDownloadFilename(fileName, agent);
		String mimeType = request.getSession().getServletContext().getMimeType(fileName);
		// 获取mimeType
		response.setContentType(mimeType);
		response.setHeader("content-disposition", "attachment;filename=" + fileName);
		workbook.write(outputStream);
		outputStream.close();
		return null;
	}

	public static String importFile(MultipartFile attachmentFile, String url, List<String> suffixNameList,String savePath) throws Exception {
		String fileName = attachmentFile.getOriginalFilename();
		// 获取后缀名
		int pos = fileName.lastIndexOf(".");
		String str = fileName.substring(pos + 1).toLowerCase();
		String suffixNameStr = "";
		for (int i = 0; i < suffixNameList.size(); i++) {
			suffixNameStr = suffixNameStr + suffixNameList.get(i) + "/";
		}

		// 判断上传文件必须是zip或者是rar否则不允许上传
		if (StringUtils.isEmpty(str) || !suffixNameStr.contains(str)) {
			throw new Exception("上传文件格式错误，请重新上传");
		}
		// 时间加后缀名保存
		String saveName = new Date().getTime() + "." + str;
		// 文件名
		String saveFileName = saveName.substring(0, saveName.lastIndexOf("."));
		// 根据服务器的文件保存地址和原文件名创建目录文件全路径
		File activityImgFilePath = new File(savePath + url + "/" + saveFileName);
		if (!activityImgFilePath.exists()) {
			activityImgFilePath.mkdirs();
		}
		File targetFile = new File(activityImgFilePath, saveName);
		// 上传到指定路径
		attachmentFile.transferTo(targetFile);
		LogFactory.getInstance().getLogger().debug("文件存储路径为：" + targetFile.getPath());
		String imgUrl = "/" + url + "/" + saveFileName + "/" + saveName;
		return imgUrl;
	}
}

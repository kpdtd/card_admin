package com.anl.card.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ExcelHelper {
	public List<Entity> dealExcel(CommonsMultipartFile file) {
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			XSSFSheet sheet = workbook.getSheetAt(0);
			int maxLineNumber = sheet.getLastRowNum();// 如果只有1行数据，但在第十行输入一个空格，此值会是9（从0行开始）
			List<Entity> result = new ArrayList<Entity>(maxLineNumber);
			for(Row row : sheet) {
				if(row.getRowNum() == 0) {// 第一行，进行行头验证
					verifyHead(row);
					continue;
				}

				String iccid = null;
				String msisdn = null;
				String imsi = null;

				if(row.getCell(0) == null) {// || row.getCell(0).getStringCellValue().trim().length() != 20
					throw new RuntimeException("错误行：" + (row.getRowNum() + 1) + " 第一列的ICCID必须有值，且必须为20位");
				}
				else {
					row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
					iccid = row.getCell(0).getStringCellValue();
					if(iccid.length() != 20) {
						throw new RuntimeException("错误行：" + (row.getRowNum() + 1) + " 第一列的ICCID必须有值，且必须为20位");
					}
				}

				if(row.getCell(1) != null) {// 可以没有值
					row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
					msisdn = row.getCell(1).getStringCellValue().trim();
					if(msisdn.length() != 11 && msisdn.length() != 13) {
						throw new RuntimeException("错误行：" + (row.getRowNum() + 1) + " 第二列的MSISDN如果有值，应该是11位或13位");
					}
				}

				if(row.getCell(2) != null) {
					row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
					imsi = row.getCell(2).getStringCellValue().trim();
					if(imsi.length() != 15 && !imsi.startsWith("46")) {
						throw new RuntimeException("错误行：" + (row.getRowNum() + 1) + " 第三列IMSI如果有值，应该是15位且46开头");
					}
				}

				Entity entity = new Entity();
				entity.setIccid(iccid);
				entity.setMsisdn(msisdn);
				entity.setImsi(imsi);
				result.add(entity);
			}
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	private void verifyHead(Row row) {
		String iccid = "";
		String msisdn = "";
		String imsi = "";
		if(row.getCell(0) != null) {
			row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
			iccid = row.getCell(0).getStringCellValue();
		}
		if(row.getCell(1) != null) {
			row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
			msisdn = row.getCell(1).getStringCellValue();
		}
		if(row.getCell(2) != null) {
			row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
			imsi = row.getCell(2).getStringCellValue();
		}
		if(!("iccid".equals(iccid) && "msisdn".equals(msisdn) && "imsi".equals(imsi))) {
			throw new RuntimeException("行头不正确，请严格按下载模板填入数据");
		}
	}

	public class Entity {
		private String iccid;
		private String msisdn;
		private String imsi;

		public String getIccid() {
			return iccid;
		}

		public void setIccid(String iccid) {
			this.iccid = iccid;
		}

		public String getMsisdn() {
			return msisdn;
		}

		public void setMsisdn(String msisdn) {
			this.msisdn = msisdn;
		}

		public String getImsi() {
			return imsi;
		}

		public void setImsi(String imsi) {
			this.imsi = imsi;
		}

	}

	public static void main(String[] args) throws Exception {
		File file = new File("/Users/suntao/TigerJoys/物联网项目/注销的卡.xlsx");
		FileInputStream in = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(in);
		XSSFSheet sheet = workbook.getSheetAt(0);
		List cardList = new ArrayList<>();
		int quantity = 0;
		System.out.println("@@@@" + sheet.getLastRowNum()); // 得到最后一行的行数，从0开始 （直观看的的5行，这里打印是4）
		for(Row row : sheet) {
			quantity++;
			System.out.println(quantity + "---quantity||" + row.getRowNum());
			String iccid = StringUtils.isNotBlank(row.getCell(0).getStringCellValue()) ? row.getCell(0).getStringCellValue() : null;

		}
	}

}

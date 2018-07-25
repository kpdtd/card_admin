package com.anl.card.controller;

import com.anl.card.persistence.po.CardWrittenOff;
import com.anl.card.persistence.po.SelectGroup;
import com.anl.card.persistence.po.Supplier;
import com.anl.card.service.CardWrittenOffService;
import com.anl.card.service.SupplierService;
import com.anl.card.util.DateUtil;
import com.anl.card.util.FileUtils;
import com.anl.card.vo.CardWrittenOffExt;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 类名: CardWrittenOffController
 * 创建日期:
 * 功能描述:
 */
@Controller
@RequestMapping("/cardWrittenOff")
public class CardWrittenOffController extends BaseController {
    @Autowired
    CardWrittenOffService cardWrittenOffService;
    @Autowired
    SupplierService supplierService;

    @RequestMapping("getPage")
    public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //查询出运营商列表,提供给查询栏下拉列表使用
        List<Supplier> supplierList = supplierService.getListByMap(new HashMap<>());
        request.setAttribute("supplierList", supplierList);
        List<SelectGroup> cardStateList = dataDictionaryService.getValueListByKey("CARD_STATE_LIST");
        request.setAttribute("cardStateList", cardStateList);
        return "cardWrittenOff/cardWrittenOff";
    }

    @RequestMapping("getList")
    @ResponseBody
    public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //获取查询条件过来的参数
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String supplierId = request.getParameter("supplierId");
        String cardState = request.getParameter("cardState");
        String iccid = request.getParameter("iccid");
        Map<String, Object> model = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(startTime)) {
            model.put("startTime", startTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            model.put("endTime", endTime);
        }
        if (StringUtils.isNotBlank(supplierId)) {
            model.put("supplierId", Integer.parseInt(supplierId));
        }
        if (StringUtils.isNotBlank(iccid)) {
            model.put("iccid", iccid);
        }
        if (StringUtils.isNotBlank(cardState)) {
            model.put("cardState", Integer.parseInt(cardState));
        }
        pageProperties(request, response, model);
        int count = cardWrittenOffService.count(model);
        recordsTotal = count;
        // 分页显示上面查询出的数据结果
        List<CardWrittenOffExt> data = cardWrittenOffService.getListByCondition(model);
        recordsFiltered = recordsTotal;
        recordsDisplay = data.size();
        //查询出运营商列表,提供给查询栏下拉列表使用
        List<Supplier> supplierList = supplierService.getListByMap(new HashMap<>());
        request.setAttribute("supplierList", supplierList);
        List<SelectGroup> cardStateList = dataDictionaryService.getValueListByKey("CARD_STATE_LIST");
        request.setAttribute("cardStateList", cardStateList);
        this.writerToClient(data, response);
    }

	/*@RequestMapping("detail")
    public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			CardWrittenOff cardWrittenOff = cardWrittenOffService.getById(Integer.parseInt(id));
			request.setAttribute("cardWrittenOff", cardWrittenOff);
		}
		return "cardWrittenOff/detail";
	}*/

    @RequestMapping("add")
    public String add(HttpServletRequest request, HttpServletResponse response, Integer id) throws Exception {
        try {
            if (Objects.isNull(id)) {
                return "cardWrittenOff/cardWrittenOffAdd";
            }
            CardWrittenOff cardWrittenOff = cardWrittenOffService.getById(id);
            request.setAttribute("cardWrittenOff", cardWrittenOff);
            return "cardWrittenOff/cardWrittenOffAdd";
        } catch (Exception e) {
            e.printStackTrace();
            return "cardWrittenOff/cardWrittenOffAdd";
        }
    }

    @RequestMapping("addCardWrittenOff")
    public void addCardWrittenOff(HttpServletRequest request, HttpServletResponse response, CardWrittenOff cardWrittenOff) throws Exception {
        try {
            if (Objects.isNull(cardWrittenOff.getId())) {
                cardWrittenOffService.insert(cardWrittenOff);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
            } else {
                cardWrittenOffService.update(cardWrittenOff);
                setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
            }

        } catch (Exception e) {
            e.printStackTrace();
            setJsonFail(response, null, 1100, "操作失败！");
        }
    }

    /**
     * 进入详细页面
     *
     * @param id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("detailPage")
    public String detailPage(Integer id, HttpServletRequest request, HttpServletResponse response) {

        try {
            if (id != null && id > 0) {
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("id", id);
                List<CardWrittenOffExt> data = cardWrittenOffService.getListByCondition(model);
                if (CollectionUtils.isNotEmpty(data)) {
                    request.setAttribute("cardWrittenOff", data.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SelectGroup> cardStateList = dataDictionaryService.getValueListByKey("CARD_STATE_LIST");
        request.setAttribute("cardStateList", cardStateList);
        return "cardWrittenOff/cardWrittenDetails";
    }

    @RequestMapping(value = "/downloadAll", method = RequestMethod.GET)
    public void downloadAll(Integer supplierId, Integer cardState, String iccid, String startTime, String endTime, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> model = new HashMap<>();
        if (StringUtils.isNotBlank(startTime)) {
            model.put("startTime", startTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            model.put("endTime", endTime);
        }
        if (supplierId != null) {
            model.put("supplierId", supplierId);
        }
        if (StringUtils.isNotBlank(iccid)) {
            model.put("iccid", iccid);
        }
        if (cardState != null) {
            model.put("cardState", cardState);
        }
        List<CardWrittenOffExt> data = cardWrittenOffService.getListByCondition(model);
        getxlsxByList(data, request, response);
        return;
    }


    private void getxlsxByList(List<CardWrittenOffExt> dataList, HttpServletRequest request, HttpServletResponse response) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在excel中创建一个sheet页
        XSSFSheet sheet = workbook.createSheet("sheet1");
        // 在标签页中创建行
        XSSFRow title = sheet.createRow(0);
        title.setHeight((short) 500);
        // 行中创建列
        title.createCell(0).setCellValue("ID");
        title.createCell(1).setCellValue("运营商");
        title.createCell(2).setCellValue("归属方");
        title.createCell(3).setCellValue("iccid");
        title.createCell(4).setCellValue("msisdn");
        title.createCell(5).setCellValue("imsi");
        title.createCell(6).setCellValue("激活时间");
        title.createCell(7).setCellValue("生命周期");
        title.createCell(8).setCellValue("运营状态");
        title.createCell(9).setCellValue("注销时间");

        for (int i = 0; i < dataList.size(); i++) {
            XSSFRow title1 = sheet.createRow(i + 1);
            String id = dataList.get(i).getId() + "";
            String supplierName = dataList.get(i).getSupplierName();
            String ownerCompany = dataList.get(i).getOwnerCompany();
            String iccid = dataList.get(i).getIccid();
            String msisdn = dataList.get(i).getMsisdn();
            String imsi = dataList.get(i).getImsi();
            String activationTime = DateUtil.dateToString(dataList.get(i).getActivationTime(), DateUtil.DATE_FORMAT_FULL);
            String cardState = dataList.get(i).getCardState() + "";
            String opState = dataList.get(i).getOpState() + "";
            String writtenOffTime = DateUtil.dateToString(dataList.get(i).getWrittenOffTime(), DateUtil.DATE_FORMAT_FULL);
            title1.createCell(0).setCellValue(id);
            if (StringUtils.isNotBlank(id)) {
                sheet.setColumnWidth(0, id.getBytes().length * 2 * 150);
            }
            title1.createCell(1).setCellValue(supplierName);
            if (StringUtils.isNotBlank(supplierName)) {
                sheet.setColumnWidth(1, supplierName.getBytes().length * 2 * 150);
            }
            title1.createCell(2).setCellValue(ownerCompany);
            if (StringUtils.isNotBlank(ownerCompany)) {
                sheet.setColumnWidth(2, ownerCompany.getBytes().length * 2 * 150);
            }
            title1.createCell(3).setCellValue(iccid);
            if (StringUtils.isNotBlank(iccid)) {
                sheet.setColumnWidth(3, iccid.getBytes().length * 2 * 150);
            }
            title1.createCell(4).setCellValue(msisdn);
            if (StringUtils.isNotBlank(msisdn)) {
                sheet.setColumnWidth(4, msisdn.getBytes().length * 2 * 150);
            }
            title1.createCell(5).setCellValue(imsi);
            if (StringUtils.isNotBlank(imsi)) {
                sheet.setColumnWidth(5, imsi.getBytes().length * 2 * 150);
            }
            title1.createCell(6).setCellValue(activationTime);
            if (StringUtils.isNotBlank(activationTime)) {
                sheet.setColumnWidth(6, activationTime.getBytes().length * 2 * 150);
            }
            title1.createCell(7).setCellValue(cardState);
            if (StringUtils.isNotBlank(cardState)) {
                sheet.setColumnWidth(7, cardState.getBytes().length * 2 * 150);
            }
            title1.createCell(8).setCellValue(opState);
            if (StringUtils.isNotBlank(opState)) {
                sheet.setColumnWidth(8, opState.getBytes().length * 2 * 150);
            }
            title1.createCell(9).setCellValue(writtenOffTime);
            if (StringUtils.isNotBlank(writtenOffTime)) {
                sheet.setColumnWidth(9, writtenOffTime.getBytes().length * 2 * 150);
            }
        }
        // 6、通过输出流写回Excel文件到浏览器,文件下载需要一个流（输出流）、两个头（设置头信息）
        ServletOutputStream outputStream = response.getOutputStream();
        String fileName = "注销卡导出.xlsx";
        String agent = request.getHeader("User-Agent");
        fileName = FileUtils.encodeDownloadFilename(fileName, agent);
        String mimeType = request.getSession().getServletContext().getMimeType(fileName);
        // 获取mimeType
        response.setContentType(mimeType);
        response.setHeader("content-disposition", "attachment;filename=" + fileName);
        workbook.write(outputStream);
        outputStream.close();
    }
}
package com.anl.card.controller;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.SelectGroup;
import com.anl.card.persistence.po.Supplier;
import com.anl.card.service.DataDictionaryService;
import com.anl.card.service.SupplierService;
import com.anl.card.util.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/** 
 * 类名: SupplierController
 * 创建日期: 
 * 功能描述: s
 */
@Controller
@RequestMapping(value="/supplier")
public class SupplierController extends BaseController {
	@Autowired
	SupplierService supplierService;
	@Autowired
	DataDictionaryService dataDictionaryService;
	
	@RequestMapping(value="getPage")
	public String getPage() throws Exception {
		request.setAttribute("menu", Constant.MENU_SUPPLIER);
		List<SelectGroup> stateList = dataDictionaryService.getValueListByKey("PUBLISH_STATE");
		request.setAttribute("stateList",stateList);
		return "supplier/supplier";
	}
	
	@RequestMapping(value="getList")
	@ResponseBody
	public void getList() throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		String state = request.getParameter("state");
		if(StringUtils.isNotBlank(state)){
			model.put("state",state);
		}
		String company = request.getParameter("company");
		if(StringUtils.isNotBlank(company)){
			model.put("company",company);
		}
		pageProperties(model);
		int count = supplierService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<Supplier> data = supplierService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data);
	}
	
	@RequestMapping(value="add")
	public String add(Integer id) throws Exception{
		request.setAttribute("menu", Constant.MENU_SUPPLIER);
		if(id!=null){
			Supplier supplier = supplierService.getById(id);
			request.setAttribute("supplier", supplier);
		}
		List<SelectGroup> providerTypeList = dataDictionaryService.getValueListByKey("PROVIDER_TYPE");
		request.setAttribute("providerTypeList", providerTypeList);
		List<SelectGroup> stateList = dataDictionaryService.getValueListByKey("PUBLISH_STATE");
		request.setAttribute("stateList", stateList);
		return "supplier/supplierAdd";
	}
	@RequestMapping(value="addSupplier")
	public void addSupplier(Supplier supplier,MultipartFile attachmentFile) throws Exception{
		try {
			// 先判断是否有图片上传
			if (attachmentFile != null) {
				//设置限制后缀名
				List<String> list = new ArrayList<>();
				list.add("png");
				list.add("pdf");
				list.add("jpg");
				String url = FileUtils.importFile(attachmentFile,"supplier/Attachment",list,getImgFilePath());
				supplier.setAttachment(url);
			} else if (attachmentFile == null && supplier.getAttachment()!= null) {
				String imgHost = dataDictionaryService.getDicByKey("D_IMG_HOST").getValue();
				supplier.setAttachment(supplier.getAttachment().replace(imgHost, ""));
			}
			supplier.setCreateTime(new Date());
//			SysUsers users = (SysUsers)request.getSession().getAttribute("loginUser");
//			if(users!=null){
				supplier.setCreator(getUsername());
//			}
			if(supplier.getId()!=null){
				supplierService.update(supplier);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else{
				supplierService.insert(supplier);
				setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "添加失败！");
		}
	}
	
}
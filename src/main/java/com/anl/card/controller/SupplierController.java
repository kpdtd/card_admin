package com.anl.card.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.Supplier;
import com.anl.card.service.SupplierService;

/** 
 * 类名: SupplierController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {
	@Autowired
	SupplierService supplierService;
	
	@RequestMapping("getPage")
	public String getPage() throws Exception {
		
		return "supplier/supplier";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList() throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(model);
		int count = supplierService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<Supplier> data = supplierService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data);
	}
	
	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			Supplier supplier = supplierService.getById(Integer.parseInt(id));
			request.setAttribute("supplier", supplier);
		}
		return "supplier/detail";
	}*/
	
	@RequestMapping("add")
	public String add(Integer id) throws Exception{
		try {
			if (Objects.isNull(id)) {
				return "supplier/supplierAdd";
			}
			Supplier supplier = supplierService.getById(id);
			request.setAttribute("supplier", supplier);
			return "supplier/supplierAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "supplier/supplierAdd";
		}
	}
	@RequestMapping("addSupplier")
	public void addSupplier(Supplier supplier) throws Exception{
		try {
			if (Objects.isNull(supplier.getId())) {
				supplierService.insert(supplier);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				supplierService.update(supplier);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(Supplier supplier) throws Exception{
//		try {
//			supplierService.update(supplier);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
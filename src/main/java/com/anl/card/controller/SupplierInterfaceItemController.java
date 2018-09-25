package com.anl.card.controller;

import java.util.*;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.InterfaceList;
import com.anl.card.persistence.po.Supplier;
import com.anl.card.service.InterfaceListService;
import com.anl.card.service.SupplierService;
import com.anl.card.vo.SupplierInterfaceItemExt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.anl.card.persistence.po.SupplierInterfaceItem;
import com.anl.card.service.SupplierInterfaceItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * 类名: SupplierInterfaceItemController
 * 创建日期: 
 * 功能描述: 
 */
@Controller
@RequestMapping("/supplierInterfaceItem")
public class SupplierInterfaceItemController extends BaseController {
	@Autowired
	SupplierInterfaceItemService supplierInterfaceItemService;
	@Autowired
	SupplierService supplierService;
	@Autowired
	InterfaceListService interfaceListService;
	@RequestMapping("getPage")
	public String getPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Supplier> supplierList =  supplierService.getListByMap(new HashMap<>());
		request.setAttribute("supplierList",supplierList);
		request.setAttribute(Constant.MENU_STRING, Constant.MENU_SUPPLIER_INTERFACE_ITEM);
		return "supplierInterfaceItem/supplierInterfaceItem";
	}
	
	@RequestMapping("getList")
	@ResponseBody
	public void getList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		pageProperties(request,response,model);
		int count = supplierInterfaceItemService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<SupplierInterfaceItem> data = supplierInterfaceItemService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	@RequestMapping("getListByCondition")
	@ResponseBody
	public void getListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> model = new HashMap<String,Object>();
		String supplierId=request.getParameter("supplierId");
		if(StringUtils.isNotBlank(supplierId)){
			model.put("supplierId",Integer.parseInt(supplierId));
		}
		pageProperties(request,response,model);
		int count = supplierInterfaceItemService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<SupplierInterfaceItemExt> data = supplierInterfaceItemService.getListByCondition(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data,response);
	}
	
	@RequestMapping("add")
	public String add(Integer id,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			List<Supplier> supplierList =  supplierService.getListByMap(new HashMap<>());
			request.setAttribute("supplierList",supplierList);
			List<InterfaceList> interfaceListList =  interfaceListService.getListByMap(new HashMap<>());
			request.setAttribute("interfaceListList",interfaceListList);
			if (Objects.isNull(id)) {
				return "supplierInterfaceItem/supplierInterfaceItemAdd";
			}
			SupplierInterfaceItem supplierInterfaceItem = supplierInterfaceItemService.getById(id);
			request.setAttribute("supplierInterfaceItem", supplierInterfaceItem);
			return "supplierInterfaceItem/supplierInterfaceItemAdd";
		} catch (Exception e) {
			e.printStackTrace();
			return "supplierInterfaceItem/supplierInterfaceItemAdd";
		}
	}
	@RequestMapping("addSupplierInterfaceItem")
	public void addSupplierInterfaceItem(SupplierInterfaceItem supplierInterfaceItem,HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			//set tag
			InterfaceList interfaceList=interfaceListService.getById(supplierInterfaceItem.getInterfaceId());
			if(interfaceList!=null){
				supplierInterfaceItem.setInterfaceId(interfaceList.getId());
			}
			if (Objects.isNull(supplierInterfaceItem.getId())) {
				supplierInterfaceItem.setCreateTime(new Date());
				supplierInterfaceItemService.insert(supplierInterfaceItem);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}else {
				supplierInterfaceItemService.update(supplierInterfaceItem);
				setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "操作失败！");
		}
	}
	
//	@RequestMapping("edit")
//	public void edit(SupplierInterfaceItem supplierInterfaceItem) throws Exception{
//		try {
//			supplierInterfaceItemService.update(supplierInterfaceItem);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
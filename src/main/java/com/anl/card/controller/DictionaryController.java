package com.anl.card.controller;

import com.anl.card.constant.Constant;
import com.anl.card.persistence.po.DataDictionary;
import com.anl.card.persistence.po.SelectGroup;
import com.anl.card.util.JsonHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * 数据字典模块
 * </p>
 * 作者 lianzhifei
 * 日期 2016 2016年9月20日
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController {
	
	@RequestMapping("getPage")
	public String getPage() throws Exception {
		request.setAttribute("menu", Constant.MENU_DICTIONARY);
		return "dictionary/dictionary";
	}
	
	@RequestMapping("add")
	public String add() throws Exception {
		request.setAttribute(Constant.MENU_STRING, Constant.MENU_DICTIONARY);
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			DataDictionary dictionary = dataDictionaryService.getById(Integer.parseInt(id));
			request.setAttribute("dic", dictionary);
		}
		return "dictionary/dictionaryAdd";
	}
	
	/**
	 * 
	 * getList
	 * @throws Exception
	 */
	@RequestMapping("getList")
	@ResponseBody
	public void getList() throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		pageProperties(model);
		// 根据查询条件查询的的数据信息并获取数据的总量
		int count = dataDictionaryService.count(model);
		recordsTotal = count;
		// 分页显示上面查询出的数据结果
		List<DataDictionary> data = dataDictionaryService.getListByMap(model);
		recordsFiltered = recordsTotal;
		recordsDisplay = data.size();
		this.writerToClient(data);
	}
	
	@RequestMapping("addDictionary")
	public void insert(DataDictionary dictionary) throws Exception {
		try {
			dataDictionaryService.insert(dictionary);
			setJsonSuccess(response, null, "添加成功",RESULT_TYPE_CLOSE_BOX_FUNCTION);
		}
		catch(Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "添加失败,请检查添加信息是否重复！");
		}
	}

	@RequestMapping("editDictionary")
	public void update(DataDictionary dictionary) throws Exception {
		try {
			dataDictionaryService.update(dictionary);
			setJsonSuccess(response, null, "修改成功",RESULT_TYPE_CLOSE_BOX_FUNCTION);
		}
		catch(Exception e) {
			e.printStackTrace();
			setJsonFail(response, null, 1100, "修改失败,请检查修改信息是否重复！");
		}
	}
	
	@RequestMapping("getDictionaryByKey")
	public void getDictionaryByKey() throws Exception {
		String key = request.getParameter("key");
		DataDictionary data = new DataDictionary();
		data.setName(key);
		List<SelectGroup> dicList = dataDictionaryService.getValueListByKey(key);

		if(!dicList.isEmpty()) {
			this.writerToClient(JsonHelper.toJson(dicList));
		}
		else {
			this.writerToClient("empty");
		}
	}

	@RequestMapping("getById")
	public void getById() throws Exception {
		Integer id = Integer.valueOf(request.getParameter("id"));
		DataDictionary dataDictionary = dataDictionaryService.getById(id);

		if(dataDictionary != null) {
			this.writerToClient(JsonHelper.toJson(dataDictionary));
		}
	}
	
	/**
	 * 
	 * deleteDictionary 方法描述: 删除数据字典 逻辑描述:
	 * 
	 * @throws Exception
	 * @since Ver 1.00
	 */
	@RequestMapping("delete")
	public void delete() throws Exception {
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			dataDictionaryService.deleteById(id);
			this.writerToClient("操作成功");// 成功
		}
		catch(Exception e) {
			e.printStackTrace();
			this.writerToClient("其他错误");// 成功
		}
	}
}

package com.anl.card.controller;

import com.anl.card.persistence.po.Card;
import com.anl.card.persistence.po.SelectGroup;
import com.anl.card.persistence.po.Supplier;
import com.anl.card.persistence.po.SupplierPool;
import com.anl.card.service.CardService;
import com.anl.card.service.SupplierPoolService;
import com.anl.card.service.SupplierService;
import com.anl.card.vo.SupplierPoolExt;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 类名: SupplierPoolControllersupplierPool
 * 创建日期:
 * 功能描述:
 */
@Controller
@RequestMapping("/supplierPool")
public class SupplierPoolController extends BaseController {
    @Autowired
    SupplierPoolService supplierPoolService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    CardService cardService;
    @RequestMapping("getPage")
    public String getPage() throws Exception {

        return "supplierPool/supplierPool";
    }

    @RequestMapping("getList")
    @ResponseBody
    public void getList() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        pageProperties(model);
        int count = supplierPoolService.count(model);
        recordsTotal = count;
        // 分页显示上面查询出的数据结果
        List<SupplierPool> data = supplierPoolService.getListByMap(model);
        recordsFiltered = recordsTotal;
        recordsDisplay = data.size();
        this.writerToClient(data);
    }

    @RequestMapping("getListByCondition")
    @ResponseBody
    public void getListByCondition() throws Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        pageProperties(model);
        int count = supplierPoolService.count(model);
        recordsTotal = count;
        // 分页显示上面查询出的数据结果
        List<SupplierPoolExt> _data = supplierPoolService.getListByCondition(model);
        List<SupplierPoolExt> data = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(_data)) {
            data = _data.stream().map(i -> {
                SupplierPoolExt supplierPoolExt = i;
                //计算每个流量池的卡总量,和流量池的流量总量
                Map<String, Object> map = new HashMap<>();
                map.put("poolId", supplierPoolExt.getId());
                try {
                    int total = cardService.count(map);
                    supplierPoolExt.setCardNumber(total);//卡数量
                    supplierPoolExt.setPoolTotal(total * supplierPoolExt.getPoolValue() / 1000);
                    supplierPoolExt.setPoolValue(supplierPoolExt.getPoolValue() / 1000);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return supplierPoolExt;
            }).collect(Collectors.toList());
        }
        recordsFiltered = recordsTotal;
        recordsDisplay = data.size();
        this.writerToClient(data);
    }

	/*@RequestMapping("detail")
	public String detail() throws Exception {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id)) {
			SupplierPool supplierPool = supplierPoolService.getById(Integer.parseInt(id));
			request.setAttribute("supplierPool", supplierPool);
		}
		return "supplierPool/detail";
	}*/

    @RequestMapping("add")
    public String add(Integer id) throws Exception {
        try {
            if (id != null) {
                SupplierPool supplierPool = supplierPoolService.getById(id);
                request.setAttribute("supplierPool", supplierPool);
            }
            //获取上游列表
            List<Supplier> supplierList = supplierService.getListByPo(new Supplier());
            request.setAttribute("supplierList", supplierList);
            return "supplierPool/supplierPoolAdd";
        } catch (Exception e) {
            e.printStackTrace();
            return "supplierPool/supplierPoolAdd";
        }
    }

    @RequestMapping("addSupplierPool")
    public void addSupplierPool(SupplierPool supplierPool) throws Exception {
        try {
            supplierPool.setCreateTime(new Date());
            supplierPool.setUpdateTime(new Date());
            if (Objects.isNull(supplierPool.getId())) {
                supplierPoolService.insert(supplierPool);
                setJsonSuccess(response, null, "添加成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
            } else {
                supplierPoolService.update(supplierPool);
                setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
            }

        } catch (Exception e) {
            e.printStackTrace();
            setJsonFail(response, null, 1100, "操作失败！");
        }
    }

//	@RequestMapping("edit")
//	public void edit(SupplierPool supplierPool) throws Exception{
//		try {
//			supplierPoolService.update(supplierPool);
//			setJsonSuccess(response, null, "修改成功", RESULT_TYPE_CLOSE_BOX_FUNCTION);
//		} catch (Exception e) {
//			setJsonFail(response, null, 1100, "修改失败！");
//		}
//	}
}
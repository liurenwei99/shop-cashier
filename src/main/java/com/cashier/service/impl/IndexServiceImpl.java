package com.cashier.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.common.DBValue;
import com.cashier.pojo.IndexParams;
import com.cashier.pojo.SelectParams;
import com.cashier.pojo.Where;
import com.cashier.service.BaseService;
import com.cashier.service.IndexService;
import com.cashier.service.ItemCatService;
import com.cashier.service.ItemService;

/**
 * <p>IndexServiceImpl: 首页service接口的实现类</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
@Service
public class IndexServiceImpl implements IndexService{
	@Autowired
    private ItemCatService itemCatService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private BaseService baseService;
	@Override
	public Map<String, Object> index(IndexParams params) {
		// 查询商品类目
		List<Map<String, Object>> list = itemCatService.selectByShopId(params.getShopId());
		
		if( list == null ||  list.size() == 0 ) {
			return null;
		}
//		if( params.getCatId() == null ) {// 加载首页
//			params.setCatId(list.get(0).get("category_number").toString());
//		}
		// 根据类目id查询商品
		List<Map<String, Object>> items = itemService.selectItemByCat(params.getCatId(),params.getShopId(),null);
		
		// 查询打印信息
		SelectParams selectParams = new SelectParams();
		selectParams.setTableName(DBValue.CASHIER_PRINTER);
		selectParams.setQcols("*");
		selectParams.setHasPage(false);
		selectParams.setCriterias(new Where().name("shop_id").eq("=",params.getShopId()).criterias());
		List<Map<String, Object>> printerList = baseService.selectList(selectParams);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cats", list);
		map.put("items", items);
		map.put("printers", printerList);
		System.out.println("返回："+map);
		System.out.println("商品数："+items.size());
		return map;
	}

}

package com.cashier.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.mapper.ItemMapper;
import com.cashier.pojo.IndexParams;
import com.cashier.pojo.Page;
import com.cashier.service.ItemService;

/**
 * <p>ItemServiceImpl: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemMapper itemMapper;
	
	@Override
	public Map<String, Object> getById(String id) {
		Map<String, Object> map = itemMapper.getById(id);
		
		return map;
	}

	@Override
	public List<Map<String, Object>> selectItemByCat(String catId,String shopId,Page page) {
		return itemMapper.selectItemByCat(catId, shopId, page);
	}

	
}

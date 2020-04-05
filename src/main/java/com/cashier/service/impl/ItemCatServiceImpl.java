package com.cashier.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.mapper.ItemCatMapper;
import com.cashier.service.ItemCatService;

/**
 * <p>ItemCatServiceImpl: 商品类目service实现类</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
@Service
public class ItemCatServiceImpl implements ItemCatService{

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public List<Map<String, Object>> selectByShopId(String shopId) {
		return itemCatMapper.selectByShopId(shopId);
		
	}

}

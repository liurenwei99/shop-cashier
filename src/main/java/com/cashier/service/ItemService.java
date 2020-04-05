package com.cashier.service;

import java.util.List;
import java.util.Map;

import com.cashier.pojo.IndexParams;
import com.cashier.pojo.Page;

/**
 * <p>ItemService: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
public interface ItemService {

	/**
	 * 根据商品id，查询单个商品
	 * @param id 商品id
	 * @return 商品信息
	 */
	Map<String,Object> getById(String id);

	/**
	 * 根据商品的类目id，查询商品
	 * 如果catId为null，那么就是根据店铺id查询全部
	 * @param catId 类目id
	 * @param shopId 店铺id，必须
	 * @return 返回商品列表
	 */
	List<Map<String, Object>> selectItemByCat(String catId,String shopId,Page page);
    
}

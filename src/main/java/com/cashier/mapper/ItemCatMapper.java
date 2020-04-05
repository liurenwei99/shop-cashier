package com.cashier.mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>ItemCatMapper: 商品类目mapper接口</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
public interface ItemCatMapper {
    /**
     * 根据店铺id查询店铺商品类目
     * @return 返回店铺所有类目
     */
	List<Map<String,Object>> selectByShopId(String shopId);
}

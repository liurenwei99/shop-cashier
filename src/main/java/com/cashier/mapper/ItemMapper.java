package com.cashier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cashier.pojo.Page;

/**
 * <p>ItemMapper: 产品mapper</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
public interface ItemMapper {

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
	 * @param page   分页
	 * @return 返回商品列表
	 */
	List<Map<String, Object>> selectItemByCat(@Param("catId")String catId,@Param("shopId")String shopId,@Param("page")Page page);
}

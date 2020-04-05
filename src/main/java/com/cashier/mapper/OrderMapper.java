package com.cashier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cashier.pojo.InsertParams;
import com.cashier.pojo.SelectParams;

/**
 * <p>OrderMapper: 订单mapper</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月14日
 * @version 1.0  
 */
public interface OrderMapper {
	// 添加主订单 
	int add(@Param("params")InsertParams[] params);
	// 添加订单详情
	int addDetail(@Param("params")InsertParams[] params);

	// 查询订单和订单详情
	Map<String,Object> selectOrderAndDetialById(Long id);
	
	/** 查询后厨订单列表 
	 * params ： 查询参数
	 *  1、当天时间段、2、店铺id
	 */
	List<Map<String, Object>> selectChefs(@Param("params") Map<String, Object> params);
	
}

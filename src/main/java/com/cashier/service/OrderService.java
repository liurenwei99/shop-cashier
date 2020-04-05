package com.cashier.service;

import java.util.List;
import java.util.Map;

import com.cashier.pojo.DeleteParams;
import com.cashier.pojo.InsertParams;
import com.cashier.pojo.OrderInfo;
import com.cashier.pojo.SelectParams;
import com.cashier.pojo.UpdateParams;

/**
 * <p>OrderService: 订单模块service接口</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月14日
 * @version 1.0  
 */
public interface OrderService {
	/**
	 * 创建订单，所有新建都当做是批量操作。
	 * @param params
	 * @return 返回主键
	 */
	long add(List<InsertParams> params);
	/**
	 * 删除订单，可以批量
	 * @param params
	 * @return
	 */
	int delete(Long orderId);
	
	/**
	 * 更新订单，先移除之前的数据到log中，再新增。
	 * @param params
	 * @return
	 */
	Long updateOrder(List<InsertParams> params);
	
	/**
	 * 查询订单列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> select(SelectParams params);
	
	/**
	 * 根据订单id，查询订单和详情
	 * @param id
	 * @return
	 */
	Map<String,Object> selectOrderAndDetialById(Long id);
	
	// 根据订单id查询主订单
	Map<String,Object> selectOrderMaster(Long orderId);
	// 根据订单id查询订单详情
	List<Map<String, Object>>  selectOrderDetails(Long orderId);
	/** 查询后厨订单列表 
	 * params ： 查询参数
	 *  1、当天时间段、2、店铺id
	 * 
	 */
	List<Map<String, Object>> selectChefs(Map<String, Object> params);
	/**
	 * 作废订单，其实是把pay_status改为4。
	 * @param orderId
	 * @return
	 */
	long cancel(UpdateParams params);
	
	
}

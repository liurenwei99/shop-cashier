package com.cashier.service;

import java.util.Map;

/**
 * <p>PayService: 支付service接口 </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年11月12日
 * @version 1.0  
 */
public interface PayService {

	/**
	 *  支付签名
	 * @param data
	 * @return  统一下单返回的数据  
	 * @throws Exception
	 */
	Map<String, String> payNow(Map<String, String> data ) throws Exception;
}

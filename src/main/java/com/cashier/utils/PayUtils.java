package com.cashier.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>PayUtils: 支付工具类</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年11月15日
 * @version 1.0  
 */
public class PayUtils {

	/**
	 * 返回 微信支付参数
	 * out_trade_no、total_fee必须设置
	 * 可设置的值：
	 * body、device_info、fee_type、spbill_create_ip、notify_url、trade_type（扫码支付必须填NATIVE）、product_id（扫码支付时必须填）
	 * @return 返回支付需要的参数bean
	 * 注意：
	 *   out_trade_no：每次支付都是唯一。
	 */
	public static Map<String, String> getWxPayBean() {
		 Map<String, String> data = new HashMap<String, String>();
		    data.put("body", "用餐支付");
//	        data.put("out_trade_no", trade_no);
//	        data.put("device_info", "");
	        data.put("fee_type", "CNY");
//	        data.put("total_fee", String.valueOf(op.get("total_money")));
	        data.put("spbill_create_ip", "61.140.47.21");
	        data.put("notify_url", "http://cashier.store.gzsttech.com:9999/cashier/order/payresult");;
	        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
	        data.put("product_id", "888888");
	        return data;
	}
}

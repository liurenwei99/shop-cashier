package com.cashier.pojo.vo;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>PayViewResult: 支付结果页面实体</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年11月15日
 * @version 1.0  
 */
@Data
@ToString
public class PayViewResult {
//	json.put("code_url", payNow.get("code_url"));
//	json.put("total_money",op.get("total_money"));
//	json.put("text","待支付");
//	json.put("logo","");
	private String total_money;// 支付 的金额
	private String text;// 支付状态
	private String logo;// 支付成功或失败的图标提示
//	private String pay_type;// 支付类型，如果是bs，说明是更新订单。
	
    private Integer status;// 状态
    private String pay_money;// 支付的钱
    private String code_url;// 付款码地址，如：weixin://xxxjgre？xxx
    private Integer order_type;// 订单类型
	private Integer pay_code;// 支付结果状态码，1待支付，2支付成功。
    private Integer pay_mode;// 支付类型
    private String message;// 一些提示消息
	private Long id;// 订单id
	private Map<String,Object> data;// 订单数据
	/**
	 * @param logo
	 * @param status
	 * @param pay_money
	 * @param code_url
	 * @param order_type
	 * @param pay_code
	 * @param id
	 * @param data
	 */
	public PayViewResult(String logo, Integer status, String pay_money, String code_url, Integer order_type,
			Integer pay_code, Long id, Map<String, Object> data) {
		super();
		this.logo = logo;
		this.status = status;
		this.pay_money = pay_money;
		this.code_url = code_url;
		this.order_type = order_type;
		this.pay_code = pay_code;
		this.id = id;
		this.data = data;
	}
	/**
	 * 
	 */
	public PayViewResult() {
		super();
	}
	
	
}

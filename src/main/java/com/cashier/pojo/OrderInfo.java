package com.cashier.pojo;

import java.math.BigInteger;

/**
 * <p>OrderInfo: 订单实体类 </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月14日
 * @version 1.0  
 */

public class OrderInfo {
	private Long id;
	private Long total_money;//   订单总金额            
	private Long reality_pay;//     实际付款金额  
	private String shop_id;//       关联的店铺id
	private String user_id;//       购买人id
	private String user_logo;//     购买人logo
	private Long address_id;//    收货地址id
	private String address;//       收货详细地址
	private String get_phone;//    收货联系手机号
	private String shop_name;//    店铺名字
	private String shop_logo;//     店铺logo
	private String user_name;//     店铺名字
	private Long logistical_id;//    物流id
	private Integer pay_type;//      支付类型 0 = 微信 1=支付宝 2...
	private Integer status;//         订单状态 0 =待支付 1 = 已支付 2 = 交易完成 3=订单取消
	private Integer has_del;//        0=否 1=用户删除  2=商家删除
	private Long create_time;//    创建时间
	private Long pay_time;//      付款时间
	private Long done_time;//     交易完成时间
	private Long update_time;//    订单更新时间
	
	
//	id          主键
//	order_id    主订单id
//	item_id     关联的产品id
//	title        产品标题/名称
//	show_img   产品展示图
//	price       产品价格
//	vip_price    会员价
//	count       购买的数量
//	spec        规格属性（json形式）
	
}

package com.cashier.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.cashier.config.WxPayConfig;
import com.cashier.service.PayService;
import com.cashier.wxpay.WXPay;

/**
 * <p>PayServiceImpl: 支付service接口实现</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年11月12日
 * @version 1.0  
 */
@Service
public class PayServiceImpl implements PayService{

	@Override
	public Map<String, String> payNow(Map<String, String> data) throws Exception {
		WxPayConfig config = new WxPayConfig();
        WXPay wxpay = new WXPay(config);
        try {
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println(resp);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

}

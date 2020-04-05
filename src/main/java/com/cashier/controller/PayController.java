package com.cashier.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cashier.common.FinalValue;
import com.cashier.config.WxPayConfig;
import com.cashier.service.PayService;
import com.cashier.wxpay.WXPay;
import com.cashier.wxpay.WXPayConstants;
import com.cashier.wxpay.WXPayUtil;

import common.WeResult;
import entity.WxPayParam;
import funclass.QrCodeFun;
import utils.HttpUtils;

/**
 * <p>PayController: 支付控制器</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月26日
 * @version 1.0  
 */
//@Controller
@RestController
@RequestMapping("/pay")
public class PayController {
    private static final String FORMAT_NAME = "JPG";
    String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    
    @Autowired
    private PayService payService;
    
//	// 微信商户平台商户号
//	@Value("${wx.mch_id}")
//	public  String mchId;
//	
//	//微信商户平台商户key
//	@Value("${wx.mch_key}")
//	public  String key;
//	@Value("${wx.appid}")
//	public  String appid;   *55万/s
    // 厨鲜生 美
	public static void  repay() throws Exception {
		WxPayParam param = new WxPayParam("wxa23d9815d991b55b", "1543277861", "9e11e87475ebfcb0cfcb2de4a6f1b9bb",null,100L );
		param.setTrade_type("MWEB");
	}
	@RequestMapping("/create_code")
	public void createQrCode() throws Exception {
//		QrCodeFun.encode("name=张三",null, "E:\\dev-wei-19813\\eclipse\\workspace\\utils\\src\\main\\java\\funclass", false);
		QrCodeFun.createQrCode("https://store.91changqi.com/restaurant/pay.html", "E:\\dev-wei-19813\\eclipse\\workspace\\utils\\src\\main\\java\\funclass", "pay.jpg");
	}
	@RequestMapping("/payNow")
	public WeResult payNow(Map<String, String> data) throws Exception {
		
		try {
			    data.put("body", "腾讯充值中心-QQ会员充值");
		        data.put("out_trade_no", new Date().getTime()+"xxx");
		        data.put("device_info", "");
		        data.put("fee_type", "CNY");
		        data.put("total_fee", "1");
		        data.put("spbill_create_ip", "61.140.47.21");
		        data.put("notify_url", "http://store.91changqi.com/restaurant/pay/result_post");
		        data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
		        data.put("product_id", "12");
			Map<String, String> payNow = payService.payNow(data);
			if( payNow != null ) {
				return WeResult.result(200, payNow);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return  WeResult.result(500, e.getMessage());
		}
		return WeResult.result(500, "未知错误！");
	}
	 public static void main(String[] args) throws Exception {
//		 new PayController().payNow(new HashMap<String, String>());
//		 new PayController().createQrCode();
		 WxPayConfig config = new WxPayConfig();
         WXPay wxpay = new WXPay(config);
//         {transaction_id=4200000468201911128232871005, nonce_str=U1x9DLSHjnQXy2hZwwQlEnkca0u6NQe4, bank_type=CFT, 
//         openid=o3r7Lv2AbCXHiPbn1QePe8Zplom8, sign=D2CF947BFCC16A86381F9F25953E16114B02D42AC4FFC07D8C19B14F7D391338, 
//         fee_type=CNY, mch_id=1543277861, cash_fee=1, out_trade_no=1573562692860xxx, appid=wxa23d9815d991b55b, total_fee=1, trade_type=NATIVE,
//         result_code=SUCCESS, time_end=20191112204516, is_subscribe=N, return_code=SUCCESS}
         Map<String,String> resultMap = new HashMap<String, String>();
         resultMap.put("transaction_id","4200000468201911128232871005");
         resultMap.put("nonce_str","U1x9DLSHjnQXy2hZwwQlEnkca0u6NQe4");
         resultMap.put("bank_type","CFT");
         resultMap.put("openid","o3r7Lv2AbCXHiPbn1QePe8Zplom8");
         resultMap.put("sign","D2CF947BFCC16A86381F9F25953E16114B02D42AC4FFC07D8C19B14F7D391338");
         resultMap.put("fee_type","CNY");
         resultMap.put("mch_id","1543277861");
         resultMap.put("cash_fee","1");
         resultMap.put("out_trade_no","1573562692860xxx");
         resultMap.put("appid","wxa23d9815d991b55b");
         resultMap.put("total_fee","1");
         resultMap.put("trade_type","NATIVE");
         resultMap.put("result_code","SUCCESS");
         resultMap.put("time_end","20191112204516");
         resultMap.put("is_subscribe","N");
         resultMap.put("return_code","SUCCESS");
//         
         
         
         
//         resultMap.put("nonce_str", "U1x9DLSHjnQXy2hZwwQlEnkca0u6NQe4"); 
//           resultMap.put("sign", "AC1AE10D976128DB39DAF64ACAC9F3F829EC9CA961E46728F4DB200A92EF0C81"); 
//           resultMap.put("fee_type", "CNY"); 
//           resultMap.put("body", "腾讯充值中心-QQ会员充值"); 
//           resultMap.put("notify_url", "http://store.91changqi.com/restaurant/pay/result"); 
//           resultMap.put("mch_id", "1543277861"); 
//           resultMap.put("spbill_create_ip", "61.140.47.21"); 
//           resultMap.put("out_trade_no", "1573562692860xxx"); 
//           resultMap.put("total_fee", "1"); 
//           resultMap.put("product_id", "12"); 
//           resultMap.put("appid", "wxa23d9815d991b55b"); 
//           resultMap.put("trade_type", "NATIVE"); 
//           resultMap.put("sign_type", "HMAC-SHA256"); 

//		System.out.println( wxpay.isResponseSignatureValid(resultMap));
//         System.out.println(FinalValue.SocketType.CASHIER_FRONT);
         Map<String, Object> m = new HashMap<String, Object>();
         m.put("code", "1321653");
         System.out.println("店铺id："+m.get("code"));
	 }
	 
	 /**
	  * 支付结果通知
	  * @param return_code
	  * @param return_msg
	  * @throws Exception
	  */
//	  @ApiOperation(value = "微信支付|支付回调接口", httpMethod = "POST", notes = "该链接是通过【统一下单API】中提交的参数notify_url设置，如果链接无法访问，商户将无法接收到微信通知。")
	    /**
	     * 返回成功xml
	     */
	    private String resSuccessXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";

	    /**
	     * 返回失败xml
	     */
	    private String resFailXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml>";
	    @GetMapping("/result_get")
		public void result_get() throws Exception {
	    	System.out.println("PayController.result_get()");
	    	Map<String, String> map = new HashMap<String, String>();
	    	String result = HttpUtils.doPost("http://localhost:8080/restaurant/pay/result_post", map);
	        System.out.println("result:"+result);
	    }
	    @PostMapping("/result_post")
		public String result_post(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    	System.out.println("PayController.result_post()>>>>>>");
	        String resXml = "";
	        InputStream inStream;
	        try {

	            inStream = request.getInputStream();
	            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024];
	            int len = 0;
	            while ((len = inStream.read(buffer)) != -1) {
	                outSteam.write(buffer, 0, len);
	            }

	            WXPayUtil.getLogger().info("wxnotify:微信支付----start----");

	            // 获取微信调用我们notify_url的返回信息
	            String result = new String(outSteam.toByteArray(), "utf-8");
	            WXPayUtil.getLogger().info("wxnotify:微信支付----result----=" + result);
	            // 关闭流
	            outSteam.close();
	            inStream.close();
	            return result;
	        }catch (Exception e) {
				// TODO: handle exception
			}
	        
	        return "";
	    }
	    /**
	     * 结果通知
	     * 
	     * @param request 接收微信支付结果通知
	     * 
	     * @return 返回微信规定的格式，字符串。
	     * @throws Exception
	     */
	    @RequestMapping("/result")
		public String payResult(HttpServletRequest request) throws Exception {
	    	System.out.println("PayController.payResult()");
	    	
	        String resXml = "";
	        InputStream inStream;
	        try {

	            inStream = request.getInputStream();
	            String queryString = request.getQueryString();
	            System.out.println("queryStrign:"+queryString);
	            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024];
	            int len = 0;
	            while ((len = inStream.read(buffer)) != -1) {
	                outSteam.write(buffer, 0, len);
	            }

	            WXPayUtil.getLogger().info("wxnotify:微信支付----start----");

	            // 获取微信调用我们notify_url的返回信息
	            String result = new String(outSteam.toByteArray(), "utf-8");
	            WXPayUtil.getLogger().info("wxnotify:微信支付----result----=" + result);

	            // 关闭流
	            outSteam.close();
	            inStream.close();

	            // xml转换为map
	            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
	            System.out.println("PayController.payResult():resultMap:"+resultMap);
	            boolean isSuccess = false;
	            WxPayConfig config = new WxPayConfig();
	            WXPay wxpay = new WXPay(config);

	            if (WXPayConstants.SUCCESS.equalsIgnoreCase(resultMap.get("result_code"))) {// 支付成功！
	               if( wxpay.isResponseSignatureValid(resultMap) ) {//校验签名
	            	   System.out.println("validSign  ok ................");
	                    // 订单处理 操作 orderconroller 的回写操作?
	                    WXPayUtil.getLogger().info("wxnotify:微信支付----验证签名成功");
	                    // 付款记录修改 & 记录付款日志	

	    	            // 回调方法，处理业务 - 修改订单状态
	    	            WXPayUtil.getLogger().info("wxnotify:微信支付回调：修改的订单===>" + resultMap.get("out_trade_no"));
	    	            int updateResult = 1;//更新订单为已支付。
	    	            if (updateResult > 0) {
	    	                WXPayUtil.getLogger().info("update success;;;;wxnotify:微信支付回调：修改订单支付状态成功");
	    	            } else {
	    	                WXPayUtil.getLogger().error("update fial wxnotify:微信支付回调：修改订单支付状态失败");
	    	            }
	                    // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
	                    resXml = resSuccessXml;
	                    isSuccess = true;

	                }
//	                if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
//	                	
//	                } 
	               
	               
	               else {
	                	System.out.println("validSign ................ not");
	                    WXPayUtil.getLogger().error("wxnotify:微信支付----判断签名错误");
	                }

	            } else {// 支付失败！！！！
	                WXPayUtil.getLogger().error("wxnotify:支付失败,错误信息：" + resultMap.get("err_code"));
	                resXml = resFailXml;
	            }

	          
	            
	        } catch (Exception e) {
	            WXPayUtil.getLogger().error("wxnotify:支付回调发布异常：", e);
	        }
//	        } finally {
//	            try {
//	            	response.setContentType("text/xml");
//	            	System.out.println("通知微信，已收到支付通知！！"+resXml);
//	            	
//	                // 处理业务完毕
//	               // response.getWriter().write(resXml);
//	            } catch (IOException e) {
//	                WXPayUtil.getLogger().error("wxnotify:支付回调发布异常:out：", e);
//	            }
//	        }
//	            resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
	        return resXml;
		}
	    
	    
}

//package com.cashier.back;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.socket.TextMessage;
//
//import com.cashier.common.DBValue;
//import com.cashier.common.FinalValue;
//import com.cashier.config.WxPayConfig;
//import com.cashier.pojo.Criteria;
//import com.cashier.pojo.InsertParams;
//import com.cashier.pojo.SelectParams;
//import com.cashier.pojo.UpdateParams;
//import com.cashier.pojo.Where;
//import com.cashier.pojo.vo.PayViewResult;
//import com.cashier.service.BaseService;
//import com.cashier.service.OrderService;
//import com.cashier.service.PayService;
//import com.cashier.service.impl.WebSocketServiceImpl;
//import com.cashier.utils.ParamsUtils;
//import com.cashier.utils.PayUtils;
//import com.cashier.wxpay.WXPay;
//import com.cashier.wxpay.WXPayConstants;
//import com.cashier.wxpay.WXPayUtil;
//import com.google.gson.Gson;
//
//import common.WeResult;
//import utils.AppUtil;
//
///**
// * <p>OrderController: 订单controller </p>  
// * <p>Company: www.91changqi.com</p>  
// * @author liurenwei
// * @Date 2019年10月14日
// * @version 1.0  
// */
//@RestController
//@RequestMapping("/order")
//public class OrderController_2 extends BaseController {
//
//	@Autowired
//	private OrderService orderService;
//
//	@Autowired
//	private PrinterController printerController;
//	
//	@Autowired
//	private WebSocketServiceImpl webSocketServiceImpl;
//	@Autowired
//	private BaseService baseService;
//	@Autowired
//	private PayService  payService;
//	
//	private static final String PAY_SUCCESS_LOGO = "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=957181243,2914366239&fm=26&gp=0.jpg";
//	private static final String PAY_FAIL_LOGO = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573658721608&di=27ddc56f2dc51f660906deb8165d4733&imgtype=0&src=http%3A%2F%2Fali1.rabbitpre.com%2F8e57c311-f247-44e4-8c6f-a1bfe704edf6.png";
//	   /**
//     * 返回成功xml
//     */
//    private String resSuccessXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
//
//    /**
//     * 返回失败xml
//     */
//    private String resFailXml = "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[报文为空]]></return_msg></xml>";
//	/**
//	 * 添加订单
//	 * @param params
//	 * [      
//				{ "tableName": "cashier_order_master", "cols":"total_money,address","values":[10000,"广州番禺。。。"]},
//			    { "tableName": "cashier_order_detail", "cols":"item_id,title","values":[[1,"这个是商品1  "],[2,"这个是商品2...  "]]}
//		]
//	 * @return 返回订单id
//	 */
////	@PostMapping("/add")
////	public WeResult add(@RequestBody List<InsertParams> params) {
////		try {
////			System.out.println("OrderController.add():" + params);
////			long  add = orderService.add(params);
////			return WeResult.result(200, add);
////		} catch (Exception e) {
////			e.printStackTrace();
////			return WeResult.result(500,e.getMessage());
////		}
////	}
//	 Long orderId = null;// AppUtil.getUniqueTimestrap(4);
//	 String pay_money = null;// 线上要替换掉
//	 Map<String , Boolean> payMap = new HashMap<String, Boolean>();
//	@RequestMapping("/payresult_")
//	public void result(String shopId) {
//		if( shopId == null ) {
//			shopId = "1561371857997297373";
//		}
//		System.out.println("shopId:" + shopId+"orderId:"+orderId);
//		
//		ArrayList<Criteria> criterias = new Where().name("id").eq("=",orderId).and().name("shop_id").eq("=", shopId).criterias();
//		Map<String, Object> one = baseService.selectOne(ParamsUtils.converSelect(DBValue.CASHIER_ORDER_MASTER, "*", criterias));
//		 String trade_no = String.valueOf( one.get("trade_no"));
//	    System.out.println("订单支付成功："+one);
//	    String cols = "pay_status,pay_time,transaction_no,already_money,onecode";
//	    one.put("pay_status", one.get("pay_status"));// 改为已支付
//	   long alreayMoney =  Long.parseLong( pay_money ) + Long.parseLong(String.valueOf( one.get("already_money")) );
//	   // 新订单是待支付
//	    if( Long.parseLong( String.valueOf( one.get("reality_money")) ) == alreayMoney ) {
//	    	one.put("pay_status", 1);
//	    }else if( Long.parseLong( String.valueOf( one.get("reality_money")) ) > alreayMoney) {// 支付中
//	    	one.put("pay_status", 3);
//	    }
//	    one.put("pay_time", new Date());// 改为已支付
//	    one.put("transaction_no", AppUtil.getRandomOnNumber(8));// 支付交易号，由第三方支付返回
//	    one.put("already_money", alreayMoney);// 支付交易号，由第三方支付返回
//	    one.put("onecode", Long.parseLong( pay_money ) + Long.parseLong(  String.valueOf( one.get("onecode"))) );
//	    System.out.println("修改后的one："+one);
//	    baseService.update(ParamsUtils.converUpdate(DBValue.CASHIER_ORDER_MASTER,one,cols ,criterias));
//		// 支付成功，更新订单
//		PayViewResult payResult = new PayViewResult();//new PayViewResult("", "", "已支付", PAY_SUCCESS_LOGO, "ok",one);
//		payResult.setPay_code(2);// 支付成功
//		payResult.setPay_mode(2);// 一码付
//		payResult.setMessage("已支付");
//		payResult.setData(one);
//		payResult.setStatus(1);
//		payResult.setId(Long.parseLong( String.valueOf ( one.get("id") ) ));
//		JSONObject json = new JSONObject(payResult);
//		webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_FRONT_PREFIX + shopId, new TextMessage(json.toString()));
//		webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_HOME_PREFIX + shopId, new TextMessage(json.toString()));
//		
//	}
//	 @RequestMapping("/payresult")
//		public String payResult(HttpServletRequest request, String shopId) throws Exception {
//	    	System.out.println("PayController.payResult()");
//	    	if( shopId == null ) {
//				shopId = "1561371857997297373";
//			}
//	        String resXml = "";
//	        InputStream inStream;
//	        try {
//
//	            inStream = request.getInputStream();
//	            String queryString = request.getQueryString();
//	            System.out.println("queryStrign:"+queryString);
//	            ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
//	            byte[] buffer = new byte[1024];
//	            int len = 0;
//	            while ((len = inStream.read(buffer)) != -1) {
//	                outSteam.write(buffer, 0, len);
//	            }
//
//	            WXPayUtil.getLogger().info("wxnotify:微信支付----start----");
//
//	            // 获取微信调用我们notify_url的返回信息
//	            String result = new String(outSteam.toByteArray(), "utf-8");
//	            WXPayUtil.getLogger().info("wxnotify:微信支付----result----=" + result);
//
//	            // 关闭流
//	            outSteam.close();
//	            inStream.close();
//
//	            // xml转换为map
//	            Map<String, String> resultMap = WXPayUtil.xmlToMap(result);
//	            System.out.println("PayController.payResult():resultMap:"+resultMap);
//	            boolean isSuccess = false;
//	            WxPayConfig config = new WxPayConfig();
//	            WXPay wxpay = new WXPay(config);
//	            
//	            if (WXPayConstants.SUCCESS.equalsIgnoreCase(resultMap.get("result_code"))) {// 支付成功！
//	               if( wxpay.isResponseSignatureValid(resultMap) ) {//校验签名
//	            	   System.out.println("validSign  ok ................");
//	                    // 订单处理 操作 orderconroller 的回写操作?
//	                    WXPayUtil.getLogger().info("wxnotify:微信支付----验证签名成功");
//	                    // 付款记录修改 & 记录付款日志	
//	                    String trade_no = resultMap.get("out_trade_no");
//	            		ArrayList<Criteria> criterias = new Where().name("trade_no").eq("=",trade_no).criterias();
//	            		Map<String, Object> one = baseService.selectOne(ParamsUtils.converSelect(DBValue.CASHIER_ORDER_MASTER, "*", criterias));
//	            		if( one == null ) {}
//	            		 if( one.get("pay_ok") == null || "".equals( one.get("pay_ok"))) {// 未支付
//	            			   try {
//	            				   System.out.println("订单支付成功："+one);
//		            			    String cols = "pay_status,pay_time,transaction_no,already_money,pay_ok,onecode";
//		            			    one.put("pay_status", one.get("pay_status"));// 改为已支付
//		            			   long alreayMoney =  Long.parseLong( pay_money ) + Long.parseLong(String.valueOf( one.get("already_money")) );
//		            			    if( Long.parseLong( String.valueOf( one.get("total_money")) ) == alreayMoney ) {
//		            			    	one.put("pay_status", 1);
//		            			    }
//		            			    one.put("pay_ok", trade_no);
//		            			    one.put("pay_time", new Date());// 改为已支付
//		            			    one.put("transaction_no", AppUtil.getRandomOnNumber(8));// 支付交易号，由第三方支付返回
//		            			    one.put("already_money", alreayMoney);// 支付交易号，由第三方支付返回
//		            			    one.put("onecode", Long.parseLong( pay_money ) + Long.parseLong( String.valueOf( one.get("onecode"))) );
//		            			    System.out.println("修改后的one："+one);
//		            			    // 新订单是待支付
//		            			    if( Long.parseLong( String.valueOf( one.get("reality_money")) ) == alreayMoney ) {
//		            			    	one.put("pay_status", 1);
//		            			    }else if( Long.parseLong( String.valueOf( one.get("reality_money")) ) > alreayMoney) {// 支付中
//		            			    	one.put("pay_status", 3);
//		            			    }
//		            			    baseService.update(ParamsUtils.converUpdate(DBValue.CASHIER_ORDER_MASTER,one,cols ,criterias));
//		            			    one.put("details", orderService.selectOrderDetails(orderId));
//		            			    // 支付成功，更新订单
//		            				PayViewResult payViewResult = null;// new PayViewResult("", "", "已支付", PAY_SUCCESS_LOGO, "ok",one);
//		            				JSONObject json = new JSONObject(payViewResult);
//		            				webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_FRONT_PREFIX + shopId, new TextMessage(json.toString()));
//		            				webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_HOME_PREFIX + shopId, new TextMessage(json.toString()));
//	            			   }catch (Exception e) {
//		            				PayViewResult payViewResult = null;
//		            				JSONObject json = new JSONObject(payViewResult);
//		            				webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_FRONT_PREFIX + shopId, new TextMessage(json.toString()));
//		            				webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_HOME_PREFIX + shopId, new TextMessage(json.toString()));
//							   }
//	            		 }
//	    	            // 回调方法，处理业务 - 修改订单状态
//	    	            WXPayUtil.getLogger().info("wxnotify:微信支付回调：修改的订单===>" + resultMap.get("out_trade_no"));
//	    	            int updateResult = 1;//更新订单为已支付。
//	    	            if (updateResult > 0) {
//	    	                WXPayUtil.getLogger().info("update success;;;;wxnotify:微信支付回调：修改订单支付状态成功");
//	    	            } else {
//	    	                WXPayUtil.getLogger().error("update fial wxnotify:微信支付回调：修改订单支付状态失败");
//	    	            }
//	                    // 通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.
//	                    resXml = resSuccessXml;
//	                    isSuccess = true;
//
//	                }
////	                if (wxpay.isPayResultNotifySignatureValid(resultMap)) {
////	                	
////	                } 
//	               
//	               
//	               else {
//	                	System.out.println("validSign ................ not");
//	                    WXPayUtil.getLogger().error("wxnotify:微信支付----判断签名错误");
//	                }
//
//	            } else {// 支付失败！！！！
//	                WXPayUtil.getLogger().error("wxnotify:支付失败,错误信息：" + resultMap.get("err_code"));
//	                resXml = resFailXml;
//	            }
//
//	          
//	            
//	        } catch (Exception e) {
//	            WXPayUtil.getLogger().error("wxnotify:支付回调发布异常：", e);
//	        }
////	        } finally {
////	            try {
////	            	response.setContentType("text/xml");
////	            	System.out.println("通知微信，已收到支付通知！！"+resXml);
////	            	
////	                // 处理业务完毕
////	               // response.getWriter().write(resXml);
////	            } catch (IOException e) {
////	                WXPayUtil.getLogger().error("wxnotify:支付回调发布异常:out：", e);
////	            }
////	        }
////	            resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
//	        return resXml;
//		}
//
//	/**
//	 * 结账
//	 * @throws Exception 
//	 */
//	@PostMapping("/closeAccount")
//	public WeResult closeAccount(@RequestParam Map<String,Object> op) throws Exception {
////		return null;
//		System.out.println("OrderController.closeAccount()"+op);
//		try {
//			if( op.get("id") == null ) {
//				return WeResult.result(400,"参数有误！");
//			}
//			 orderId = Long.parseLong( op.get("id").toString() );
////			ArrayList<Criteria> criterias = new Where().name("id").eq("=",orderId).and().criterias();
////    		Map<String, Object> one = baseService.selectOne(ParamsUtils.converSelect(DBValue.CASHIER_ORDER_MASTER, "*", criterias));
//    		
//			 String shopId =  String.valueOf(op.get("shop_id"));
//			 String pay_mode = String.valueOf( op.get("pay_mode"));
//			 pay_money =  String.valueOf(op.get("pay_money"));
//			 PayViewResult payResult = new PayViewResult();
//			 payResult.setOrder_type(1);// 收款
//			 payResult.setPay_money(pay_money);//
//			 payResult.setPay_mode( Integer.parseInt( pay_mode ) ); 
//			 payResult.setStatus(1);// 表示下单成功
//			 if( "2".equals(pay_mode) ) {// 一码付
//				    String trade_no =  AppUtil.getUniqueTimestrap(4);//  String.valueOf(op.get("trade_no"));
//		    		op.put("trade_no", trade_no);
//		    		op.put("pay_ok", null);
//		    		op.put("onecode", Long.parseLong( pay_money ) + Long.parseLong(String.valueOf( op.get("onecode") )) );
//		    		int ok = baseService.update(ParamsUtils.converUpdate(DBValue.CASHIER_ORDER_MASTER, op, "trade_no,pay_ok,onecode", new Where().name("id").eq("=", orderId).criterias()));
//		    		if( ok <= 0 ) {
//		    			return WeResult.result(500,"订单不存在！");
//		    		}
//				 // 生成付款码
//				 //-----------支付，统一下单
//				  Map<String, String> data = PayUtils.getWxPayBean();
//				  data.put("out_trade_no", trade_no);
//				  data.put("total_fee", pay_money);
//				  Map<String, String> payNow = payService.payNow( data );
//				  //-----------支付，统一下单
//				  payResult.setCode_url(payNow.get("code_url"));
//				  payResult.setMessage("待支付");
//				  payResult.setPay_code(1);// 待支付
//				 // 通知
////				  payViewResult = new PayViewResult(payNow.get("code_url"),pay_money,"待支付","",null,null);
//				 System.out.println("OrderController.add():下单通知："+payResult);
//				 JSONObject json = new JSONObject(payResult);
//				 webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_FRONT_PREFIX + shopId, new TextMessage(json.toString()));
//			 }else {// 现金直接更新
//				 
//				 ArrayList<Criteria> criterias = new Where().name("id").eq("=",op.get("id")).and().name("shop_id").eq("=", shopId).criterias();
//				 Date d = new Date();
//				 op.put("update_time", d);
//				 op.put("pay_time",d);
////				 op.put("already_money", Long.parseLong(String.valueOf(op.get("already_money")))+Long.parseLong(pay_money));
//				 op.remove("pay_money");
//				 op.remove("onecode");
//				 op.put("cash", Long.parseLong( pay_money ) + Long.parseLong( String.valueOf( op.get("cash"))) );
//				 int ok = baseService.update(ParamsUtils.converUpdateOnCols(DBValue.CASHIER_ORDER_MASTER, op, criterias));
//				 if( ok <= 0 ) {
//		    			return WeResult.result(500,"订单不存在！");
//		    	 }
//				 payResult.setData(op);
//				 payResult.setMessage("已支付");
//				 
////				 payViewResult = payResult new PayViewResult("", "", "已支付", PAY_SUCCESS_LOGO, "ok",op);
//			 }
//			  return WeResult.result(200,payResult);
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			  return WeResult.result(500, "系统繁忙，请重试！");
//		}
//			
////		webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_HOME_PREFIX + shopId, new TextMessage(json.toString()));
//	  
//	}
//	@PostMapping("/add")
//	public WeResult add(@RequestBody  List<InsertParams> params) {
//		if( params != null ) {
////			 return WeResult.result(500, 500);
//			
//		}
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		System.out.println("OrderController.add():"+params);
//		long  add = 0;
//		try {
////			int i = 9  /0;
////			if( params != null ) {
////				
////				throw new RuntimeException();
////			}
////			int i = 9/0;
//			 InsertParams first = params.get(0);
//			 Map<String, String> op = first.getOtherParams(); 
//			 String shopId = String.valueOf( op.get("shop_id") );
//			 // 检查socket是否连接
//			 String[] ids = new String[] {FinalValue.SOCKET_HOME_PREFIX + shopId,FinalValue.SOCKET_FRONT_PREFIX+shopId,FinalValue.SOCKET_CHEF_PREFIX+shopId};
//			 if( !webSocketServiceImpl.existAll(ids) ) return WeResult.result(500, "环境为准备就绪，连接失败，请检查各个页面是否已打开或者刷新试试！");
//			 
//			
//			 //----------------
//			String trade_no = AppUtil.getUniqueTimestrap(4);
//			 Date d = new Date();
//			 ParamsUtils.addColumns(first, "trade_no,stream_no,update_time,pay_time", trade_no,AppUtil.getRandomOnNumber(4),d,d);
//			 add =  orderService.add(params);
//			 PayViewResult payResult = new PayViewResult();
//			if( add > 0 ) {// 下单成功
//				 Map<String, Object> selectOrderAndDetialById = null;
//				 orderId = add;
//				 payResult.setStatus(1);
//				 payResult.setId(orderId);
//				 
//			  try {
//					 int orderType = Integer.parseInt(String.valueOf(op.get("order_type")));// 1表示收款，2=挂单
//					 int payMode = Integer.parseInt(String.valueOf(op.get("pay_mode")));//1=现金 2=一码付 3=银联4=会员卡
//					 payResult.setOrder_type(orderType);
//					 payResult.setPay_mode(payMode);
//					 if( orderType == 1 && payMode == 2) {// 收款，并且使用一码付
//						
//						 // 生成付款码
//						 pay_money = op.get("pay_money");
//						 payResult.setPay_money(pay_money);
//						 //-----------支付，统一下单
//						  Map<String, String> data = PayUtils.getWxPayBean();
//						  data.put("out_trade_no", trade_no);
//						  data.put("total_fee", pay_money);
//						  Map<String, String> payNow = payService.payNow( data );
//						  if( payNow.get("code_url") == null ) {
//							  payResult.setMessage("收款码生成失败，请重试！");
//							  return WeResult.result(500, payResult);
//						  }
//						  payResult.setCode_url(payNow.get("code_url"));
//						  payResult.setMessage("待支付");
//						  payResult.setPay_code(1);// 待支付
//						  //-----------支付，统一下单
//							// 通知
////							PayViewResult payViewResult = new PayViewResult(payNow.get("code_url"),pay_money,"待支付","",null,null);
//							System.out.println("OrderController.add():下单通知："+payResult);
//							JSONObject json = new JSONObject(payResult);
//							webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_FRONT_PREFIX + shopId, new TextMessage(json.toString()));
////							webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_HOME_PREFIX + shopId, new TextMessage(json.toString()));
//							return WeResult.result(200,payResult);
//					 }
//					 selectOrderAndDetialById  = orderService.selectOrderAndDetialById(add);
//					 try {// 后厨通知
//						 Gson gson = new Gson();
//						 webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_CHEF_PREFIX + shopId, new TextMessage( gson.toJson(selectOrderAndDetialById)));
//					 }catch (Exception e) {
//						 System.out.println("OrderController.add():下单通知后厨异常："+e.getMessage()); 
//					 }
////					 if( orderType == 1 ) {
////						 payResult
////					 }
//					 return WeResult.result(200,payResult );
//					
//			  }catch (Exception e) {// 只有add>0就是下单成功。生成码失败，或者其他问题
//				  e.printStackTrace();
//				 return WeResult.result(200, payResult);
//						
//			  }
//			}
//			throw new RuntimeException("未知错误！");
//		} catch (Exception e) {
//			e.printStackTrace();
////			if( add > 0 ) {
////				return WeResult.result(200,""+add);
////			}
//			return WeResult.result(500,e.getMessage());
//		}
//	}
//
//	/**
//	 * 修改订单，移除订单，重新新增
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/update")
//	public WeResult updateOrder(@RequestBody  List<InsertParams> params) {
//		if( params != null ) {
////			return WeResult.result(400, "参数有误！");
//		}
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
//	 try {
//		System.out.println("OrderController.updateOrder()"+params);
//		if( params == null  ) {//
//			return WeResult.result(400, "参数有误！");
//		}
//		InsertParams first = params.get(0);
//		Map<String, String> op = first.getOtherParams();// 获取其他参数
//		if(op == null || op.get("id") == null || op.get("order_type") == null || "".equals(op.get("order_type").toString())) {// 更新订单，必须要有id。
//			return WeResult.result(400, "参数有误！");
//		}
//		
//		 orderId = Long.parseLong( op.get("id").toString() );
//		 pay_money = op.get("pay_money");
//		
//		String shopId = String.valueOf( op.get("shop_id") );
//		// 检查socket是否连接
//		String[] ids = new String[] {FinalValue.SOCKET_HOME_PREFIX + shopId,FinalValue.SOCKET_FRONT_PREFIX+shopId,FinalValue.SOCKET_CHEF_PREFIX+shopId};
//		if( !webSocketServiceImpl.existAll(ids) ) return WeResult.result(500, "环境为准备就绪，连接失败，请检查各个页面是否已打开或者刷新试试！");
//		
//		
//		String trade_no = AppUtil.getUniqueTimestrap(4);		
//		ParamsUtils.addColumns(first, "trade_no,update_time", trade_no,new Date());
//		int orderType = Integer.parseInt(op.get("order_type"));// 订单类型，1=收款，2挂单
//		int payMode = Integer.parseInt(String.valueOf(op.get("pay_mode")));//1=现金 2=一码付 3=银联4=会员卡
//		Long ok = null;
//		 PayViewResult payResult = new PayViewResult();
//		 payResult.setStatus(1);
//		 payResult.setId(orderId);
//		 payResult.setOrder_type(orderType);
//		 payResult.setPay_mode(payMode);
//		 payResult.setPay_money(pay_money);
//		 if(  ( orderType == 1 && payMode != 2 ) || orderType == 2 ) {// 挂单或者非一码付，直接更新返回。		 
//			 ok = orderService.updateOrder(params);
//			 return WeResult.result(200,payResult);
//		 }else {// 一码付
//			 // 生成付款码
//			 //-----------支付，统一下单
//			
//			  Map<String, String> data = PayUtils.getWxPayBean();
//			  data.put("out_trade_no", trade_no);
//			  data.put("total_fee", pay_money);
//			  Map<String, String> payNow = payService.payNow( data );
//			  if( payNow.get("code_url") == null ) {
//				  payResult.setMessage("收款码生成失败，请重试！");
//				  return WeResult.result(500, payResult);
//			  }
//			  payResult.setCode_url(payNow.get("code_url"));
//			  payResult.setMessage("待支付");
//			  payResult.setPay_code(1);// 待支付
//			  //-----------支付，统一下单
//			// 通知
////			PayViewResult payViewResult = new PayViewResult(payNow.get("code_url"),pay_money,"待支付","",null,null);
//			System.out.println("OrderController.add():下单通知："+payResult);
//			JSONObject json = new JSONObject(payResult);
//			webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_FRONT_PREFIX + shopId, new TextMessage(json.toString()));
////			webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_HOME_PREFIX + shopId, new TextMessage(json.toString()));
//			return WeResult.result(200,payResult);
//		 }
//		 
//		} catch (Exception e) {
//			e.printStackTrace();
//			return WeResult.result(500,e.getMessage());
//		}
//	}
//	
//	@DeleteMapping("/delete/{orderId}")
//	public WeResult delete(@PathVariable("orderId") Long orderId) {
//		System.out.println("OrderController.delete():" + orderId);
//		try {
////			CheckParamsUtils.check("id,has_del,user_type", orderId);
//			long  add = orderService.delete(orderId);
//			
//			return WeResult.result(200, add);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return WeResult.result(500,e.getMessage());
//		}
//		
//		
//	}
//	/**
//	 * 作废订单
//	 * 根据id
//	 */
//	@RequestMapping("/cancel")
//	public WeResult cancel(@RequestBody UpdateParams params) {
//		System.out.println("OrderController.cancel():" + params);
//		try {
////			CheckParamsUtils.check("id,has_del,user_type", orderId);
//			params.setCriterias(new Where().name("id").eq("=", params.getCols().get("id")).criterias());
//			params.setTableName(DBValue.CASHIER_ORDER_MASTER);
//			long  add = orderService.cancel(params);
//			
//			return WeResult.result(200, add);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return WeResult.result(500,e.getMessage());
//		}
//	}
//	/**
//	 * 查询订单
//	 * @return
//	 */
//	@PostMapping("/select")
//	public WeResult select(@RequestParam Map<String, String> maps) {
//		if( maps.get("shopId") == null ) {
//			return WeResult.result(400,"参数有误，shopId不能为空！！");
//		}
//		try {
//			System.out.println("OrderController.select():"+maps);
//			Where w = new Where().name("shop_id").eq("=",maps.get("shopId"));
//			if( maps.get("startTime") != null && maps.get("endTime") != null) {
//				w.groupAnd().name("create_time").eq(">=", maps.get("startTime")).and().name("create_time").eq("<", maps.get("endTime"));
//			}
//			if( maps.get("pay_status") != null ){// 支付状态，刚下单的支付状态都是2，待支付。
//				 Integer pay_status = Integer.parseInt( maps.get("pay_status").toString() );
//				 // P.p(maps, "pay_status", Integer.class);
//				 if( pay_status.intValue() != 0) {
//					 w.groupAnd().name("pay_status").eq("=", pay_status);// 时间
//				 }
//			}
//			ArrayList<Criteria> criterias = w.criterias();
//			SelectParams params = ParamsUtils.converSelect(DBValue.CASHIER_ORDER_MASTER, "*",criterias,Integer.parseInt(maps.get("currPage")),Integer.parseInt(maps.get("pageSize")),"update_time desc");
//     		List<Map<String, Object>> select = orderService.select(params);
//			return WeResult.result(200,select);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return WeResult.result(500,e.getMessage());
//		}
//	}
//	
//	/**
//	 * 查询后厨订单列表
//	 * @param maps
//	 * @return
//	 */
//	@PostMapping("/selectChef")
//	public WeResult selectChef(@RequestParam Map<String, Object> maps) {
//		if( maps.get("shopId") == null ) {
//			return WeResult.result(400,"参数有误，shopId不能为空！！");
//		}
//		try {
//			System.out.println("OrderController.selectChef():"+maps);
//			Where w = new Where().name("shop_id").eq("=",maps.get("shopId"));
//			if( maps.get("startTime") != null && maps.get("endTime") != null) {
//				w.groupAnd().name("create_time").eq(">=", maps.get("startTime")).and().name("create_time").eq("<", maps.get("endTime"));
//			}
////			if( maps.get("pay_status") != null ){// 支付状态，刚下单的支付状态都是2，待支付。
////				 Integer pay_status = Integer.parseInt( maps.get("pay_status").toString() );
////				 // P.p(maps, "pay_status", Integer.class);
////				 if( pay_status.intValue() != 0) {
////					 w.groupAnd().name("pay_status").eq("=", pay_status);// 时间
////				 }
////			}
////			ArrayList<Criteria> criterias = w.criterias();
////			SelectParams params = ParamsUtils.converSelect(DBValue.CASHIER_ORDER_MASTER, "*",criterias,Integer.parseInt((String)maps.get("currPage")),Integer.parseInt((String)maps.get("pageSize")),"create_time desc");
//			
//     		List<Map<String, Object>> select = orderService.selectChefs(maps);
//			return WeResult.result(200,select);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return WeResult.result(500,e.getMessage());
//		}
//	}
//	/**
//	 * 根据店铺id查询门店订单
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping("/select/{id}")
//	public WeResult select(@PathVariable("id") Long shopId) {
//		try {
//			System.out.println("OrderController.select()>>>shopId:"+shopId);
//			Map<String, Object> selectOrderAndDetialById = orderService.selectOrderAndDetialById(shopId);
//			return WeResult.result(200,selectOrderAndDetialById);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return WeResult.result(500,e.getMessage());
//		}
//	}
//	/**
//	 * 查询订单详情
//	 * @param shopId
//	 * @return
//	 */
//	@RequestMapping("/selectDetail/{id}")
//	public WeResult selectDetail(@PathVariable("id") Long orderId) {
//		try {
//			System.out.println("OrderController.select()>>>shopId:"+orderId);
//			
//			List<Map<String, Object>> selectOrderDetails = orderService.selectOrderDetails(orderId);
//			return WeResult.result(200,selectOrderDetails);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return WeResult.result(500,e.getMessage());
//		}
//	}
//
//	
//	private String name = "a";
//	/**
//	 * 
//	 */
//	public OrderController_2() {
//		this.name = "张三";
//	}
//	{
//		name = "李四";
//	}
//	public static void main(String[] args) {
//		OrderController_2 order = new OrderController_2();
//		System.out.println(order.name);
////		List<InsertParams> params = new ArrayList<InsertParams>();
////		InsertParams p = new InsertParams();
////		p.setTableName("cashier_order_master");
////		params.add(p);
////		Gson gson = new Gson();
////		String json = gson.toJson(params);
////		System.out.println("json:" + json);
////		PayViewResult payViewResult = new PayViewResult("wenxin://xxx?gr","554.44","待支付","",null);
////		JSONObject json = new JSONObject(payViewResult);
////		System.out.println(json.toString());\
//// 	 System.out.println( 	Integer.parseInt( "100" ) + Integer.parseInt("1200")); 
//		Map<String, Object > map = new HashMap<String, Object>();
//		map.put("1", "2");
//		map.put("3", "44444");
//		PayViewResult payResult = new PayViewResult();
//		payResult.setData(map);
//		map.remove("1");
//		System.out.println(payResult);
//		
//	}
//	
//	
//}

package com.cashier.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cashier.common.FinalValue;
import com.cashier.config.CashierConfig;
import com.cashier.pojo.IndexParams;
import com.cashier.service.IndexService;
import com.cashier.service.impl.WebSocketServiceImpl;
import com.cashier.utils.CookieUtils;

import common.WeResult;

/**
 * <p>IndexController: 首页controller</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
@RequestMapping("/index")
@RestController
public class IndexController {

	@Autowired
	private IndexService indexService;
	@Autowired
	private WebSocketServiceImpl webSocketServiceImpl;
	/**
	 * 首页的controller，加载商品、商品类目、打印机列表
	 * @param shopId 店铺id
	 * @return
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	@RequestMapping("shop")
	public WeResult  home(@RequestParam String id,HttpServletRequest request,HttpServletResponse response) throws Exception {
//		String shopId = CookieUtils.getValue(request, CashierConfig.cashier_token_name);
		String shopId = id;
		System.out.println("IndexController.home()shopId:"+shopId);
//		Thread.sleep(5000);
		if( shopId == null ) {
//			response.sendRedirect(CashierConfig.shopLogin);
			return WeResult.result(500, "请先登录！");
		}
		IndexParams params = new IndexParams();
		params.setShopId(shopId);
		Map<String, Object> map = indexService.index(params);
		if( map != null ) {
			Map<String, Map<String, Object>> loginInfo = new HashMap<String, Map<String, Object>>();// UserController.loginInfo.get(shopId);
			Map<String, Object> seller = new HashMap<String, Object>();
			Map<String, Object> user = new HashMap<String, Object>();
			seller.put("store_name","拾佳客酥披萨厦滘店");
			seller.put("store_number",shopId);
			user.put("name", "周鹏");
			user.put("id", 5);
			loginInfo.put("seller", seller);
			loginInfo.put("user", user);
			map.put("loginInfo", loginInfo);
			return WeResult.result(200, map);
		}
		return null;
	}
	
	/**
	 * 检测环境
	 * @param shopId
	 * @return
	 */
	@RequestMapping("/checkEnv/{id}")
	public WeResult  checkEnv(@PathVariable("id") String shopId) {
		if( shopId == null ) {
			return WeResult.result(400, "参数有误！");
		}
		System.out.println("IndexController.checkEnv()" + shopId);
		try {
			boolean qtym = webSocketServiceImpl.exist(FinalValue.SOCKET_FRONT_PREFIX+shopId);// 前台页面
			boolean syym = webSocketServiceImpl.exist(FinalValue.SOCKET_HOME_PREFIX + shopId);// 收银首页
			boolean hcym = webSocketServiceImpl.exist(FinalValue.SOCKET_CHEF_PREFIX + shopId);// 后厨页
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("qtym",qtym);
			map.put("syym",syym);
			map.put("hcym",hcym);
			return WeResult.result(200, map);
		} catch (Exception e) {
			e.printStackTrace();
			return WeResult.result(500, e.getMessage());
		}
	}
	
	
	
	
	
}

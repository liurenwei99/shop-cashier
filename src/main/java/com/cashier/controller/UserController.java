package com.cashier.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cashier.common.DBValue;
import com.cashier.config.CashierConfig;
import com.cashier.pojo.Where;
import com.cashier.service.BaseService;
import com.cashier.utils.ParamsUtils;

import common.WeResult;

/**
 * <p>UserController: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2020年4月1日
 * @version 1.0  
 */
@RequestMapping("user")
@RestController
public class UserController {

	@Autowired
	private BaseService baseService;
	public static final Map<String,Map<String, Map<String, Object>>> loginInfo = new ConcurrentHashMap<String, Map<String,Map<String, Object>>>();
	/**
	 * @param storeId
	 * @param userId
	 * @return
	 */
	@RequestMapping("login")
	public WeResult login(String storeId,String userId,HttpServletResponse response) {
		System.out.println("UserController.login():用户登录：");
		if( storeId==null || userId == null) {
			return WeResult.result(400, "登录失败，参数有误");
		}
		Map<String, Object> sellerInfo = baseService.selectOne(ParamsUtils.converSelect(DBValue.SELLER_INFO, "*", new Where().name("store_number").eq("=", storeId).criterias()));
		Map<String, Object> userInfo = baseService.selectOne(ParamsUtils.converSelect(DBValue.ADMINISTRATORS, "*", new Where().name("id").eq("=",userId).criterias()));
		System.out.println("商家信息："+sellerInfo);
		System.out.println("用户信息："+userInfo);
		
		if( sellerInfo != null && userInfo != null ) {
			Map<String, Map<String, Object>> seller = new HashMap<String, Map<String, Object>> ();
			seller.put("seller", sellerInfo);
			seller.put("user", userInfo);
			Cookie cookie = new Cookie(CashierConfig.cashier_token_name,storeId);
			response.addCookie(cookie);
			loginInfo.put(sellerInfo.get("store_number").toString(),seller );
			
			return WeResult.result(200, "ok");
		}
		return WeResult.result(500, "登录失败，门店或用户信息不可用！");
	}
}

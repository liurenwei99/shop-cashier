package com.cashier.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cashier.config.CashierConfig;
import com.cashier.utils.CookieUtils;
import com.myutil.common.WeResult;

/**
 * <p>RouteController: 页面路由控制器 </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月28日
 * @version 1.0  
 */
@RequestMapping("/")
@Controller
public class RouteController {

	/**
	 * 收银首页
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/")
	public WeResult  goHome(@RequestParam String id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("IndexController.goHome().,可能死循环。。。。"+id);
		if( id == null ) {
			response.sendRedirect(CashierConfig.shopLogin);
			return null;
		}
		Map<String, Map<String, Object>> loginInfo = UserController.loginInfo.get(id);
		if( loginInfo == null ) {
//			return WeResult.result(500, "请先登录！");
			response.sendRedirect(CashierConfig.shopLogin);
			return null;
		}
		try {
			Cookie cookie = new Cookie(CashierConfig.cashier_token_name,id);
			response.addCookie(cookie);
			request.getRequestDispatcher("/index.html").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/goCashier")
	public WeResult  setCookie(@RequestParam String id,HttpServletRequest request,HttpServletResponse response) throws Exception {
		System.out.println("RouteController.setCookie():"+id);
		// 看看有没有登录
		if( UserController.loginInfo.get(id) != null ) {
			response.addCookie(new Cookie(CashierConfig.cashier_token_name, id));
			response.addCookie(new Cookie("abcd", "abcd"));
			response.sendRedirect("http://localhost:8001/cashier");
		}else {// 登录
			response.sendRedirect("http://localhost:8080/seller/loginList");
		}
		return null;
	}
	/**
	 * 收银订单页
	 * @param reques
	 * @param response
	 * @return
	 */
	@RequestMapping("/order")
	public WeResult  order(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("RouteController.order()");
		try {
			String id = CookieUtils.getValue(request, CashierConfig.cashier_token_name);
			System.out.println("IndexController.goHome().,可能死循环。。。。"+id);
			if( id == null ) {
				response.sendRedirect(CashierConfig.shopLogin);
				return null;
			}
			Map<String, Map<String, Object>> loginInfo = UserController.loginInfo.get(id);
			if( loginInfo == null ) {
//				return WeResult.result(500, "请先登录！");
				response.sendRedirect(CashierConfig.shopLogin);
				return null;
			}
			request.getRequestDispatcher("/index.html").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 收银设置
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/setting")
	public WeResult  setting(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("RouteController.setting()");
		try {
			String id = CookieUtils.getValue(request, CashierConfig.cashier_token_name);
			System.out.println("IndexController.goHome().,可能死循环。。。。"+id);
			if( id == null ) {
				response.sendRedirect(CashierConfig.shopLogin);
				return null;
			}
			Map<String, Map<String, Object>> loginInfo = UserController.loginInfo.get(id);
			if( loginInfo == null ) {
//				return WeResult.result(500, "请先登录！");
				response.sendRedirect(CashierConfig.shopLogin);
				return null;
			}
			request.getRequestDispatcher("/index.html").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/chef")
	public WeResult  chef(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("RouteController.chef()");
		try {
			String id = CookieUtils.getValue(request, CashierConfig.cashier_token_name);
			System.out.println("IndexController.goHome().,可能死循环。。。。"+id);
			if( id == null ) {
				response.sendRedirect(CashierConfig.shopLogin);
				return null;
			}
			Map<String, Map<String, Object>> loginInfo = UserController.loginInfo.get(id);
			if( loginInfo == null ) {
//				return WeResult.result(500, "请先登录！");
				response.sendRedirect(CashierConfig.shopLogin);
				return null;
			}
			request.getRequestDispatcher("/index.html").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	@RequestMapping("/front")
	public WeResult  front(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("RouteController.front()");
		try {
			String id = CookieUtils.getValue(request, CashierConfig.cashier_token_name);
			System.out.println("IndexController.goHome().,可能死循环。。。。"+id);
			if( id == null ) {
				response.sendRedirect(CashierConfig.shopLogin);
				return null;
			}
			Map<String, Map<String, Object>> loginInfo = UserController.loginInfo.get(id);
			if( loginInfo == null ) {
//				return WeResult.result(500, "请先登录！");
				response.sendRedirect(CashierConfig.shopLogin);
				return null;
			}
			request.getRequestDispatcher("/index.html").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

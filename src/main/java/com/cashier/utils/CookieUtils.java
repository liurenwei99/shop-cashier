package com.cashier.utils;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.cashier.config.CashierConfig;
import com.cashier.controller.UserController;

/**
 * <p>CookieUtils: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2020年4月1日
 * @version 1.0  
 */
public class CookieUtils {

	/**
	 * 根据name获取cookie值。
	 * @param request
	 * @param name
	 * @return
	 * 如果有返回值，没有返回null
	 */
	public static String getValue(HttpServletRequest request,String name) {
		Cookie[] cookies = request.getCookies();
		if( cookies != null ) {
			for (Cookie cookie : cookies) {
				System.out.println("cookie:"+cookie.getName()+"="+cookie.getValue());
				if( cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
}

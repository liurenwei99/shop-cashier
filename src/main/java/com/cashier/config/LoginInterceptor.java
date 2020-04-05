package com.cashier.config;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.cookie.CookieAttributeHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cashier.controller.UserController;
import com.cashier.utils.CookieUtils;

/**
 * <p>Interceptor: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2020年4月1日
 * @version 1.0  
 */
@Component
public class LoginInterceptor  implements HandlerInterceptor{
	/**
	 * 任何请求都会经过这里。
	 * 在WebAppConfig中设置的拦截规则，都在这里拦截
	 * true:表示不拦截请求。往下执行controller
	 * false：表示拦截请求.不执行controller，直接返回。
	 * request.getRequestURI()：http://localhost:8889/backcloud/user/login，uri=/backcloud/user/login
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		System.out.println("LoginInterceptor.preHandle()URI："+request.getRequestURI());
//		if( request != null ) {			
//			return true;
//		}
//		Cookie[] cookies = request.getCookies();
//		if( cookies != null ) {
//			for (Cookie cookie : cookies) {
//				System.out.println("cookie:"+cookie.getName()+"="+cookie.getValue());
//				if( cookie.getName().equals("cashier_token")) {
//					String shopId = cookie.getValue();
//					Map<String, Map<String, Object>> info = UserController.loginInfo.get(shopId);
//					if( info == null ) {
////						response.sendRedirect("https://store.gzsttech.com/seller/loginList");
//						response.sendRedirect(PathConfig.shopLogin);
//						return false;
//					}
//				}
//			}
//		}
		String value = CookieUtils.getValue(request, CashierConfig.cashier_token_name);
		if("/cashier/user/login".equals(request.getRequestURI()) || "/cashier/user/login/".equals(request.getRequestURI())
				|| "/cashier/goCashier".equals(request.getRequestURI() )|| "/cashier/goCashier/".equals(request.getRequestURI())
				) {
				
			return true;
		}
		System.out.println("登录拦截：：：：：：：：value:"+value);
		if( value == null ) {
			response.sendRedirect(CashierConfig.shopLogin);
			return false;
		}
		Map<String, Map<String, Object>> info = UserController.loginInfo.get(value);
		if( info == null ) {
			response.sendRedirect(CashierConfig.shopLogin);
			return false;
		}
//		if (request.getRequestURI().equals("/mongo/attencedetail/selectAll")
//				|| request.getRequestURI().equals("/backcloud/user/login")) {
//			System.out.println("You get a http request");
//			return false;
//		}
		return true;
	}
  
   /**
     * 该方法将在Controller执行之后，返回视图之前执行，modelAndView表示请求Controller处理之后返回的Model和View对象，所以可以在
     * 这个方法中修改modelAndView的属性，从而达到改变返回的模型和视图的效果。
     */
	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		System.out.println("LoginInterceptor.postHandle()");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
		System.out.println("LoginInterceptor.afterCompletion()");
	}
	public String getRemortIP(HttpServletRequest request) {
		System.out.println("LoginInterceptor.getRemortIP()");
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
}
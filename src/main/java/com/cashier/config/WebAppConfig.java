package com.cashier.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * <p>WebAppConfig: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2020年4月1日
 * @version 1.0  
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter   {
	  @Autowired
	    private LoginInterceptor loginInterceptor;
	 
	  // 这个方法是用来配置静态资源的，比如html，js，css，等等
	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
		  // 添加资源映射规则。
//		  registry.addResourceHandler("/admin/**").addResourceLocations("/WEB-INF/admin/");
		  System.out.println("WebAppConfig.addResourceHandlers()");
	  }
	  // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
	  @Override
	  public void addInterceptors(InterceptorRegistry registry) {
		  	// 拦截所有请求
	        registry.addInterceptor(loginInterceptor).addPathPatterns("/**")
	        // 排除登录、注册。
	        .excludePathPatterns("/login", "/register");
		  System.out.println("WebAppConfig.addInterceptors()");
	  }

}

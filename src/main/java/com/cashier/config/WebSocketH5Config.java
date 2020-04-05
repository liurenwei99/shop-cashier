package com.cashier.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.cashier.controller.MyHandler;
import com.cashier.service.impl.WebSocketServiceImpl;

/**
 * <p>WebSocketConfig: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年11月9日
 * @version 1.0  
 */

//实现接口来配置Websocket请求的路径和拦截器。
@Configuration
@EnableWebSocket
public class WebSocketH5Config implements WebSocketConfigurer{

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    //handler是webSocket的核心，配置入口
    registry.addHandler(new WebSocketServiceImpl(), "/cashier/{ID}").setAllowedOrigins("*").addInterceptors(new WebSocketInterceptor());
       
  }

   
}
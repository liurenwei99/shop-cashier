package com.cashier.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * <p>WebSocketController: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年11月9日
 * @version 1.0  
 */

@Service
public class MyHandler implements WebSocketHandler {

	   
	  //在线用户列表
	  private static final Map<String, WebSocketSession> users;
	  
	   
	  static {
	    users = new HashMap<>();
	  }
	  //新增socket
	  @Override
	  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	     System.out.println("成功建立连接");
	     String ID = session.getUri().toString().split("ID=")[1];
	     System.out.println(ID);
	     if (ID != null) {
	       users.put(ID, session);
	       session.sendMessage(new TextMessage("成功建立socket连接"));
	       System.out.println(session);
	     }
	     System.out.println("当前在线人数："+users.size()+"分别是："+users);
	  }
	  
	  //接收socket信息
	   @Override
	    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
	    try{
	    	System.out.println("webSocketMessage.getPayload():"+webSocketMessage.getPayload());
	        JSONObject jsonobject = new JSONObject(webSocketMessage.getPayload().toString());   
	        System.out.println(jsonobject.get("id"));
	        System.out.println(jsonobject.get("message")+":来自"+(String)webSocketSession.getAttributes().get("WEBSOCKET_USERID")+"的消息");
	        sendMessageToUser(jsonobject.get("id")+"",new TextMessage("服务器收到了，hello!"));
	     }catch(Exception e){
	      e.printStackTrace();
	     }
	         
	    }
	  
	  /**
	   * 发送信息给指定用户
	   * @param clientId
	   * @param message
	   * @return
	   */
	  public boolean sendMessageToUser(String clientId, TextMessage message) {
	    if (users.get(clientId) == null) return false;
	    WebSocketSession session = users.get(clientId);
	    System.out.println("sendMessage:" + session);
	    if (!session.isOpen()) return false;
	    try {
	      session.sendMessage(message);
	    } catch (IOException e) {
	      e.printStackTrace();
	      return false;
	    }
	    return true;
	  }
	  
	  /**
	   * 广播信息
	   * @param message
	   * @return
	   */
	  public boolean sendMessageToAllUsers(TextMessage message) {
	    boolean allSendSuccess = true;
	    Set<String> clientIds = users.keySet();
	    WebSocketSession session = null;
	    for (String clientId : clientIds) {
	      try {
	        session = users.get(clientId);
	        if (session.isOpen()) {
	          session.sendMessage(message);
	        }
	      } catch (IOException e) {
	        e.printStackTrace();
	        allSendSuccess = false;
	      }
	    }
	  
	    return allSendSuccess;
	  }
	  
	  
	  @Override
	  public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
	    if (session.isOpen()) {
	      session.close();
	    }
	    System.out.println("连接出错");
	    users.remove(getClientId(session));
	  }
	  
	  @Override
	  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	    System.out.println("连接已关闭：" + status);
	    users.remove(getClientId(session));
	  }
	  
	  @Override
	  public boolean supportsPartialMessages() {
	    return false;
	  }
	  
	  /**
	   * 获取用户标识
	   * @param session
	   * @return
	   */
	  private Integer getClientId(WebSocketSession session) {
	    try {
	      Integer clientId = (Integer) session.getAttributes().get("WEBSOCKET_USERID");
	      return clientId;
	    } catch (Exception e) {
	      return null;
	    }
	  }
	  
	  @Scheduled(fixedRate = 5000)
	  public void sendMessage() {
		  
	  }

}

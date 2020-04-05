package com.cashier.service.impl;

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

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

/**
 * <p>WebSocketController: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年11月9日
 * @version 1.0  
 */

@Service("webSocketServiceImpl")
public class WebSocketServiceImpl implements WebSocketHandler {

	   
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
//	     System.out.println(ID);
//	     if (ID != null) {
//	       users.put(ID, session);
//	       session.sendMessage(new TextMessage("成功建立socket连接"));
//	     }
	     if( ID != null  ) {
	    	 users.put(ID,session); 
	    	 session.sendMessage(new TextMessage("{\"socket_status\":\"ok\"}"));
	     }
	     System.out.println("当前在线人数："+users.size()+"\n分别是："+users);
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
	    System.out.println("连接已关闭：" + status+"\n 当前session："+session);
	    Map<String, Object> sessionAttr = session.getAttributes();
	    System.out.println("session属性："+sessionAttr);
	    users.remove(getClientId(session));
	    System.out.println("当前的连接："+users);
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
	  private String getClientId(WebSocketSession session) {
	    try {
	      return (String) session.getAttributes().get("WEBSOCKET_USERID");
	    } catch (Exception e) {
	      return null;
	    }
	  }
	  
	 
	  /**
	   * 检查某个socket是否已经连接
	   * @param clientId
	   * @return
	   */
	  public boolean exist(String clientId) {
		  if( users.get(clientId) != null ) {
			  return true;
		  }
		  return false;
	  }
	  
	  /**
	   * 检查一组socket，全部存在才返回true
	   * @param ids 连接 的时候的id集合
	   * @return
	   */
	  public boolean existAll( String[] ids) {
		  for (String id : ids) {
			  if( !exist(id) ) {
				  return false;
			  }
		  }
		  return true;
	  }

}

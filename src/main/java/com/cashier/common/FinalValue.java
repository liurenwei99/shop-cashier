package com.cashier.common;

/**
 * <p>FinalValue: 定义项目中所用的常量值</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
public final class FinalValue {
  public static final Integer PAGE_SIZE = 20;// 分页，页大小

  public enum SocketType{
	  CASHIER_HOME,// 收银首页的socket连接
	  CASHIER_FRONT// 收银前台的socket连接
  }
  
  public static final String SOCKET_HOME_PREFIX = "cashier_home_";// 收银首页socket连接前缀id
  public static final String SOCKET_FRONT_PREFIX = "cashier_front_";// 收银前台页socket连接前缀id
  public static final String SOCKET_CHEF_PREFIX = "cashier_chef_";// 后厨socket连接前缀
  
  
}

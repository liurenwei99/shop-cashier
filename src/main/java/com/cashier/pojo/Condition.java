package com.cashier.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * 如：(Condition,Condition,Condition)
 * @author Administrator
 *
 */
@Data
@ToString
public class Condition {
	private String name;
	private Object value;
	private String cond;// 下个连接条件
	private String symbol="=";// 赋值符合，如：=，!= ,< >默认是=号  还可以是in 
	private String valueType;// 只有在复杂查询条件的时候有用。自然整数代表着值是子select，并且同时代表索引
	
}

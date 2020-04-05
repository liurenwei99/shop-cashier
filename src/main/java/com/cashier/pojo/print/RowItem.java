package com.cashier.pojo.print;

import java.util.List;

import lombok.Data;

/**
 * <p>Row: 封装打印的行</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月30日
 * @version 1.0  
 */
@Data
public class RowItem {
	
	private String type;// 类型
	private String name;// 名字
	private String value;// 值
	private List<Object> heads;// 只有type为table的 时候，heads才有值
	private List<Object> values;//  表格对应的值
	
	private Integer rowX;// 水平距离
	private Integer rowY;// 纵轴距离
	
	private Integer fontSize;// 
	
	

}

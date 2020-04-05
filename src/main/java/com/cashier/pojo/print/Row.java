package com.cashier.pojo.print;

import java.util.List;

import lombok.Data;

/**
 * <p>Row: 打印的行</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月31日
 * @version 1.0  
 */
@Data
public class Row {

	private  String name;// 打印机名字
	private List<RowItem> rows;// 打印的行
	private String print_type;// 打印类型
}

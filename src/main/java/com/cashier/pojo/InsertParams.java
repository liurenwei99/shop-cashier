package com.cashier.pojo;

import java.util.List;
import java.util.Map;

import com.cashier.pojo.print.Row;

/**
 * insert语句参数
 *  { tableName: "user_", cols:"name,age,money",values:["zhangsan",33,888],"success":""};
 * @author Administrator
 *
 */
public class InsertParams extends BaseParams{
	
	private Long id=-1L;// 新增数据，是否需要返回主键  -1表示返回
	
	
	private String cols;// 添加的字段
	
	private List<Object> values;// 字段对应的值

//	private List<Row> rows;//打印

	private Map<String, String> otherParams;
	
	
	
	

	public Map<String, String> getOtherParams() {
		return otherParams;
	}

	public void setOtherParams(Map<String, String> otherParams) {
		this.otherParams = otherParams;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCols() {
		return cols;
	}

	public void setCols(String cols) {
		this.cols = cols;
	}



	

	
	
	public List<Object> getValues() {
		return values;
	}

	public void setValues(List<Object> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "InsertParams [id=" + id + ", cols=" + cols + ", values=" + values + ", otherParams=" + otherParams
				+ "]";
	}

	
	

	
}

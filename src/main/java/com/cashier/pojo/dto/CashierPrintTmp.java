package com.cashier.pojo.dto;
import lombok.*;
@Data
@ToString
public class CashierPrintTmp{

	// 主键
	private java.lang.Long id;

	// 打印样板容器的html标签id
	private java.lang.String tmpId;

	// 模板名称
	private java.lang.String name;

	// 打印的信息
	private java.lang.String text;

	// 创建时间
	private java.util.Date createTime;

	// 更新时间
	private java.util.Date updateTime;

	public String getIdDbFieldName(){
		return "id";
	}

	public String getTmpIdDbFieldName(){
		return "tmp_id";
	}

	public String getNameDbFieldName(){
		return "name";
	}

	public String getTextDbFieldName(){
		return "text";
	}

	public String getCreateTimeDbFieldName(){
		return "create_time";
	}

	public String getUpdateTimeDbFieldName(){
		return "update_time";
	}

}
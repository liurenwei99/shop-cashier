package com.cashier.pojo;

import lombok.Data;

/**
 * 基参数
 * @author Administrator
 *
 */
@Data
public class BaseParams {

	private String tableName;
	private String dbName;
	private String proId;// 项目秘钥。数据库名：基本名_proId
	
	@Override
	public String toString() {
		return "BaseParams [tableName=" + tableName + ", dbName=" + dbName + ", proId=" + proId + "]";
	}
	
	
	
	
}

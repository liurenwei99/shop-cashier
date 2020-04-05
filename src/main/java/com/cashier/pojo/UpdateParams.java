package com.cashier.pojo;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

//{ tableName: "user_ s", cols: { name: "kkk", age: 100 }, criteria: [{ conds: [{ "name": "id", value: 8, "symbol": "="}]}] };
@Data
public class UpdateParams extends BaseParams{

	private Map<String, Object> cols;// 字段键值对，比如更新：name="张三"
	private Map<String, String> otherParams;
	
	private List<Criteria> criterias;// 必须是criteria数组，兼容多个，一个也可以表示  
	public UpdateParams() {}
	public UpdateParams(String tableName,Map<String, Object> cols, List<Criteria> criterias) {
		super.setTableName(tableName);
		this.cols = cols;
		this.criterias = criterias;
	}
    

	@Override
	public String toString() {
		return "UpdateParams [cols=" + cols + ", criterias=" + criterias + "]";
	}
	
	
	
}

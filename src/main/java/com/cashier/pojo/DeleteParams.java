package com.cashier.pojo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
//{ tableName: "user_", criteria: [{ conds: [{ "name": "name", value: "aaa","cond":"and"},{"name":"age",value:27}]}] };
public class DeleteParams extends BaseParams{
	
	private List<?> criterias;// 条件集合。(name=a)  (name=a and age=2)  

	public List<?> getCriterias() {
		return criterias;
	}

	public void setCriterias(List<?> criterias) {
		this.criterias = criterias;
	}

	@Override
	public String toString() {
		return "DeleteParams [criterias=" + criterias + "]";
	}

	
	
	
	
}

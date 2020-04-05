package com.cashier.pojo;

import java.util.List;
import java.util.Map;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 查询参数实体
 * @author Administrator
 *{ tableName: "user_", qcols: "id,age,name,money", order:"money desc", page:{pageNum:1,pageSize:3},criteria: [{ conds: [{ "name": "age", value: 99,"symbol":">=","cond":"or"},{"name":"name",value:"kkk"}] }]  };
 * select id,age,name,money from user_ WHERE ( age >= ? or name = ? ) order by money desc limit ?,? 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@ToString
public class SelectParams extends BaseParams{
	
	private String qcols="*";// 表示，查询的时候返回的字段  ，null表示全部
	
	private List<Criteria> criterias;// 条件集合。(name=a)  (name=a and age=2)  
	
	private Page page = new Page();//分页信息
	
	private String order;// 查询排序 1=升序（asc） -1=降序（desc） 
	
	private Boolean  hasPage = true;// 是否分页,默认是分页的
   
	
}

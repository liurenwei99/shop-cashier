package com.cashier.pojo;

import java.util.List;

/**
 * 条件： 如：[(Condition,Condition,Condition),(Condition,Condition)]
 * 单个条件实体：{params:[{name:'id',value:33,condition:'and'},{name:'id2',value:44}]} ( id=22 and id2=44 )
 * @author Administrator
 *
 */
public class Criteria {

	private List<Condition> conds;//每个conds都是一个条件对象：[{name:'id',value:33,condition:'and'},{name:'id',value:33}]
	private String cond;// 下一个链接条件，比如：and、or
	
	
	public List<Condition> getConds() {
		return conds;
	}
	public void setConds(List<Condition> conds) {
		this.conds = conds;
	}
	public String getCond() {
		return cond;
	}
	public void setCond(String cond) {
		this.cond = cond;
	}
	@Override
	public String toString() {
		return "Criteria [conds=" + conds + ", cond=" + cond + "]";
	}
	
}





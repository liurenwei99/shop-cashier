package com.cashier.pojo;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Where: 构造where条件的实体类</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月16日
 * @version 1.0  
 */
//		where.name("(","id").eq("张三").and().name("status").notEq(2,")").and().name("a").eq("张三");
public class Where_ {
	Map<String, Object> map = new HashMap<String, Object>();
	List<String> keys = new ArrayList<String>();
	/**
	 *  List< Criteria > criteria = new  ArrayList<Criteria>();
		 Criteria c = new Criteria();
		 List<Condition> conds = new ArrayList<Condition>();
		 Condition cond = new Condition();
		 cond.setName("id");
		 cond.setValue(id);
		 conds.add(cond);
		 c.setConds(conds);
	 */
	/**
	 * 什么时候要创建Condition对象，什么时候要创建Criteria对象
	 * 当eq("")的记录是否有)括号，如果有就要创建Criteria对象。如没有就要创建Condition对象
	 * 
	 */
    public Where_() {
    	System.out.println("Where.Where()"+this);
    	map.put(this.toString(), new  ArrayList<Criteria>() );
    	// 是否创建条件对象
    	map.put(this.toString()+"_condEnd", false );// 条件组 ()  and ()
    	map.put(this.toString()+"_isCriteria", false );// 是否是 条件组 ()  and ()  )这样才是
//    	map.put(this.toString()+"_condEnd", false );// 单个条件  ( name=a)
//    	map.put(this.toString()+"_criteria", new Criteria() );
//    	map.put(this.toString()+"_conds",new  ArrayList<Criteria>() );
	}
	public Where_ name(String name) {
		Criteria criteria;
		ArrayList<Condition> conds;
		Condition cond;
		if( map.get(this.toString()+"_criteria") == null ) {
			 criteria = new Criteria();
			 map.put(this.toString()+"_criteria",criteria);
		}else {
			criteria = (Criteria) map.get(this.toString()+"_criteria");
		}
		if( map.get(this.toString()+"_conds") == null ) {
			conds = new  ArrayList<Condition>();
			 map.put(this.toString()+"_conds",conds);
		}else {
			conds = (ArrayList<Condition>) map.get(this.toString()+"_conds");
		}
//		if( map.get(this.toString()+"_cond") == null ) {
//			 cond = new Condition();
//			 map.put(this.toString()+"_cond",cond);
//		}else {
//			cond = (Condition) map.get(this.toString()+"_cond");
//		}
		if( conds.size() > 0 ) {
			cond = conds.get(conds.size()-1);
		}else {
			cond = new Condition();
		}
		
		cond.setName(name);
		conds.add(cond);
		criteria.setConds(conds);
		map.put(this.toString()+"_conds",conds);
//		map.put(this.toString()+"_cond",  cond);
		map.put(this.toString()+"_criteria",  criteria);
		 ArrayList<Criteria> criterias = (ArrayList<Criteria>) map.get(this.toString());
		 criterias.add(criteria);
		map.put(this.toString(), criterias);
		
		return this;
	}
	
	public Where_ eq(Object value) {// 执行值表示一个Condition结束
		if( map.get(this.toString()+"_criteria") == null ) {// 都执行条件了，criteria还是空，那就是不对、
			throw new RuntimeException("条件的开始不能直接使用eq方法，请先执行name试试！");
		}
		if( map.get(this.toString()+"_conds") == null ) {
			throw new RuntimeException("赋值的时候，ArrayList<Condition>条件组不能为空！");
		}
		ArrayList<Condition> conds = (ArrayList<Condition>) map.get(this.toString()+"_conds");
		if( conds.size() ==  0 ) {
			throw new RuntimeException("赋值的时候，Condition对象不能为空！");
		}
		Condition cond = conds.get(conds.size()-1);
		cond.setValue(value);
		return this;
	}
	public Where_ and() {//需要判断是Condition 还是Criteria
		if( map.get(this.toString()+"_criteria") == null ) {// 都执行条件了，criteria还是空，那就是不对、
			throw new RuntimeException("条件的开始不能直接使用eq方法，请先执行name试试！");
		}
		if( map.get(this.toString()+"_conds") == null ) {
			throw new RuntimeException("赋值的时候，ArrayList<Condition>条件组不能为空！");
		}
		ArrayList<Condition> conds = (ArrayList<Condition>) map.get(this.toString()+"_conds");
		if( conds.size() ==  0 ) {
			throw new RuntimeException("赋值的时候，Condition对象不能为空！");
		}
		boolean _isCriteria = (boolean) map.get(this.toString()+"_isCriteria");
		if( _isCriteria ) {// 条件组
			
		}else {// 单个条件  condition
			Condition cond = conds.get(conds.size()-1);
			cond.setCond("and");
			cond = new Condition();
			conds.add(cond);
//			map.put(this.toString()+"_conds",conds);
		}
		return this;
	}
	
	public ArrayList<Criteria> criterias() {
		
		return (ArrayList<Criteria>) map.get(this.toString());
	}
	
	public static void main(String[] args) {
		
		ArrayList<Criteria> ok = new Where_().name("id").eq(3).criterias();
		System.out.println(ok);
		ok = new Where_().name("id").eq(4).criterias();
		System.out.println(ok);
	}
	
	
}

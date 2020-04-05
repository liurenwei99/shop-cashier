package com.cashier.pojo;

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
public class Where {
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
    public Where() {
    	System.out.println("构建Where对象"+this);
    	map.put(this.toString(), new  ArrayList<Criteria>() );
	}
 //-----------------------------------------------------------------------------------Condition
  /**
   * 拼接字段名
   * new Where().name().实例化Where对象跟着一定是name方法
   * 条件组(Criteria)--条件(Condition)
   * 一个where条件可以包含一个或者多个Criteria对象。为了方便，where只能是Criteria数组
   * 一个Criteria包含一个或者多个Condition对象 (name = jack  ...) 和  下个条件的组连接符号  and  or...
   * criteria:[ {"conds":[{Condition对象,cond:"下个Condition连接条件"},...], cond:"下一个criteria连接条件"},{cirteria对象}]
   * @param name 字段名
   * @return 返回当前对象，方便链式调用
   */
	public Where name(String name) {
		Criteria criteria;
		ArrayList<Condition> conds;
		Condition cond;
		if( map.get(this.toString()+"_criteria") == null ) {
			 criteria = new Criteria();
		}else {
			criteria = (Criteria) map.get(this.toString()+"_criteria");
		}
		if( map.get(this.toString()+"_conds") == null ) {
			conds = new  ArrayList<Condition>();
		}else {
			conds = (ArrayList<Condition>) map.get(this.toString()+"_conds");
		}
		boolean flag = false;// 标记是否要操作新的Condition对象。只是刚开始的时候为空。多个条件的时候调用and()之后就已经创建好放到conds中，下次从conds中取出即可
		if( conds.size() > 0 ) {
			cond = conds.get(conds.size()-1);
		}else {
			cond = new Condition();
			flag = true;
		}
		
		cond.setName(name);
		if( flag )conds.add(cond);
		
		criteria.setConds(conds);
		map.put(this.toString()+"_conds",conds);
		map.put(this.toString()+"_criteria",  criteria);
		
		return this;
	}
	
	/**
	 * 比较符号。表示=的意思。
	 * 注意：只能是Condition使用。Criteria不能使用，Criteria只能使用group开头的。
	 * @param value 比较的值
	 * @param symbol 比较符号，是=，<  > 
	 * @return  返回当前对象
	 */
	public Where eq(String symbol,Object value) {// 执行值表示一个Condition结束
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
		cond.setSymbol(symbol);
		return this;
	}
	/**
	 * Condition对象直接的连接符号。and  or  ...
	 * 不能直接调用，需要调用name，然后调用eq..。如果有多个条件才可以调用。
	 * @return  返回当前对象
	 */
	public Where and() {//需要判断是Condition 还是Criteria
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
		cond.setCond("and");
		cond = new Condition();
		conds.add(cond);
		map.put(this.toString()+"_conds",conds);
		System.out.println("conds:"+conds);
		return this;
	}
	 //-----------------------------------------------------------------------------------Condition  end
	 //-----------------------------------------------------------------------------------Criteria  
	/**
	 * Criteria对象之间的连接条件，不能用于Condition对象之间的连接（使用and()..)
	 * groupXxx();表示Criteria对象 之间的连接条件，
	 * @return 返回当前对象
	 */
	public Where groupAnd() {
		 Criteria criteria = (Criteria) map.get(this.toString()+"_criteria");
		 ArrayList<Criteria> criterias = (ArrayList<Criteria>) map.get(this.toString());
		criterias.add(criteria);
		criteria.setCond("and");
		map.put(this.toString(), criterias);
		map.put(this.toString()+"_criteria",null);
		map.put(this.toString()+"_conds",null);
		return this;
	}
	/**
	 * 参考groupAnd()
	 * @return
	 */
	public Where groupOr() {
		 Criteria criteria = (Criteria) map.get(this.toString()+"_criteria");
		 ArrayList<Criteria> criterias = (ArrayList<Criteria>) map.get(this.toString());
		criterias.add(criteria);
		criteria.setCond("or");
		map.put(this.toString(), criterias);
		map.put(this.toString()+"_criteria",null);
		map.put(this.toString()+"_conds",null);
		return this;
	}
	 //-----------------------------------------------------------------------------------Criteria  end
	
	/**
	 * 条件连接完成后，需要调用该方法，返回条件。
	 * @return 条件组
	 */
	public ArrayList<Criteria> criterias() {
		ArrayList<Criteria> criterias = (ArrayList<Criteria>) map.get(this.toString());
		Object c = map.get(this.toString()+"_criteria");
		if( c != null ) {
			Criteria criteria = (Criteria) c;
			criterias.add(criteria);
		}
		map.put(this.toString()+"_criteria",null);      
		map.put(this.toString()+"_conds",null);
		return criterias;
	}
	
	public static void main(String[] args) {
		
		ArrayList<Criteria> ok = 
				new Where().name("id").eq("=",3)
				.criterias();
		String sql = "";
		for (Criteria criteria : ok) {
			sql += "(";
			List<Condition> conds = criteria.getConds();
			for (Condition cond : conds) {
				sql += cond.getName()+cond.getSymbol()+cond.getValue();
				if( cond.getCond() != null ) {
					sql += " "+cond.getCond()+" ";
				}
			}
			sql += ")";
			if( criteria.getCond() != null ) {
				sql += " "+criteria.getCond()+" ";
			}
			
		}
		System.out.println(ok);
		System.out.println("sql:"+sql);
	}
	
	
}

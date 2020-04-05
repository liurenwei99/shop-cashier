package com.cashier.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.cashier.pojo.Criteria;
import com.cashier.pojo.DeleteParams;
import com.cashier.pojo.InsertParams;
import com.cashier.pojo.Page;
import com.cashier.pojo.SelectParams;
import com.cashier.pojo.UpdateParams;
import com.cashier.pojo.Where;


/**
 * <p>ParamsUtils: 参数工具类，转换成params</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月15日
 * @version 1.0  
 */
public class ParamsUtils {

	/**
	 *  把map参数转成UpdateParams
	 * @param table  表名
	 * @param params map参数
	 * @param cols   需要修改的字段 用，号分割	String[] colArr = cols.split(",");
	 * @param criteria  条件
	 * @return
	 */
	public  static UpdateParams converUpdate(String table,Map<String, Object> params,String cols,List<Criteria> criteria) {// id = 2  has_del = 2;
		String[] colArr = cols.split(",");
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
	    for (String col : colArr) {// 拼接更新的字段
	    	hashMap.put(col, params.get(col));
		}
		UpdateParams updateParams = new UpdateParams();
		updateParams.setTableName(table);
		updateParams.setCols(hashMap);
		updateParams.setCriterias(criteria);
		System.out.println("ParamsUtils.converUpdate():"+updateParams);
		return updateParams;
	}
	/**
	 * map的所有kv都是更新的值
	 * @param table
	 * @param cols
	 * @param criteria
	 * @return
	 */
	public  static UpdateParams converUpdateOnCols(String table,Map<String, Object> cols,List<Criteria> criteria) {// id = 2  has_del = 2;
		UpdateParams updateParams = new UpdateParams();
		updateParams.setTableName(table);
		updateParams.setCols(cols);
		updateParams.setCriterias(criteria);
		System.out.println("ParamsUtils.converUpdate():"+updateParams);
		return updateParams;
	}
	/**
	 *       构造一个InsertParams对象
	 *        { tableName: "mhy_business", cols:"name",values:["张三的水果店"]};
	 * @param table 表名
	 * @param params 参数列表
	 * @param cols 字段列表
	 * @param vaues 值集合  需要注意，如果是通过Arrays.asList()来构造list，那么需要用new ArrayList(Arrays.asList(..))来包装一下。否则Exception in thread "main" java.lang.UnsupportedOperationException
	 * @param key 是否返回主键 true 返回
	 * @return 
	 */
	public  static InsertParams converInsert(String table,String cols,List<Object> values,boolean key) {// id = 2  has_del = 2;
		InsertParams insertParams = new InsertParams();
		insertParams.setTableName(table);
		insertParams.setCols(cols);
		insertParams.setValues(new ArrayList<Object>(values));
		if( !key ) {
			insertParams.setId(0L);
		}
		return insertParams;
	}
	/**
	 * 构造删除参数，如果是in删除，也可以使用， criteria = new Where().name("id").eq(ids).criterias(); ids是一个数组
	 * @param table 表名
	 * @param criteria  条件  可以使用Where构造
	 * @return
	 */
	public  static DeleteParams converDelete(String table,List<Criteria> criteria) {// id = 2  has_del = 2;
		DeleteParams deleteParams = new DeleteParams();
		deleteParams.setTableName(table);
		
		deleteParams.setCriterias(criteria);
			
		return deleteParams;
	}
	/**
	 * 构造查询参数,没有分页
	 * @param table  表名
	 * @param qcol   查询字段
	 * @param criteria 条件
	 * @return
	 */
	public  static SelectParams converSelect(String table,String qcol,List<Criteria> criteria) {// id = 2  has_del = 2;
		SelectParams selectParams = new SelectParams();
		selectParams.setTableName(table);
		selectParams.setQcols(qcol);
		selectParams.setCriterias(criteria);
		selectParams.setHasPage(false);
		return selectParams;
	}
	/**
	 * 构造查询参数,  含分页
	 * @param table  表名
	 * @param qcol   查询字段
	 * @param criteria 条件
	 * @return
	 */
	public  static SelectParams converSelect(String table,String qcol,List<Criteria> criteria,Integer currPage,Integer pageSize,String order) {// id = 2  has_del = 2;
		SelectParams selectParams = new SelectParams();
		selectParams.setTableName(table);
		selectParams.setQcols(qcol);
		selectParams.setCriterias(criteria);
//		selectParams.setHasPage(true);
		selectParams.setPage(new Page(currPage,pageSize));
		if( order != null ) {
			selectParams.setOrder(order);
		}
		return selectParams;
	}
	
	/**
	 * InsertParams转UpdateParams
	 * @param params
	 * @return
	 */
	public static UpdateParams  converInsertToUpdate(InsertParams params,List<Criteria> criterias) {
		String[] colArr = params.getCols().split(",");
		List<?> values = params.getValues();
		HashMap<String,Object> hashMap = new HashMap<String, Object>();
		for (int i = 0; i < colArr.length; i++) {
			hashMap.put(colArr[i], values.get(i));
		}
		UpdateParams updateParams = new UpdateParams();
		updateParams.setTableName(params.getTableName());
		updateParams.setCols(hashMap);
		updateParams.setCriterias(criterias);
		System.out.println("ParamsUtils.converInsertToUpdate():"+updateParams);
		
		return updateParams;
	}
	
	/**
	 * 补全字段,如果是有相同的字段会替换原来的值。
	 * @param params InsertParams
	 * @param names  字段列表，用,号分割
	 * @param objects 字段对应的值。
	 */
	public static void addColumns(InsertParams params,String names,  Object ...objects){
		params.setCols(params.getCols()+","+names);
		List<Object> values = params.getValues();
		for (Object object : objects) {
			values.add(object);
		}
		
	}
	
	
}

package com.cashier.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cashier.pojo.DeleteParams;
import com.cashier.pojo.InsertParams;
import com.cashier.pojo.Select;
import com.cashier.pojo.SelectParams;
import com.cashier.pojo.UpdateParams;

/**
 * 基Service
 * 如果params.criteria==null 则表示没有条件
 * @author Administrator
 */
public interface BaseService {

	/**
	 * 普通插入一条数据，不能返回主键,-1表示返回主键
	 * @param params 包含insert的全部信息，是json格式的字符串
	 * @return
	 */
	Long insert(InsertParams  params);
	/**
	 * 新增记录，并且返回自增长id
	 * @return
	 */
//	Long insertKey(InsertParams  params);
	/**
	 * 插入多条记录，暂时不支持返回自增长主键
	 * @param params 数组
	 */
	void insertBatch(InsertParams  params);
	int delete(DeleteParams params);
	int deleteIn(DeleteParams params);
	/**
	 * 更新记录，返回更新条数
	 * @param params
	 * @return
	 */
	int update(UpdateParams params);
	/**
	 * 查询单条记录
	 * @param params
	 * @return
	 *  <select id="selectOne" resultType="java.util.HashMap">
	 	select ${params.qcols} from ${params.tableName} 
	   <include refid="where_criteria_clause"></include>
	 </select>
	 */
	Map<String, Object> selectOne(SelectParams params);
	/**
	 * 条件查询，可设置分页
	 * @param params 查询参数
	 * @return
	 */
	List<Map<String, Object>> selectList(SelectParams params);
//	// 查询全部记录
//	List<Map<String, Object>> selectAll(String tableName, String qcols);
	
	/**
	 *构造sql查询
	 * @param params
	 * @return 始终返回所有字段的列表，如果是一对多等，需要自己处理。
	 */
	List<Map<String, Object>> selectX(Select select);
}

package com.cashier.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cashier.pojo.DeleteParams;
import com.cashier.pojo.InsertParams;
import com.cashier.pojo.SelectParams;
import com.cashier.pojo.UpdateParams;


/**
 * <p>OrderMapper: 基mapper。一个简单的抽取</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月14日
 * @version 1.0  
 */
public interface BaseMapper {
	/**
	 *  单条插入
	 * @param params
	 * @return 返回添加的记录数
	 */
	int insert(@Param("params")InsertParams params);
	int  insertKey(@Param("params")InsertParams params);
	/**
	 * 多条插入
	 * @param params
	 * @return
	 */
	void insertBatch(@Param("params")InsertParams  params);
	//---------------------------------------------------delete 
	// 条件删除
	int delete(@Param("params")DeleteParams params);
	// 批量删除 in(。。。 )
	int deleteIn(@Param("params")DeleteParams params);
    
	/**
	 * 根据某个字段删除
	 * delete from user where user_id = 2
	 * @param tableName   user  表名
	 * @param pk   user_id      字段
	 * @param values  2                           值
	 * @return 成功返回条数
	 * 
	 */
    int deleteByCol(@Param("tableName")String tableName, @Param("col")String col,  @Param("values")Object values);
    
	//---------------------------------------------------delete end 
	//---------------------------------------------------update
	/**
	 * 条件为空的时候，则更新全部
	 * @param tableName 表名
	 * @param upMaps  更新的字段Map
	 * @param clist   条件 criteria 集合
	 * @return 返回更新的条数
	 */
	 int update(@Param("params")UpdateParams params);
//	 int update(@Param("tableName")String tableName,@Param("upMaps")Map<String,Object> upMaps,@Param("clist")List<Criteria> clist);
    //---------------------------------------------------update end
	 
		/**
		 * 查询单个记录
		 * @param params
		 * @return 返回map
		 *  只有该字段不为空的时候，才会有该字段，否则没有
		 */
		Map<String, Object> selectOne(@Param("params")SelectParams params);
	 
	/**
	 * 条件查询，通过hasPage设置分页
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> selectList(@Param("params")SelectParams params);
	/**
	 *构造sql查询
	 * @param params
	 * @return 始终返回所有字段的列表，如果是一对多等，需要自己处理。
	 */
	List<Map<String, Object>> selectX(@Param("params")Map<String, Object> params);
	
}

package com.cashier.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.mapper.BaseMapper;
import com.cashier.pojo.Config;
import com.cashier.pojo.DeleteParams;
import com.cashier.pojo.InsertParams;
import com.cashier.pojo.Select;
import com.cashier.pojo.SelectParams;
import com.cashier.pojo.UpdateParams;
import com.cashier.service.BaseService;



@Service
public class BaseServiceImpl implements BaseService{
	@Autowired
	private BaseMapper baseMapper;
	
	
	@Override
	public Long  insert(InsertParams params) {
		System.out.println("BaseServiceImpl.insert()："+params);
		if( Config.insertNeedTime ) {
			params.setCols(params.getCols()+",create_time");
			List<Object> list = (List<Object>) params.getValues();
			list.add(new Date());
		}
		int ok;
		if( params.getId() == -1) {// 返回主键
			ok = baseMapper.insertKey(params);
		}else {
			ok = baseMapper.insert(params);
		}
		
		System.out.println("params:"+params);
		if( ok > 0 ) {
			return params.getId();// 返回主键
		}
		return null;
	}
//	@Override
//	public Long  insertKey(InsertParams params) {
//		int ok = baseMapper.insertKey(params);
//		System.out.println("params:"+params);
//		if( ok > 0 ) {
//			return params.getId();// 返回主键
//		}
//		return null;
//	}
	@Override
	public void  insertBatch(InsertParams params) {
		 baseMapper.insertBatch(params);
	}

	@Override
	public int delete(DeleteParams params) {
		int ok = baseMapper.delete(params);
		return ok;
	}

	@Override
	public int update(UpdateParams params) {
		int ok = baseMapper.update(params);
		return ok;
	}
	@Override
	public int deleteIn(DeleteParams params) {
		return baseMapper.deleteIn(params);
	}
	@Override
	public List<Map<String, Object>> selectList(SelectParams params) {
		return  baseMapper.selectList(params);
	}
	@Override
	public Map<String, Object> selectOne(SelectParams params) {
		// 如果没有查询到，map则为null，不像List，返回的是[]
		Map<String, Object> map = baseMapper.selectOne(params);
		return map;
	}
	
	/**
	 * 返回最原始的查询，比如：一对多，会冗余一的数据
	 * 使用?作为占位符。
	 */
	@Override
	public List<Map<String, Object>> selectX(Select select) {
		Map<String, Object > map = new LinkedHashMap<String, Object>(); 
		String sql = select.toSql();
		System.out.println("BaseServiceImpl.selectX()...sql:"+sql);
		if( sql.indexOf("?") != -1) {
			if( select.getValues().size() == 0 ) throw new RuntimeException("没有给占位符赋值！！");
			String[] split = sql.split("\\?");
			if( split.length == select.getValues().size() + 1 ) {// 如果中间占位符，那么就需要加上如：  id = ? and o.id > 3
				select.setValues("");
			}
			for (int i = 0; i < split.length; i++ ) {
				map.put(split[i], select.getValues().get(i) );
			}
		}else {
			map.put(sql, "");
		}
		System.out.println("BaseServiceImpl.selectX()" + map);
		return baseMapper.selectX(map);
	}
//	o.shop_id = ? and o.create_time >= ? order by o.create_time 

//	//-------------------------------------------查询

//
//	/**
//	 * 查询：所有list查询都是带分页的。 结果的顺序是记录插入的排序。
//	 */
//	@Override
//	public List<Map<String, Object>> selectList(SelectParams params) {
//		List<Map<String, Object>> list = baseMapper.selectList(params);
//		System.out.println(list);
//		return list;
//	}
//	
//	/**
//	 * 查询：查询全部记录,qcols:id,name...
//	 */
//	@Override
//	public List<Map<String, Object>> selectAll(String tableName,String qcols) {
//		if(qcols == null || "".equals(qcols)) {
//			qcols = "*";
//		}
//		List<Map<String, Object>> list = baseMapper.selectAll(tableName,qcols);
//		System.out.println(list);
//		return list;
//	}

	

}

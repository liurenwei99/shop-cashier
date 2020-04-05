package com.cashier.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>SelectResult: 查询结果集</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年11月10日
 * @version 1.0  
 */
public class SelectResult {
	
	/**
	 *  把查询到的数据集转成一对多接受
	 * @param oneCols  一方的字段 别名使用空格区分
	 * @param manyCols 多方的字段
	 * @param oneKey 返回的时候，一方的key。
	 * @param manyKey 返回的时候多方的key
	 * @param pk      一方的主键。唯一区分记录
	 * @param results 查询到的结果集
	 * @return 返回一对多实体
	 * result = new ArrayList<Map<String, Object>>();
	 * details = new ArrayList<Map<String, Object>>();
	 * detailsItem = new HashMap<String, Object>();
	 * 
	 * 最终result 如：details（多） master（一）
	 *  [
	 *    {"details":[{}],"master":{}},
	 *    {"details":[{}],"master":{}},
	 *  ]
	 * 
	 */
	public static List<Map<String, Object>> oneToMany(String oneCols, String manyCols, String oneKey, String manyKey, String pk, List<Map<String, Object>> results){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//		for (Map<String, Object> map : results) {
//			System.out.println("SelectResult.oneToMany()-----------"+map);
//		}
		//_______________________字段名和查询别名映射出来，到时候，封装的时候使用数据库的字段名，不用别名。
		String[] onecs = oneCols.split(",");
		Map<String, String> oneCs = new HashMap<String, String>();
	    for (int i = 0; i < onecs.length; i++) {
	    	String name = onecs[i].trim();
	    	if( name.indexOf(" ") != -1 ) {
	    		String[] nameKv = name.split(" ");
	    		String orgName = nameKv[0].trim();// 原name
	    		String nowName = nameKv[1].trim();
	    		if( orgName.indexOf(".") != -1 ) {
	    			orgName = orgName.split("\\.")[1];
	    		}
	    		oneCs.put(nowName, orgName);
			}
		}
	  //_______________________字段名和查询别名映射出来，到时候，封装的时候使用数据库的字段名，不用别名。
		// 多  是否使用别名
		String[] manycs = manyCols.split(",");
		Map<String, String> manyCs = new HashMap<String, String>();
	    for (int i = 0; i < manycs.length; i++) {
	    	String name = manycs[i].trim();
	    	if( name.indexOf(" ") != -1 ) {
	    		String[] nameKv = name.split(" ");
	    		String orgName = nameKv[0].trim();// 原name
	    		String nowName = nameKv[1].trim();
	    		if( orgName.indexOf(".") != -1 ) {
	    			orgName = orgName.split("\\.")[1];
	    		}
	    		manyCs.put(nowName, orgName);
			}
		}
		
//		List<Map<String, Object> > oneList = new ArrayList<Map<String,Object>>();
		Map<String, Object> one = null;
		List<Map<String, Object> > manyList = null;// new ArrayList<Map<String,Object>>();
		Map<String, Object>  many = null;
		Map<String, Object> listItemMap = null;
		String prevPkValue = "";
		results.add(new HashMap<String, Object>());
		for (Map<String, Object> map : results) {
			if( !prevPkValue.equals(String.valueOf(map.get(pk))) ) { 
				
				if( listItemMap != null ) {
					listItemMap.put(manyKey, manyList);
					result.add(listItemMap);
				}
				listItemMap = new HashMap<String, Object>();
				if( map.get(pk) != null ) {
				 	one = new HashMap<String, Object>();
					Set<String> keys = oneCs.keySet();
					for (String item : keys) {
						if( map.get(item) != null ) {
							one.put(oneCs.get(item), map.get(item));
						}
					}
					listItemMap.put(oneKey, one);
				}
				manyList = null;
			}
			if( map.get(pk) == null ) break;// 已经执行回调赋值，就不需要执行下面的了、
			many = new HashMap<String, Object>();
			Set<String> keys = manyCs.keySet();
			for (String item : keys) {
				if( map.get(item) != null ) {
					many.put(manyCs.get(item), map.get(item));
				}
			}
			if( manyList == null ) manyList = new ArrayList<Map<String,Object>>();
			manyList.add(many);
			     
			prevPkValue = String.valueOf( map.get(pk) );
			
		}
		
		
//		List<Map<String, Object>> manyList = null;// 详情列表
//		Map<String, Object> listItemMap = null;// 列表对象
//		Map<String, Object> manyItemMap = null;// 一个 一对多对象  
//		Map<String, Object> one = null;// 一方对象
//		String currPkValue = "";
//		results.add(new HashMap<String, Object>());// 如果不加上这个，那个最后一步不执行。
//		for (Map<String, Object> map : results) {
//		
//			if( !currPkValue.equals(String.valueOf(map.get(pk))) ) {
//				manyItemMap = new HashMap<String, Object>();// 一对多对象
//				one = new HashMap<String, Object>();
//				for (String key : onecs) {
//					if( map.get(key) != null ) {
//						one.put(key, map.get(key));
//					}
//				}
//				manyItemMap.put(oneKey, one);	
//				if( manyList != null ) {// 详情列表
//					manyItemMap.put(manyKey, manyList);
//					result.add(manyItemMap);
//				}
//			
//				manyList = new ArrayList<Map<String,Object>>();// 详情列表
//			}
//			
//			
//			System.out.println("String.valueOf(map.get(pk):"+String.valueOf(map.get(pk)));
//			
////		    if( !currPkValue.equals(String.valueOf(map.get(pk))) ) {// 一个轮回
////				if( manyList != null ) {// 详情列表
////					manyItemMap.put(manyKey, manyList);
////					result.add(manyItemMap);
////				}
////				manyList = new ArrayList<Map<String,Object>>();// 详情列表
////			}
//			listItemMap =  new HashMap<String, Object>();// 列表项
//			for (String key : manycs) {
//				if( map.get(key) != null ) {
//					listItemMap.put(key, map.get(key));
//				}
//			}
//			manyList.add(listItemMap);
//			
//			
//			
//			currPkValue = String.valueOf( map.get(pk) );
//		}
		return result;
	}
	public static void main(String[] args) {
		String aString = "ghrei gjroe   .,ghr ";
		System.out.println(aString.split("\\.")[0]);
		int indexOf = aString.indexOf(" ");
		System.out.println("IndexOf:"+indexOf);
	}
}




/**
public class SelectResult {
	
	/**
	 *  把查询到的数据集转成一对多接受
	 * @param oneCols  一方的字段 别名使用空格区分
	 * @param manyCols 多方的字段
	 * @param oneKey 返回的时候，一方的key。
	 * @param manyKey 返回的时候多方的key
	 * @param pk      一方的主键。唯一区分记录
	 * @param results 查询到的结果集
	 * @return 返回一对多实体
	 * result = new ArrayList<Map<String, Object>>();
	 * details = new ArrayList<Map<String, Object>>();
	 * detailsItem = new HashMap<String, Object>();
	 * 
	 * 最终result 如：details（多） master（一）
	 *  [
	 *    {"details":[{}],"master":{}},
	 *    {"details":[{}],"master":{}},
	 *  ]
	 * 
	public static List<Map<String, Object>> oneToMany(String oneCols, String manyCols, String oneKey, String manyKey, String pk, List<Map<String, Object>> results){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
//		for (Map<String, Object> map : results) {
//			System.out.println("SelectResult.oneToMany()-----------"+map);
//		}
		String[] onecs = oneCols.split(",");
		Map<String, String> oneCs = new HashMap<String, String>();
	    for (int i = 0; i < onecs.length; i++) {
	    	String name = onecs[i].trim();
	    	if( name.indexOf(" ") != -1 ) {
	    		String[] nameKv = name.split(" ");
	    		String orgName = nameKv[0].trim();// 原name
	    		String nowName = nameKv[1].trim();
	    		if( orgName.indexOf(".") != -1 ) {
	    			orgName = orgName.split("\\.")[1];
	    		}
	    		oneCs.put(orgName, nowName);
			}
		}
		// 是否使用别名
		for (int i = 0; i < onecs.length; i++) {
			String name = onecs[i].trim();
			if( name.indexOf(" ") != -1 ) { 
				onecs[i] = name.split(" ")[1].trim();// 替换上别名
			}
		}
		// 是否使用别名
		String[] manycs = manyCols.split(",");
		for (int i = 0; i <manycs.length; i++) {
			String name = manycs[i].trim();
			if( name.indexOf(" ") != -1 ) { 
				manycs[i] = name.split(" ")[1].trim();// 替换上别名
			}
		}
		
//		List<Map<String, Object> > oneList = new ArrayList<Map<String,Object>>();
		Map<String, Object> one = null;
		List<Map<String, Object> > manyList = null;// new ArrayList<Map<String,Object>>();
		Map<String, Object>  many = null;
		Map<String, Object> listItemMap = null;
		String prevPkValue = "";
		results.add(new HashMap<String, Object>());
		for (Map<String, Object> map : results) {
			if( !prevPkValue.equals(String.valueOf(map.get(pk))) ) { 
				
				if( listItemMap != null ) {
					listItemMap.put(manyKey, manyList);
					result.add(listItemMap);
				}
				listItemMap = new HashMap<String, Object>();
				if( map.get(pk) != null ) {
					one = new HashMap<String, Object>();
					for (String key : onecs) {
						if( map.get(key) != null ) {
							one.put(key, map.get(key));
						}
					}
					listItemMap.put(oneKey, one);
				}
				manyList = null;
			}
			if( map.get(pk) == null ) break;// 已经执行回调赋值，就不需要执行下面的了、
			many = new HashMap<String, Object>();
			for (String key : manycs) {
				if( map.get(key) != null ) {
					many.put(key, map.get(key));
				}
			}
			if( manyList == null ) manyList = new ArrayList<Map<String,Object>>();
			manyList.add(many);
			     
			prevPkValue = String.valueOf( map.get(pk) );
			
		}
		
		
//		List<Map<String, Object>> manyList = null;// 详情列表
//		Map<String, Object> listItemMap = null;// 列表对象
//		Map<String, Object> manyItemMap = null;// 一个 一对多对象  
//		Map<String, Object> one = null;// 一方对象
//		String currPkValue = "";
//		results.add(new HashMap<String, Object>());// 如果不加上这个，那个最后一步不执行。
//		for (Map<String, Object> map : results) {
//		
//			if( !currPkValue.equals(String.valueOf(map.get(pk))) ) {
//				manyItemMap = new HashMap<String, Object>();// 一对多对象
//				one = new HashMap<String, Object>();
//				for (String key : onecs) {
//					if( map.get(key) != null ) {
//						one.put(key, map.get(key));
//					}
//				}
//				manyItemMap.put(oneKey, one);	
//				if( manyList != null ) {// 详情列表
//					manyItemMap.put(manyKey, manyList);
//					result.add(manyItemMap);
//				}
//			
//				manyList = new ArrayList<Map<String,Object>>();// 详情列表
//			}
//			
//			
//			System.out.println("String.valueOf(map.get(pk):"+String.valueOf(map.get(pk)));
//			
////		    if( !currPkValue.equals(String.valueOf(map.get(pk))) ) {// 一个轮回
////				if( manyList != null ) {// 详情列表
////					manyItemMap.put(manyKey, manyList);
////					result.add(manyItemMap);
////				}
////				manyList = new ArrayList<Map<String,Object>>();// 详情列表
////			}
//			listItemMap =  new HashMap<String, Object>();// 列表项
//			for (String key : manycs) {
//				if( map.get(key) != null ) {
//					listItemMap.put(key, map.get(key));
//				}
//			}
//			manyList.add(listItemMap);
//			
//			
//			
//			currPkValue = String.valueOf( map.get(pk) );
//		}
		return result;
	}
	public static void main(String[] args) {
		String aString = "ghrei gjroe   .,ghr ";
		System.out.println(aString.split("\\.")[0]);
		int indexOf = aString.indexOf(" ");
		System.out.println("IndexOf:"+indexOf);
	}
}
*/
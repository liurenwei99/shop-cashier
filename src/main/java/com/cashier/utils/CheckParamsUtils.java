package com.cashier.utils;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.Map;

/**
 * <p>CheckParamsUtils: map参数校验</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月15日
 * @version 1.0  
 */
public class CheckParamsUtils {

	
	/**
	 *  必要字段校验空或者null,校验失败抛出异常
	 * @param names 参数名："name,id"
	 * @param params
	 * 
	 */
	public static void check(String names,Map<String, String> params) {
		if( names == null || params == null) {
			throw new RuntimeException("names为空，或者params为空，请检查") ;
		}
		String[] split = names.split(",");
		for (String name : split) {
			Object object = params.get(name);
			if( object == null ) {
				throw new RuntimeException(name+"参数不能为空!") ;
			}
		}
		
	}
}

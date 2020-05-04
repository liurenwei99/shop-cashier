package com.cashier.controller;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.cashier.pojo.dto.CashierPrintTmp;
import com.cashier.utils.ParamsUtils;
import com.cashier.utils.PrintUtils;
import com.myutil.utils.HttpUtils;

/**
 * <p>MainText: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年12月4日
 * @version 1.0  
 */
public class MainTest {
	public static void main(String[] args) {
		File file = new File("E:\\dev-wei-19813\\dev-soft\\apache-tomcat-8.5.33\\webapps\\data\\data");
//		File file = new File("E:\\dev-wei-19813\\行街\\ssl\\test");
//	    System.out.println(file.isDirectory());
//		whileDir(file);
//		File[] fs = file.listFiles();
//		for (File file2 : fs) {
//			System.out.println(file2.getName());
//		}
		
		new Thread(new  Runnable() {
			public void run() {
				String doGet = HttpUtils.doGet("http://www.baidu.com", null);
				System.out.println("MainTest.main()"+doGet);
			}
		}).start();
		System.out.println("this is result！！");
		
		
	}
	
  public static void whileDir(File dir){
	   File[] f = dir.listFiles();
	   for (File file : f) {
		  if( file.isDirectory() ) {
			  if( file.list().length == 0 ) {// 删除空目录
				  file.delete();
			  }else {
				  whileDir(file);
			  }
		  }
		
	   }
   }
  @Test
  public void testClass() throws Exception {
	  CashierPrintTmp tmp = new CashierPrintTmp();
	  tmp.setName("张三");
	  tmp.setUpdateTime(new Date());
	  tmp.setText("这是文本");
	  tmp.setCreateTime(new Date());
	  Class<? extends Object> clazz = tmp.getClass();
		Field[] fields = clazz.getDeclaredFields();
	    StringBuffer sb = new StringBuffer();
	    List<Object> values = new ArrayList<Object>();
		for (Field field : fields) {
			System.out.println("field:"+field.getName()+"，类型："+field.getType());
			String type = field.getType().toString();
			String fieldName = field.getName() ;// 字段名
			String methodName = "";// 方法，名
			if( "boolean".equals(type) || "java.lang.Boolean".equals(type) ) {
				methodName = "is";
			}else {
				methodName = "get";
			}
			methodName += fieldName.substring(0, 1).toUpperCase()+fieldName.substring(1);
			  Method method = clazz.getMethod(methodName, null);
			  Object invoke = method.invoke(tmp, null);
			  System.out.println(invoke);
			  if( invoke != null ) {
				  sb.append(clazz.getMethod(methodName+"DbFieldName",null).invoke(tmp, null) + ",");
				  values.add(invoke);
			  }
		}
//		System.out.println("cols:"+sb.substring(0, sb.length()-1));
		ParamsUtils.converInsert("", sb.substring(0, sb.length()-1), values,true);
  }
  
  @Test
  public void testJavaBase() {
	  Object[] o = new Object[1];
	  o[0] = "张三";
	  System.out.println(o.length);
	  Object[] copyOf = Arrays.copyOf(o,o.length+1);
	  System.out.println(copyOf[0]);
  }
}

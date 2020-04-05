package com.cashier.mapper;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>CreateEntity: 把表单的字段生成对应的实体</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年11月7日
 * @version 1.0  
 */
public class CreateEntity {
    private static final String CONN_URL = "jdbc:mysql://47.106.237.105:3306/restaurant?characterEncoding=utf-8";
    private static final String USER = "root";
    private static final String PSSS = "u523pZEfAxayXTHR";
    private static final boolean CLASS_IS_HUMP = true;// 生成类名是否按照驼峰法
    private static final boolean ATTR_IS_HUMP = true;// 生成字段名是否按照驼峰法
    private static final boolean IS_USE_LOMBOK = true;// 是否使用lombok生成setget方法   import lombok.Data;
    private static final String[] USE_LOMBOKS = {"@Data","@ToString"};// 使用lombok的哪些注解  
    private static final boolean IS_SET_GET = false;// 是否生成setget方法,如果设置了lombok，那么优先使用lombok
    private static StringBuffer setGets =  new StringBuffer();// 保存 setGets方法 集合
    private static StringBuffer dbMap =  new StringBuffer();// 提供方法获取 类字段对应数据库中字段的名称
    
    
    
    private static Map<String, String> tables = getTables();// 所有表名
    
    
    
    private static List< DbRelation > relations;
   
	public static void main(String[] args) throws Exception {
//		List<String> attrs = getAttrs("cashier_log");
//		attrs.forEach((item)->{
//			System.out.println(item);
//		});
		String path = "E:\\dev-wei-19813\\eclipse\\workspace\\shangtao\\shop-cashier\\src\\main\\java\\com\\cashier\\pojo\\dto\\";
		String tableName = "cashier_print_tmp";
//		String tableName = "cashier_order_details";
		String _package = "com.cashier.pojo.dto";
//		relations = new ArrayList<DbRelation>();
//		relations.add(new DbRelation("cashier_order_master", "cashier_order_details"));
//		relations.add(new DbRelation("administrators", "address"));
		// 单个表
		createOne(tableName, _package,path);
		// 全部表
//		createAll(_package,path);
	}
	
	public static List<String> getAttrs(String tableName)  {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		ResultSet result = null;
		try {
			conn = DriverManager.getConnection(CONN_URL,USER,PSSS);
			 Statement statement = conn.createStatement();  
			 result = statement.executeQuery("SELECT * FROM " + tableName);
			   ResultSetMetaData md2 = result.getMetaData();
			   int count = md2.getColumnCount();  
			  for (int i = 1; i < count+1; i++) {
				   String columnName = md2.getColumnName(i);
				   if( ATTR_IS_HUMP ) {
						 String[] split = columnName.split("_");// 数据库的命名需要是_分隔
						 if( split.length > 1) {
							 columnName = "";
							for (String name : split) {
								 columnName += name.substring(0,1).toUpperCase() + name.substring(1);
							}
							columnName = columnName.substring(0,1).toLowerCase() + columnName.substring(1);
						 }
				   }
				   //columnName成员变量
				   // 生成提供获取数据库字段的方法“DbFieldName”作为后缀
				   dbMap.append("\tpublic String get"+(columnName.substring(0, 1).toUpperCase()+columnName.substring(1)) + "DbFieldName(){\n");
				   dbMap.append("\t\treturn \"" + md2.getColumnName(i) + "\";\n");
				   dbMap.append("\t}\n\n");
				   // 生成提供获取数据库字段的方法“DbFieldName”作为后缀
				   int o = md2.getColumnType(i);
				   System.out.print(o+"," + "名字："+md2.getColumnTypeName(i)+",className:"+ md2.getColumnClassName(i)+",label："+md2.getColumnLabel(i));
				   System.out.println();
				   System.out.print(columnName+",");
				   String columnClassName = md2.getColumnClassName(i);
				   if(!IS_USE_LOMBOK && IS_SET_GET  ) {// 生成Set Get函数
//					   String cName = columnName.substring(0,1).toUpperCase() + columnName.substring(1);
					   createSet(columnName,columnClassName);
					   createGet(columnName,columnClassName);
				   }
				   if( "java.sql.Timestamp".equals(columnClassName) ) {
					   columnClassName = "java.util.Date";
				   }
				   list.add("\tprivate " + columnClassName + " "+ columnName+";\n");
			   }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if( result != null) {
				try {
					result.close();
					closeConn(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		
		  
		return list;
	}
	/**
	 * 获取表的注释
	 * @param tableName
	 * @return
	 */
	public static List<String> getComments(String tableName)  {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		ResultSet result = null;
		try {
			conn = DriverManager.getConnection(CONN_URL,USER,PSSS);
			Statement statement = conn.createStatement();  
			 result = statement.executeQuery("show full columns from " + tableName);
			   while ( result.next() ) {
				   String comment = result.getString("Comment");
				   System.out.println("Comment:" + comment);
				   list.add("\t// "+comment+"\n");
			   }
			   return list;
		} catch (SQLException e) {    
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			if( result != null) {
				try {
					result.close();
					closeConn(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	/**
	 * @param conn
	 */
	private static void closeConn(Connection conn) {
		if( conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 开始创建(单张表)
	 * @param tableName 表名
	 * @param _package 包名
	 * @param descPath 生成后的位置
	 * @param isHump   是否驼峰命名
	 * @throws Exception 
	 */
	public static void createOne(String tableName, String _package, String descPath) throws Exception {
		System.out.println("tables:"+tables);
		System.out.println("tableName:"+ tableName);
		create(tableName,_package,descPath);
	}
	static StringBuilder packages=null;
	static StringBuilder annotations =null;
	static StringBuilder sb = null;
	public static void create(String tableName, String _package, String descPath) throws Exception {
		 setGets =  new StringBuffer();
		 packages = new StringBuilder();
		 annotations = new StringBuilder();
		 sb = new StringBuilder();// 类
		 sb.append("public class " + tables.get( tableName ) + "{\n\n");
		 FileOutputStream fos = new FileOutputStream(new File(descPath +  tables.get( tableName ) +".java"));
		 
		 List<String> attrs = getAttrs(tableName);
		 List<String> comments = getComments(tableName);
		
		 packages.append("package " + _package + ";\n" );
		 if( IS_USE_LOMBOK ) {// 是否使用lombok
			 packages.append("import lombok.*;\n");
			 for (int i = 0; i < USE_LOMBOKS.length; i++) {
				String zj = USE_LOMBOKS[i];
				annotations.append(zj+"\n");
			}
			
		 }
		 System.out.println("开始生成：。。。。");
		 
		 for (int i = 0; i < comments.size(); i++) {
			sb.append(comments.get(i));
			sb.append(attrs.get(i));
			sb.append("\n");
		 }
		 
		 if( relations != null ) {
			for (DbRelation relation : relations) {
				if( relation.getOne().equals(tableName) ){
					String manyTable = tables.get(relation.getMany());
					packages.append("import " + _package+"."+manyTable+";\n");// 导包
					sb.append("\tprivate  java.util.List<" + manyTable + "> " + manyTable + "s;\n");
					 if( IS_SET_GET  ) {
						 createSet(manyTable, _package);
						 createGet(manyTable, _package);
					 }
				}
				
			}
		 }
		 
		 // 生成setget
		 if(  setGets.length() > 0  ) {
			 sb.append(setGets);
		 }
		 sb.append(dbMap);// 对应的db名字
		 System.out.println("tables:"+tables);
		 sb.append("}");// 类结束
		 packages.append(annotations);
		 packages.append(sb);
		 fos.write(packages.toString().getBytes());
		 fos.close();
		
	}
	/**
	 * 开始创建(全部)
	 * @param tableName 表名
	 * @param _package 包名
	 * @param descPath 生成后的位置
	 * @param isHump   是否驼峰命名
	 * @throws Exception 
	 */
	public static void createAll(String _package, String descPath) throws Exception {
		tables.forEach((key,value)->{
			try {
				create(key,_package,descPath);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		});
//		for (String name : tables) {
//			create(name,_package,descPath);
//		}
	}
	public static Map<String,String> getTables()  {
		Connection conn = null;
		ResultSet result = null;
		tables = new HashMap<String, String>();
		try {
			conn = DriverManager.getConnection(CONN_URL,USER,PSSS);
//			 result = statement.executeQuery("show tables");
			 //获取数据库的元数据
	            DatabaseMetaData db = conn.getMetaData();
	            //从元数据中获取到所有的表名
	            result = db.getTables(null, null, "%",null);
	            while(result.next()) {
//	                tableNames.add(rs.getString(3));
	                System.out.println("表名："+result.getString(3));
	                String tableName = result.getString(3);
	                String tableName_ = tableName;
	                
	           	 if( CLASS_IS_HUMP ) {// 类是否驼峰命名生成
	    			 String[] split = tableName.split("_");
		    			 if( split.length > 1 ) {
		    				 tableName = "";
		    				 for (String name : split) {
		    					 tableName += name.substring(0,1).toUpperCase() + name.substring(1);
		    				}
		    		
		    			 }
		    	 }
		    		 tableName = tableName.substring(0,1).toUpperCase() + tableName.substring(1);
		    		 tables.put(tableName_, tableName);
	            }
	            return tables;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally {
			if( result != null) {
				try {
					result.close();
					closeConn(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			
		}
	}

	public static void createSet(String columnName,String columnClassName) {
			if( "java.sql.Timestamp".equals(columnClassName) ) {
				columnClassName = "java.util.Date";
			}
		  String cName = columnName.substring(0,1).toUpperCase() + columnName.substring(1);
		   setGets.append("\n\tpublic void set"+cName + "(" + columnClassName + " "+columnName+"){\n");
		   setGets.append("\t\tthis." + columnName + "=" + columnName+";\n");
		   setGets.append("\t}\n");
	}

	public static void createGet(String columnName,String columnClassName) {
		  String cName = columnName.substring(0,1).toUpperCase() + columnName.substring(1);
		  setGets.append("\tpublic " + columnClassName + " get"+cName + "(){\n");
		   setGets.append("\t\treturn " + columnName + ";\n");
		   setGets.append("\t}\n\n");
	}
	
}

class DbRelation{
	public DbRelation(String one, String many) {
		this.one = one;
		this.many  = many;
	}
	private String one;// 表名，主表
	private String many;// 副表，表名
	public String getOne() {
		return one;
	}
	public void setOne(String one) {
		this.one = one;
	}
	public String getMany() {
		return many;
	}
	public void setMany(String many) {
		this.many = many;
	}
	
	
	
}

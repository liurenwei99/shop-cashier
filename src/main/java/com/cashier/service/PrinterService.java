package com.cashier.service;

import java.util.List;
import java.util.Map;

import com.cashier.pojo.InsertParams;

/**
 * <p>PrinterService: 打印模块 service接口</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月20日
 * @version 1.0  
 */
public interface PrinterService {


	/**
	 * 首页展示
	 * @return
	 */
	Map<String, Object> index();
	/**
	 * 查询已添加的打印机
	 * @return
	 */
	List<Map<String, Object>> selectAdds();
	
	/**
	 * 查询打印机列表
	 * @return
	 */
	List<Map<String, Object>> selectPrints();
	
	/**
	 * 扫码本地打印机列表
	 * @return
	 */
	List<Object> scanPrints();
	

	
}

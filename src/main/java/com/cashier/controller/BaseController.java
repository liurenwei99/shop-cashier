package com.cashier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cashier.pojo.DeleteParams;
import com.cashier.pojo.InsertParams;
import com.cashier.service.BaseService;

import common.WeResult;

/**
 * <p>BaseController: 基控制器，包含基本的增删改查</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月15日
 * @version 1.0  
 */
@Controller
@RequestMapping("/base")
public class BaseController {

	@Autowired
	private BaseService baseService;
	
	/**
	 * 新增一条记录
	 * @param params 
	 * { "tableName": "cashier_order_master", "cols":"total_money,address","values":[[10000,"广州番禺..."]] }
	 * @return
	 */
	@RequestMapping("/insert")
	public WeResult insert( InsertParams params) {
		try {
			long  add = baseService.insert(params);
			return WeResult.result(200, add);
		} catch (Exception e) {
			e.printStackTrace();
			return WeResult.result(500,e.getMessage());
		}
	}
	
	/**
	 * 批量添加记录
	 * @param params 
	 * { "tableName": "cashier_order_master", "cols":"total_money,address","values":[[10000,"广州番禺..."]] }
	 * @return
	 */
	@RequestMapping("/insertBatch")
	public WeResult insertBatch( InsertParams params) {
		try {
			baseService.insertBatch(params);
			return WeResult.result(200, "ok");
		} catch (Exception e) {
			e.printStackTrace();
			return WeResult.result(500,e.getMessage());
		}
	}
	/**
	 *        条件删除一条记录
	 * @param params
	 *{ 
		tableName:"user_",
		criteria:[
			{ 
				conds: [
					{ "name": "name", value: "aaa",symbol:”=”,"cond":"and"},
					{"name":"age",value:27,symbol:”=”}
				]
			}
		] 
	 };
	 * @return
	 */
	@RequestMapping("/delete")
	public WeResult delete( DeleteParams params) {
		try {
			int ok = baseService.delete(params);
			return WeResult.result(200, ok);
		} catch (Exception e) {
			e.printStackTrace();
			return WeResult.result(500,e.getMessage());
		} 
	}
	/**
	 * 批量删除记录,in(...)
	 * @param params
	 * @return
	*/
	@RequestMapping("/delin")
	public WeResult deleteIn(@RequestBody DeleteParams params) {
		try {
			int ok = baseService.delete(params);
			return WeResult.result(200, ok);
		} catch (Exception e) {
			e.printStackTrace();
			return WeResult.result(500,e.getMessage());
		}
	} 
}

package com.cashier.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.cashier.common.DBValue;
import com.cashier.common.FinalValue;
import com.cashier.pojo.DeleteParams;
import com.cashier.pojo.IndexParams;
import com.cashier.pojo.Page;
import com.cashier.pojo.UpdateParams;
import com.cashier.pojo.Where;
import com.cashier.service.BaseService;
import com.cashier.service.ItemService;
import com.cashier.service.OrderService;
import com.cashier.service.impl.WebSocketServiceImpl;
import com.cashier.utils.ParamsUtils;
import com.google.gson.Gson;

import common.WeResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.TextMessage;

/**
 * <p>ItemController: 产品controller</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	@Autowired
	private BaseService baseService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WebSocketServiceImpl webSocketServiceImpl;
	
	@RequestMapping("/get/id/{id}")
	public WeResult getItem(@PathVariable("id") String id) {
		System.out.println("ItemController.getItem():"+id);
		Map<String, Object> item = itemService.getById(id);
		if( item != null ) {
			return WeResult.result(200, item);
		}
		return null;
	}
	
	/**
	 * 根据类目id，查询商品（分页）
	 * 如果catId为null，则查询全部商品
	 * @param catId  产品类目id
	 * @param shopId 门店id
	 * @param currPage  当前页
	 * @return
	 */
	@GetMapping("/select")
	public WeResult selectItemByCat( String catId, String shopId, Integer currPage ) {
		if( shopId == null ) {
			return null;
		}
		Page page = new Page();
		page.setCurrPage(currPage);
		page.setPageSize(FinalValue.PAGE_SIZE);
		List<Map<String, Object>> list =   itemService.selectItemByCat(catId,shopId, page);
		if( list != null ) {
			return WeResult.result(200, list);
		}  
		return null;
	}
	@RequestMapping("/test/div")
	public WeResult div() {
		
		  List<String> list = new ArrayList<String>();
		  list.add("{\"value\":\"<div style=&quot;color:red&quot;><img src=&quot;http://www.baidu.com&quot;>官方你发给&quot;你发个1</div>\"}");
//		  List<String>list2 = Arrays.asList("{value:'<div style='挂牌价人'>官方你发给你发个</div>'}","");
			return WeResult.result(200, list);
	}
	
	
	
	/**
	 * 后厨更新订单的商品
	 * @return
	 */
	@PostMapping("/updateItem")
	public WeResult updateItem( @RequestBody UpdateParams params) {
		System.out.println("ItemController.updateItem()"+params);
		if( params == null || params.getOtherParams() == null || params.getOtherParams().get("id") == null) return WeResult.result(400, "参数有误，请检查");
		params.setTableName(DBValue.CASHIER_ORDER_DETAIL);
		params.setCriterias(new Where().name("id").eq("=", params.getOtherParams().get("id")).criterias());
		try {
//			int i = 9 /0;
			int data = baseService.update(params);
			if( data > 0 ) {
				return WeResult.result(200, params.getOtherParams().get("id"));				
			}
		}catch (Exception e) {
			return WeResult.result(500,e.getMessage());	
		}
		return WeResult.result(500, "系统繁忙！");
	}
	/**
	 * 订单页 删除订单的商品，实际把speed改为-1。成功返回原id
	 * @return
	 */
	@PostMapping("/delete")
	public WeResult deleteItem(String id,String orderId,String shopId) {
		System.out.println("ItemController.deleteItem():"+id);
		try {
//			int data = baseService.delete(ParamsUtils.converDelete(DBValue.CASHIER_ORDER_DETAIL, new Where().name("id").eq("=",id).criterias()));
			Map<String, Object> params = new ConcurrentHashMap<String, Object>();
			params.put("speed",-1);
			int data = baseService.update(ParamsUtils.converUpdateOnCols(DBValue.CASHIER_ORDER_DETAIL, params, new Where().name("id").eq("=",id).criterias()));
			if( data > 0 ) {
				 try {// 后厨通知
					 Map<String, Object> selectOrderAndDetialById = orderService.selectOrderAndDetialById( Long.parseLong( orderId ));
					 System.out.println("OrderController.updateOrder():"+selectOrderAndDetialById);
					 Gson gson = new Gson();
					 webSocketServiceImpl.sendMessageToUser(FinalValue.SOCKET_CHEF_PREFIX + shopId, new TextMessage( gson.toJson(selectOrderAndDetialById)));
				 }catch (Exception e) {
					 System.out.println("OrderController.add():下单通知后厨异常："+e.getMessage()); 
				 }
				return WeResult.result(200, id);		
				
			}
		}catch (Exception e) {
			return WeResult.result(500,e.getMessage());	
		}
		return WeResult.result(500, "系统繁忙！");
	}
}

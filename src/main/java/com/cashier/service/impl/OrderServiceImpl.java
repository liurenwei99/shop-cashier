package com.cashier.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;

import com.cashier.common.DBValue;
import com.cashier.mapper.OrderMapper;
import com.cashier.pojo.Criteria;
import com.cashier.pojo.InsertParams;
import com.cashier.pojo.Select;
import com.cashier.pojo.SelectParams;
import com.cashier.pojo.SelectResult;
import com.cashier.pojo.UpdateParams;
import com.cashier.pojo.Where;
import com.cashier.service.BaseService;
import com.cashier.service.OrderService;
import com.cashier.utils.ParamsUtils;
import com.google.gson.Gson;

/**
 * <p>OrderServiceImpl: 订单service接口的实现类 </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月14日
 * @version 1.0  
 */
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private BaseService baseService;
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private WebSocketServiceImpl webSocketServiceImpl;

	
//	@Autowired
//	private BaseMapper baseMapper;
	
	
	@Override
	@Transactional
	public long add(List<InsertParams> params) {
//		try {
			
			// 0表示添加订单
			 long add = baseService.insert(params.get(0));
			 InsertParams details = params.get(1);
			 details.setCols(details.getCols()+",order_id");
			 List<Object> values =  details.getValues();
			 System.out.println("values:" + values);
			 // 给订单详情补上订单id
			 for (Object objects : values) {
				 ArrayList<Object> arr =  (ArrayList<Object>) objects;
				 arr.add(add);
			 }
			 details.setValues(values);
			 baseService.insertBatch(details);// 添加订单详情
			 params.get(0).setId(add);
//			 // 通知后厨
//			 webSocketServiceImpl.sendMessageToUser("888",new TextMessage(new Gson().toJson(params)));
			 return add;
		
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
	}

	/**
	 * 作废订单
	 */
	@Override
	public long cancel(UpdateParams params) {
		return baseService.update(params);
	}

	@Override
	@Transactional
	public int delete(Long orderId) {
//		DeleteParams orderDel = params.get(0);// 表示是订单
//		 Long id = Long.parseLong(params.get("id"));
//		 Integer del = Integer.parseInt(params.get("has_del"));
//		 Integer userType = Integer.parseInt(params.get("user_type"));// 1=用户  2=商家
		 int  ok = 0;
//		 if( del == 0 ) {
//			 // 修改为删除状态即可。
//			 params.put("has_del", userType.toString());
//			 ArrayList<Criteria> criterias = new Where().name("id").eq("=",id).criterias();
//			 ok = baseService.update(ParamsUtils.converUpdate(DBValue.CASHIER_ORDER_MASTER,params,"has_del",criterias));
//		 }else {// 用户和商家都 删除了，把订单数据删除，移到日志表
//			 Map<String, Object> selectOrderAndDetialById = orderMapper.selectOrderAndDetialById(id);
//			 System.out.println("------------------------删除--到日志");
//			 deleteToLog(id,selectOrderAndDetialById);
//		 }
		 Map<String, Object> selectOrderAndDetialById = orderMapper.selectOrderAndDetialById(orderId);
		 System.out.println("------------------------删除--到日志");
		 ok = deleteToLog(orderId,selectOrderAndDetialById);
		return ok;
	}

	@Transactional
	public int deleteToLog(Long id, Map<String, Object> selectOrderAndDetialById) {
		 System.out.println("OrderServiceImpl.deleteToLog():"+selectOrderAndDetialById);
		 List<Map<String, Object>> details = (List<Map<String, Object>>) selectOrderAndDetialById.get("details");
		 List<Object> ids =new ArrayList<Object>();
		 for (Map<String, Object> map : details) {
			 	ids.add(map.get("id"));
		 }
		 System.out.println("sele:"+selectOrderAndDetialById);
		 // 删除主订单 where.name("(","id").eq("张三").and().name("status").notEq(2,")").and().name("a").eq("张三");
		 List< Criteria > criteria = new Where().name("id").eq("=",id).criterias();
		 baseService.delete(ParamsUtils.converDelete(DBValue.CASHIER_ORDER_MASTER,criteria));
//		 { tableName: "user_", criteria: [{ conds: [{ "name": "name", value: "aaa","cond":"and"},{"name":"age",value:27}] },{ conds: [{ "name": "name", value: "aaa","cond":"and"},{"name":"age",value:27}] }] };
		 criteria = new Where().name("id").eq(null,ids).criterias();
		 System.out.println("criteria:"+criteria);
		 // 删除订单详情
		 baseService.deleteIn(ParamsUtils.converDelete(DBValue.CASHIER_ORDER_DETAIL,criteria));
		 // 新增日志
		 baseService.insert(ParamsUtils.converInsert("cashier_log", "type,title,detail,own",Arrays.asList("-删除","商家删除订单",selectOrderAndDetialById.toString(),"1:张三"),false));
		return 1;
	}
	public static void main(String[] args) {
		ArrayList<Object> list = new ArrayList<Object>();
		list.add("xxx");
		ArrayList<Object> arrayList = new ArrayList<Object>(list);
	     arrayList.add("list");
	     System.out.println("list:"+arrayList);
	}

	@Override
	public List<Map<String, Object>> select(SelectParams params) {
		return  baseService.selectList(params);
	}
	
	@Override
	public List<Map<String, Object>> selectChefs(Map<String, Object> params) {// ? ? ?  1 2 3
		String oneCols = "o.id id, o.tea_fee tea_fee,o.count all_count, o.people_count people_count, o.total_money total_money, o.table_num table_num, o.order_type order_type,";
		oneCols += "o.eat_type, o.remark remark, o.emp_name emp_name, o.emp_id emp_id, o.emp_num emp_num, o.reality_money reality_money, o.discount discount,";
		oneCols += "o.shop_id shop_id, o.pay_type pay_type, o.pay_mode pay_mode, o.printers printers, o.create_time create_time, o.update_time update_time, o.pay_time pay_time,";
		oneCols += "o.already_money  already_money, o.list_money list_money";
		String manyCols = "d.id detail_id, d.order_id order_id, d.item_id item_id, d.title title, d.show_img show_img, d.price price, d.vip_price vip_price, d.count count, d.spec spec, d.discount d_discount, d.speed speed";
		
		Select sql = new Select()
		 		.qcols(oneCols,manyCols)
				.from(DBValue.CASHIER_ORDER_MASTER).as("o")
				.leftJoin(DBValue.CASHIER_ORDER_DETAIL).as("d")
				.on("o.id=d.order_id")
				.where("o.shop_id = ? and o.create_time >= ?");
				// 给占位符赋值
				if( params != null ) {
					sql.setValues( params.get("shopId"));
					sql.setValues( params.get("startTime"));
				}
			    List<Map<String, Object>> selectX = baseService.selectX(sql);
			    System.out.println("selectX:"+selectX);
			    // 构造一对多。
			    List<Map<String, Object>> oneToMany = SelectResult.oneToMany(oneCols,manyCols, "master", "details", "id", selectX);
			return oneToMany;
				
	}
	
	@Override
	public Map<String, Object> selectOrderAndDetialById(Long id) {
	    Map<String, Object> master = this.selectOrderMaster(id);
	    List<Map<String, Object>> details = this.selectOrderDetails(id);
//		return orderMapper.selectOrderAndDetialById(id);
	    HashMap<String, Object> map = new HashMap<String, Object>();
	    map.put("master", master);
	    map.put("details", details);
	    return map;
	}

	@Override
	public Map<String, Object> selectOrderMaster(Long orderId) {
		// TODO Auto-generated method stub
		return  baseService.selectOne(ParamsUtils.converSelect(DBValue.CASHIER_ORDER_MASTER, "*", new Where().name("id").eq("=",orderId.toString()).criterias()));
	}

	@Override
	public List<Map<String, Object>> selectOrderDetails(Long orderId) {
		return baseService.selectList(ParamsUtils.converSelect(DBValue.CASHIER_ORDER_DETAIL, "*", new Where().name("order_id").eq("=",orderId.toString()).criterias()));
		
	}

	@Override
	@Transactional
	public Long updateOrder(List<InsertParams> params) {
		Long id = Long.parseLong( params.get(0).getOtherParams().get("id") );
		Map<String, Object> back = this.selectOrderAndDetialById(id);
		List<Map<String, Object>> details = (List<Map<String, Object>>) back.get("details");
		List<Object> ids =new ArrayList<Object>();
		details.forEach((item)->{  ids.add(item.get("id"));});
		// 删除记录
//		baseService.delete(ParamsUtils.converDelete(DBValue.CASHIER_ORDER_MASTER, new Where().name("id").eq("=",id.toString()).criterias()));
		// 更新主表
		InsertParams master = params.get(0);
//		master.setCols(master.getCols()+",update_time");
//		List<Object> values = (List<Object>) master.getValues();
//		values.add(new Date());
		ParamsUtils.addColumns(master, "update_time", new Date());
		baseService.update(ParamsUtils.converInsertToUpdate(master,new Where().name("id").eq("=", id.toString()).criterias()));
		// 删除详情，重新插入
		baseService.deleteIn(ParamsUtils.converDelete(DBValue.CASHIER_ORDER_DETAIL, new Where().name("id").eq(null,ids).criterias()));
		long add = this.insertDetails(id,params.get(1));
		// 添加日志表
		baseService.insert(ParamsUtils.converInsert(DBValue.CASHIER_LOG, "type,title,detail,own", Arrays.asList("修改","修改订单",new Gson().toJson(back),"李四"), false));
		return add;
	}

	/**
	 * 更新订单
	 * @param id
	 * @param params
	 * @return 
	 */
	@Transactional
	private long insertDetails(Long masterId,InsertParams params) {
		params.setCols(params.getCols()+",order_id");
		 List<Object> values =  params.getValues();
		 System.out.println("values:" + values);
		 // 给订单详情补上订单id
		 for (Object objects : values) {
			 ArrayList<Object> arr =  (ArrayList<Object>) objects;
			 arr.add(masterId);
		 }
		 params.setValues(values);
		 baseService.insertBatch(params);// 添加订单详情
//		 int i =9 / 0;
		return 1;
	}

	
	
}

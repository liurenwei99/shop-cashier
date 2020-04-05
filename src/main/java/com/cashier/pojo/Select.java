package com.cashier.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bouncycastle.crypto.prng.drbg.SP80090DRBG;

import com.cashier.common.DBValue;

/**
 * <p>ComplexSelectParams: 复杂的查询，多表操作</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年11月10日
 * @version 1.0  
 */
public class Select {
//	select 
//	o.id id, o.tea_fee tea_fee, o.people_count people_count, o.total_money total_money, o.table_num table_num, o.order_type order_type, 
//	o.eat_type, o.remark remark, o.emp_name emp_name, o.emp_id emp_id, o.emp_num emp_num, o.reality_put reality_put, o.discount discount,
//	o.shop_id shop_id, o.pay_type pay_type, o.pay_mode pay_mode, o.printers printers, o.create_time create_time, o.update_time update_time, o.pay_time pay_time,
//	o.already_money  already_money, o.list_money list_money,
//	
//	d.id detail_id, d.order_id order_id, d.item_id item_id, d.title title, d.show_img show_img, d.price price, d.vip_price vip_price, d.count count, d.spec spec, d.discount d_discount
//	
//	from cashier_order_master as o left join cashier_order_details as d on d.order_id=o.id where o.shop_id=#{id}
	
//	private String[] tables;// cashier_order_master c
//	private String[] qcols;// c.*,d.id 和表对应
////	private Map<String, Object> on;
////	
//	private String on;
//	private String where;
	private List<Object> values = new ArrayList<Object>();
	private StringBuffer sb =  new StringBuffer();
	public Select() {
		sb.append(" select ");
	}
	public Select from(String tables) {
		sb.append(" from " + tables);
		return this;
	}
	public Select as(String as) {
		sb.append(" " + as);
		return this;
	}
	public Select leftJoin(String tables) {
		sb.append(" left join " + tables);
//		this.tables = tables;
		return this;
	}
	public void setValues(Object ...value) {
		for (Object object : value) {
			this.values.add(object);
		}
	}
	public List<Object> getValues() {
		return this.values;
	}
	public Select qcols(String ...cols) {
		for (int i = 0; i < cols.length; i++) {
			String name = cols[i];
			sb.append(name);
			if( i != cols.length-1) {
				sb.append(",");
			}
		}
//		this.qcols = cols;
		return this;
	}
	public Select on(String on) {
		sb.append(" on " + on);
//		this.on = on;
		return this;
	}
	public Select where(String where) {
		sb.append(" where " + where);
//		this.where = where;
		return this;
	}
	public String toSql() {
		return sb.toString();
	}
//	public ComplexSelectParams on(String on, Object ...values) {
//		this.on = new HashMap<String,Object>();
//		if( on.indexOf("?") != -1  && values != null ) {
//			String[] split = on.split("\\?");
//			for (int i = 0; i < split.length; i++) {
//				this.on.put(split[i], values[i]);
//			}
//		}else {
//			this.on.put(on, "");
//		}
//		return this;
//	}
	public static void main(String[] args) {
		String oneCols = "o.id id, o.tea_fee tea_fee, o.people_count people_count, o.total_money total_money, o.table_num table_num, o.order_type order_type,";
		oneCols += "o.eat_type, o.remark remark, o.emp_name emp_name, o.emp_id emp_id, o.emp_num emp_num, o.reality_put reality_put, o.discount discount,";
		oneCols += "o.shop_id shop_id, o.pay_type pay_type, o.pay_mode pay_mode, o.printers printers, o.create_time create_time, o.update_time update_time, o.pay_time pay_time,";
		oneCols += "o.already_money  already_money, o.list_money list_money,";
		String manyCols = "d.id detail_id, d.order_id order_id, d.item_id item_id, d.title title, d.show_img show_img, d.price price, d.vip_price vip_price, d.count count, d.spec spec, d.discount d_discount ";
		
		Select sql = new Select()
		 		.qcols(oneCols,manyCols)
				.from(DBValue.CASHIER_ORDER_MASTER).as("o")
				.leftJoin(DBValue.CASHIER_ORDER_DETAIL).as("d")
				.on("o.id=d.order_id")
				.where("o.shop_id = ? and o.create_time >= ?");
				sql.setValues("2323","gre");
				String _sql = sql.toSql();
				if( _sql.indexOf("?") != -1 ) {
					String[] split = _sql.split("\\?");
					for (int i = 0; i < split.length; i++ ) {
						System.out.println(split[i] +"==="+ sql.getValues().get(i));
					}
				}
	}
	

}

package com.cashier.service.impl;

import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cashier.common.DBValue;
import com.cashier.service.BaseService;
import com.cashier.service.PrinterService;
import com.cashier.utils.ParamsUtils;

/**
 * <p>PrinterServiceImpl: 打印模块 service接口实现类</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月20日
 * @version 1.0  
 */
@Service
public class PrinterServiceImpl implements PrinterService{
	@Autowired
	private BaseService baseService;

	@Override
	public Map<String, Object> index() {
	   List<Map<String, Object>> adds = this.selectAdds();
	   Map<String, Object> map =  new HashMap<String, Object>();
		map.put("addList",adds);// 已添加的打印
		return map;
	}
	

	@Override
	public List<Map<String, Object>> selectAdds() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = baseService.selectList(ParamsUtils.converSelect(DBValue.CASHIER_PRINTER,"*", null, 1, 50, "create_time desc"));
		return list;
	}

   
	@Override
	public List<Map<String, Object>> selectPrints() {
		return null;
	}

	@Override
	public List<Object> scanPrints() {
		 PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		   DocFlavor flavor = DocFlavor.BYTE_ARRAY.PNG;
		   //可用的打印机列表(字符串数组)
		   PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
		   List<Object> list = new ArrayList<Object>();
		   for(int i=0;i<printService.length;i++){
		           	System.out.println("TestPrint.main()"+printService[i].getName());
		           	PrintService p = printService[i];
		          String name = 	"{\"p_explain\":\""+printService[i].getName()+"\"}";
		          list.add(name);
		   }
		   PrintService PS = PrintServiceLookup.lookupDefaultPrintService();
		   System.out.println("TestPrint.main()"+PS.getName());

		   return list;
	}



}

package com.cashier.utils;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import com.cashier.controller.Print;
import com.cashier.pojo.print.Row;
import com.cashier.pojo.print.RowItem;

/**
 * <p>PrintUtils: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月31日
 * @version 1.0  
 */
public class PrintUtils {
	static  PrintService printServices[] = null;
	static Map<String,PrintService> map = new HashMap<String, PrintService>();
	static {
		   PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		   DocFlavor flavor = DocFlavor.BYTE_ARRAY.PNG;
		   //可用的打印机列表(字符串数组)
		   printServices = PrintServiceLookup.lookupPrintServices(flavor, pras);
		   for (PrintService printService : printServices) {
			   System.out.println("rintService.getName():"+printService.getName());
			   map.put(printService.getName(), printService);
		   }
//		   List<Object> list = new ArrayList<Object>();
//
////		   PrinterJob job = PrinterJob.getPrinterJob();
//		   for(PrintService p : printService){
//			   if( name.equals(p.getName())) {
////				   job.setPrintService(p);
//				   print.printBiaoQian(p);
//			   }
//			   if( xp.equals(p.getName())) {
//				   print.printXiaoPiao();
//			   }
//		   }
	}
	
	public static void print(List<Row> rows) throws Exception {
		for (Row row : rows) {
			defaultPrint(row.getRows(),map.get(row.getName()));
		}
		
	}
	
	private static void defaultPrint(List<RowItem> items,PrintService service) throws Exception {
		int height = 175 + 3 * 15 + 20;

		// 通俗理解就是书、文档
		Book book = new Book();

		// 打印格式
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT);

		// 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
		Paper p = new Paper();
		p.setSize(230, height);
		p.setImageableArea(5, -20, 230, height + 20);
		pf.setPaper(p);

		// 把 PageFormat 和 Printable 添加到书中，组成一个页面
		book.append(new Print(items), pf);

		// 获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintService(service);
		job.setPageable(book);
		try {
			job.print();
		} catch (PrinterException e) {
			System.out.println("================打印出现异常");
		}
	}
}

package com.cashier.controller;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import com.alibaba.druid.sql.visitor.functions.If;
import com.cashier.pojo.print.Row;
import com.cashier.pojo.print.RowItem;

import common.WeResult;

public class Print implements Printable {
	
	private List<RowItem> rows;
	private String name;
    /**
	 * 
	 */
	public Print() {}
	public Print(List<RowItem> rows){
		this.rows = rows;
	}
	public Print(String name ){
		this.name = name;
	}
	

	@Override
	public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

		if (page > 0) {
			return NO_SUCH_PAGE;
		}
		Graphics2D g2d = (Graphics2D) g;
//		g2d.setFont(new Font("Default", Font.PLAIN, 8));
//		g2d.drawString("奇异果", 17, 25);
		if( rows != null ) {
		  for (RowItem row : rows) {
			System.out.println("Print.print()"+row);
			switch (row.getType()) {
			case "kv":// 普通的行
				g2d.drawString(row.getValue(), row.getRowX(),row.getRowY());
				break;
			case "split":// 普通的行
				g2d.drawString(row.getValue(), row.getRowX(),row.getRowY());
				break;
			case "table":// 普通的行
				g2d.drawString(row.getValue(), row.getRowX(),row.getRowY());
//				List<Object> heads = row.getHeads();
//				String headStr = "";
//				for (Object object : heads) {
//					headStr += object.toString()+"   ";
//				}
//				g2d.drawString( headStr, row.getRowX(),row.getRowY());
//				List<Object> values = row.getValues();
//				for (Object object : values) {// 这里要和头部对应，比如：2  张三  对应  id  name
//					g2d.drawString( object.toString(), row.getRowX(),row.getRowY());
//				}
				
				break;
			case "text":// 多个kv行在行展示
				g2d.drawString(row.getValue(), row.getRowX(),row.getRowY());
				break;
				
			case "tableRow":// 表格行
				g2d.drawString(row.getValue(), row.getRowX(),row.getRowY());
				break;
			case "font":// 字体
				g2d.setFont(new Font("Default", Font.PLAIN, row.getFontSize()));
				break;
			default:
				
				break;
			}
//			if("font".equals(row.getType())) {
//				g2d.setFont(new Font("Default", Font.PLAIN, row.getFontSize()));
//			}else if("kv".equals(row.getType())) {	
//				g2d.drawString(row.getName()+":"+row.getValue(), row.getRowX(), row.getRowY());
//
//			}
//			g2d.drawString(row.getValue(), row.getRowX(), row.getRowY());
		 }
        
		}
		
		
//		g2d.drawString("-------------------------------------", 7, 20);
//		g2d.drawString("收银员:" + "1001", 17, 20);
//		g2d.drawString("时间：" + new Date(), 17, 30);
//		g2d.drawString("-------------------------------------", 7, 40);
//		g2d.setFont(new Font("Default", Font.PLAIN, 10));
//		g2d.drawString("品名          单价    数量   小计", 17, 50);
//		g2d.setFont(new Font("Default", Font.PLAIN, 8));
//		g2d.drawString("生菜炒肉       13    1      10", 17, 65);
//		g2d.drawString("猪杂汤河粉    6     1      6", 17, 75);
//		g2d.drawString("合计：" + "16" + "  数量： 2", 17, 85);
//		g2d.drawString("-------------------------------------", 17, 100);
//		g2d.drawString("地址：" + "广州市番禺区节能科技园智库大厦5层5b", 17, 105);
		return PAGE_EXISTS   ;
	}
	
	
	public  void printXiaoPiao() {
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
		book.append(new Print(), pf);

		// 获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPageable(book);
		try {
			job.print();
		} catch (PrinterException e) {
			System.out.println("================打印出现异常");
		}
	}
	public  void printBiaoQian(PrintService p) throws Exception {
//		String name = "Gprinter  GP-1124D";
		// 获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintService(p);
		int height = 175 + 3 * 15 + 20;

		// 通俗理解就是书、文档
		Book book = new Book();

		// 打印格式
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT);

		// 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。0.013 0.26
		Paper page = new Paper();//  20 * 1/72*    0.39 =   4cm = 1.56英寸  
		page.setSize(120, 150);// 整张纸的宽高。
		page.setImageableArea(0, 0, 120, 150);//可绘画区域，也就是打印的面积
		pf.setPaper(page);
		// 把 PageFormat 和 Printable 添加到书中，组成一个页面
		book.append(new Print(), pf);
		job.setPageable(book);
		job.print();

	}

	public static void main(String[] args) throws Exception {
		String name = "Gprinter  GP-1124D";// 标签
		String xp = "Aibao A-5801";
			Print print = new Print();
//			print.printXiaoPiao();
			 PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
			   DocFlavor flavor = DocFlavor.BYTE_ARRAY.PNG;
			   //可用的打印机列表(字符串数组)
			   PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
			   List<Object> list = new ArrayList<Object>();

//			   PrinterJob job = PrinterJob.getPrinterJob();
			   for(PrintService p : printService){
				   if( name.equals(p.getName())) {
//					   job.setPrintService(p);
					   print.printBiaoQian(p);
				   }
				   if( xp.equals(p.getName())) {
					   print.printXiaoPiao();
				   }
			   }
			   PrintService PS = PrintServiceLookup.lookupDefaultPrintService();
			   System.out.println("TestPrint.main()"+PS.getName());

	}

}

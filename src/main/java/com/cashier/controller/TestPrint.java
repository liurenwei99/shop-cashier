package com.cashier.controller;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Media;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.MediaTray;
import javax.swing.JFileChooser;

import org.json.JSONObject;

/**
 * <p>TestPrint: </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月26日
 * @version 1.0  
 */
public class TestPrint extends Applet{
//	public static void main(String[] args) {
//		         JFileChooser fileChooser = new JFileChooser(); // 创建打印作业
//		         int state = fileChooser.showOpenDialog(null);
//		         if (state == fileChooser.APPROVE_OPTION) {
//		             File file = fileChooser.getSelectedFile(); // 获取选择的文件
//		             // 构建打印请求属性集
//		             HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
//		             // 设置打印格式，因为未确定类型，所以选择autosense
//		             DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//		             // 查找所有的可用的打印服务
//		             PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
//		             // 定位默认的打印服务
//		             PrintService defaultService = PrintServiceLookup
//		                     .lookupDefaultPrintService();
//		             // 显示打印对话框
//		             PrintService service = ServiceUI.printDialog(null, 200, 200,
//		                     printService, defaultService, flavor, pras);
//		             if (service != null) {
//		                 try {
//		                     DocPrintJob job = service.createPrintJob(); // 创建打印作业
//		                     FileInputStream fis = new FileInputStream(file); // 构造待打印的文件流
//		                     
//		                     DocAttributeSet das = new HashDocAttributeSet();
//		                     Doc doc = new SimpleDoc(fis, flavor, das);
//		                     job.print(doc, pras);
//		                 } catch (Exception e) {
//		                     e.printStackTrace();
//		                 }
//		             }
//		         }
//		     }

	 
   public static void main(String[] args) {
	   PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
	   DocFlavor flavor = DocFlavor.BYTE_ARRAY.PNG;
	   //可用的打印机列表(字符串数组)
	   PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
	   for(int i=0;i<printService.length;i++){
	           	System.out.println("TestPrint.main()"+printService[i].getName());
	            PrintService printer = printService[i];
	            Media[] objs = (Media[]) printer.getSupportedAttributeValues(Media.class, null, null);
	            for (Media obj : objs) {
	                if (obj instanceof MediaSizeName) {
	                    System.out.println("纸张型号：" + obj);
	                } else if (obj instanceof MediaTray) {
	                    System.out.println("纸张来源：" + obj);
	                }
	            }
	   }
	   PrintService printer = PrintServiceLookup.lookupDefaultPrintService();
       Media[] objs = (Media[]) printer.getSupportedAttributeValues(Media.class, null, null);
       for (Media obj : objs) {
           if (obj instanceof MediaSizeName) {
               System.out.println("纸张型号：" + obj);
           } else if (obj instanceof MediaTray) {
               System.out.println("纸张来源：" + obj);
           }
       }
}
}

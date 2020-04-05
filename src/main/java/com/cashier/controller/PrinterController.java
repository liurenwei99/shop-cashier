package com.cashier.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cashier.common.DBValue;
import com.cashier.pojo.InsertParams;
import com.cashier.pojo.UpdateParams;
import com.cashier.pojo.Where;
import com.cashier.pojo.print.Row;
import com.cashier.service.BaseService;
import com.cashier.service.PrinterService;
import com.cashier.utils.ParamsUtils;
import com.cashier.utils.PrintUtils;

import common.WeResult;

/**
 * <p>PrintController: 打印控制器</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月26日
 * @version 1.0  
 */
@RestController
@RequestMapping("/cashier/print")
public class PrinterController {

	@Autowired
	private PrinterService printerService;
	
	@Autowired
	private BaseService baseService;
	

	/**
	 * 打印机首页
	 * @return
	 */
	@RequestMapping("/index")
	public WeResult index() {
//			try {
//				Thread.sleep(2000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
		try {
			Map<String, Object> index = printerService.index();
			return WeResult.result(200, index);
		}catch(Exception e) {
			return WeResult.result(500, e.getMessage());
		}
		
	}
	/**
	 * 查询已添加的打印机
	 * @return
	 */
	@RequestMapping("/selectAdds")
	public WeResult selectAdds() {
		try {
			Map<String, Object> index = printerService.index();
			return WeResult.result(200, index);
		}catch(Exception e) {
			return WeResult.result(500, e.getMessage());
		}
		
	}
	/**
	 * 新增打印机
	 * @return
	 */
	@RequestMapping("/add")
	public WeResult addPrinter(@RequestBody InsertParams params) {
		System.out.println("PrinterController.addPrinter()"+params);
		try {
			Map<String, String> op = params.getOtherParams();
			
			Map<String, Object> one = baseService.selectOne(ParamsUtils.converSelect(DBValue.CASHIER_PRINTER, "id", 
					new Where().name("p_explain").eq("=",op.get("p_explain")).criterias() ));
			if( one != null ) {
				return WeResult.result(501,"添加失败，已存在同名打印机！");
			}
			
			Long id = baseService.insert(params);
			return WeResult.result(200, id);
		}catch(Exception e) {
			e.printStackTrace();
			return WeResult.result(500, e.getMessage());
		}
		
	}

	/**
	 * 移除打印机
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	public WeResult delete(@PathVariable("id")Long id) {
		System.out.println("PrinterController.delete()"+id);
		try {
			int delete = baseService.delete(ParamsUtils.converDelete(DBValue.CASHIER_PRINTER, new Where().name("id").eq("=", id).criterias()));
			return WeResult.result(200, delete);
		}catch(Exception e) {
			
			return WeResult.result(500, e.getMessage());
		}
		
	}
	/**
	 * 删除打印样板
	 * @return
	 */
	@DeleteMapping("/tmp/delete/{id}")
	public WeResult deleteTmp(@PathVariable("id")Long id) {
		System.out.println("PrinterController.delete()"+id);
		try {
			int delete = baseService.delete(ParamsUtils.converDelete(DBValue.CASHIER_PRINT_TMP, new Where().name("id").eq("=", id).criterias()));
			if( delete <= 0 )throw new RuntimeException("删除失败");
			return WeResult.result(200, id);
		}catch(Exception e) {
			
			return WeResult.result(500, e.getMessage());
		}
		
	}
	/**
	 * 扫码打印机
	 * @return
	 */
	@RequestMapping("/scan")
	public WeResult scan() {
		try {
			 List<Object> scanPrints = new ArrayList<Object>( printerService.scanPrints());
			 scanPrints.add("{\"p_explain\":\"路虎揽胜打印机\"}");
			 System.out.println("PrinterController.scan()"+scanPrints);
			return WeResult.result(200, scanPrints);
		}catch(Exception e) {
			e.printStackTrace();
			return WeResult.result(500, e.getMessage());
		}
		
	}
	
	/**
	 * 添加打印模板
	 * @return
	 */
	@RequestMapping("/tmp/add")
	public WeResult addTmp(@RequestBody InsertParams params) {
		System.out.println("PrinterController.addTmp():" + params);
		
		
		try {
			params.setTableName(DBValue.CASHIER_PRINT_TMP);
			Long insert = baseService.insert(params);
//			Map<String, Object> index = printerService.index();
			return WeResult.result(200, insert);
		}catch(Exception e) {
			return WeResult.result(500, e.getMessage());
		}
		
	}
	/**
	 * 修改
	 * @return
	 */
	@RequestMapping("/tmp/update")
	public WeResult updateTmp(@RequestBody UpdateParams params) {
		System.out.println("PrinterController.updateTmp():"+params);
		try {
			Map<String, String> op = params.getOtherParams();
			if( op.get("id") == null ) {
				return WeResult.result(400, "缺少id，请检查！");
			}
			params.setTableName(DBValue.CASHIER_PRINT_TMP);
			params.setCriterias(new Where().name("id").eq("=",op.get("id")).criterias());
			
//			Long insert = baseService.insert(ParamsUtils.converInsert(DBValue.CASHIER_PRINT_TMP, "name,text",Arrays.asList(maps.get("name"),maps.get("text")) , true));
			int ok = baseService.update(params);
			if( ok > 0 ) {
				return WeResult.result(200, ok);
			}
		}catch(Exception e) {
			return WeResult.result(500, e.getMessage());
		}
		return WeResult.result(500, "更新失败，未知错误！");
		
	}
	/**
	 * 更新打印机
	 */
	@RequestMapping("/updatePrinter")
	public WeResult updatePrinter(@RequestBody UpdateParams params) {
		System.out.println("PrinterController.updatePrinter():" + params );
		try {
			Map<String, Object> op = params.getCols();
			if ( op.get("id") == null ) return WeResult.result(400, "参数有误，id不能为空！");
			params.setCriterias(new Where().name("id").eq("=", op.get("id")).criterias() );
			params.setTableName(DBValue.CASHIER_PRINTER);
			int update  =  baseService.update(params);
//					ParamsUtils.converUpdate(DBValue.CASHIER_PRINTER,maps, "name,p_explain,page_tmp,tmp_name,status,img,update_time,width,height", new Where().name("id").eq("=", id).criterias()));
			//			Map<String, Object> index = printerService.index();
			return WeResult.result(200, update);
		}catch(Exception e) {
			e.printStackTrace();
			return WeResult.result(500, e.getMessage());
		}
		
	}
	
	/**
	 *  打印
	 */
	@RequestMapping("/print")
	public WeResult print(List<Row> rows) {
		System.out.println("PrinterController.print():" + rows);
		try {
			PrintUtils.print(rows);
//			this.printXiaoPiao(rows);// 打印小票
			return WeResult.result(200, 0);
		}catch(Exception e) {
			e.printStackTrace();
			return WeResult.result(500, e.getMessage());
		}
		
	}
	/**
	 * 查询打印模板列表
	 */
	@RequestMapping("/selectTmp")
	public WeResult selectTmp() {
		System.out.println("PrinterController.selectTmp()");
		try {
			List<Map<String, Object>> selectList = baseService.selectList(ParamsUtils.converSelect(DBValue.CASHIER_PRINT_TMP, "*", null));
			return WeResult.result(200, selectList);
		}catch(Exception e) {
			e.printStackTrace();
			return WeResult.result(500, e.getMessage());
		}
		
	}
	/**打印小票*/
	public void printXiaoPiao(List<Row> rows) {
//		int height = 175 + 3 * 15 + 20;
//
//		// 通俗理解就是书、文档
//		Book book = new Book();
//
//		// 打印格式
//		PageFormat pf = new PageFormat();
//		pf.setOrientation(PageFormat.PORTRAIT);
//
//		// 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
//		Paper p = new Paper();
//		p.setSize(230, height);
//		p.setImageableArea(5, -20, 230, height + 20);
//		pf.setPaper(p);
//
//		// 把 PageFormat 和 Printable 添加到书中，组成一个页面
//		book.append(new Print(rows), pf);
//
//		// 获取打印服务对象
//		PrinterJob job = PrinterJob.getPrinterJob();
//		job.setPageable(book);
//		try {
//			job.print();
//		} catch (PrinterException e) {
//			System.out.println("================打印出现异常");
//		}
	}
}

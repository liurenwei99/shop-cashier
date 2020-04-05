package com.cashier.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.expr.NewArray;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>DownloadController: 下载控制器</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年12月7日
 * @version 1.0  
 */
@Controller
@RequestMapping("download")
public class DownloadController {

	
	/**
	 * 下载lodop控件
	 * @throws IOException 
	 */
	@RequestMapping("/print")
	public ResponseEntity<byte[]> print_lodop(HttpServletResponse response){
		System.out.println("DownloadController.print_lodop()开始下载。。。");
		 HttpHeaders headers = new HttpHeaders();    
		 String fileName = "CLodop_Setup_for_Win32NT.exe";
//	     String path = "E:\\dev-wei-19813\\antdesign\\web打印\\lodop\\";
	     String path = "D:\\site\\java\\web_print";// 线上
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);    
	        headers.setContentDispositionFormData("attachment", fileName); 
	        InputStream is = null;
	        byte[] buff = new byte[1024];
			try {
				is = new FileInputStream(new File(path,fileName));
				buff = new byte[is.available()];
		        is.read(buff);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        return new ResponseEntity<byte[]>(buff,headers, HttpStatus.CREATED);  
	}
}

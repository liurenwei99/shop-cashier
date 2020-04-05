
package com.cashier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 收银系统主启动类: Cashier_Main_Start
 * Company: www.91changqi.com
 * @author liurenwei
 * @Date 2019年10月10日   
 * @version 1.0
 * 
 */  
@SpringBootApplication
@MapperScan(basePackages = {"com.cashier.mapper"})
@EnableTransactionManagement
public class Cashier_Main_Start {
	 
	public static void main(String[] args) {
		SpringApplication.run(Cashier_Main_Start.class);   
	}
	
} 
//./configure --add-module=/usr/local/nginx8/fastdfs-nginx-module/src
/**  小票
	  [
		 {"text":"<div>","type":"text"},
		 {"text":"<div style='font-size:12px'>************广州分店**********</div>","type":"text"},
		 {"text":"<div style='font-size:12px'>流水：_?_</div>","type":"text","vari":["cashier_order_master.stream_no"]},
		 {"text":"<div style='font-size:12px'>收银员：_?_</div>","type":"text","vari":["cashier_order_master.emp_name"]},
		 {"text":"<div style='font-size:12px'>时间：_?_</div>","type":"text","vari":["cashier_order_master.create_time"]},
		 {"text":"<div style='font-size:13px;'>---------------------------------</div>","type":"text","vari":""},
		 {"text":"<table>","type":"text"},			 
		 {"text":"<tr style='font-size:13px'><td style='width: 50%'>品名</td><td>单价</td><td>数量</td><td>小计</td></tr>","type":"text"},
		
		 {"text":"<tr style='font-size:13px'><td >_?_</td><td>_?_</td><td>_?_</td><td>_?_</td></tr>","type":"list","item":"cashier_order_details","vari":["title","price","count","smallcount"]},
		 {"text":"</table>","type":"text"},
		 {"text":"<div style='font-size:13px'>---------------------------------</div>","type":"text","vari":""},
		 {"text":"<div style='font-size:13px'>总金额：<span style='font-size:14px;font-weight:700'>_?_</span> <span style='margin-left:10px'>数量：</span><span style='font-size:14px;font-weight:700'>_?_</span></div>","type":"text","vari":["cashier_order_master.total_money","cashier_order_master.count"]},
		 {"text":"</div>","type":"text"}
		]
    不干胶标签
        [
	     {"text":"<div>","type":"text"},
		 {"text":"<div style='font-size:12px'>_?_<span style='margin-left:8px'>【_?_/_?_】</span></div>","type":"text","vari":["cashier_order_details.title","cashier_order_details.speed","cashier_order_master.count"]},
		 {"text":"<div style='font-size:12px'>单价：_?_</div>","type":"text","vari":["cashier_order_details.price"]},
		 {"text":"<div style='font-size:12px'>流水：_?_</div>","type":"text","vari":["cashier_order_master.stream_no"]},
		 {"text":"<div style='font-size:12px'>时间：_?_</div>","type":"text","vari":["cashier_order_master.create_time"]},
		 {"text":"</div>","type":"text"}
	   ]
	   
	   电脑如何连接打印机：
	    1、下载对应的驱动
	    2、打开电脑的控制面板，找到“设备和打印机”
	    3、点击添加打印机
	    4、如果找不到设备，点击“我需要的打印机未列出”
	    5、选择“通过手动设置添加本地打印机或者网络打印机”，然后下一步
	    6、选择“使用现有的端口”，再下拉选择“xxx(USB虚拟打印机端口)”,然后下一步
	    7、点击“从磁盘安装”，找到该打印机的驱动下面的.inf文件，打开，然后下一步        
	    8、选择“使用当前已安装的驱动程序”，下一步
	    9、给该打印机设置一个名字或者默认即可
	    10、选择“共享此打印机..”，然后下一步
	    11、点击完成
	    安装完打印机后，刷新页面或者点击重新扫描，即可看到本地电脑已连接的打印机。
	 让收银系统连接打印机：
	    1、输入打印机类型来做个区分，即是用来打印什么的
	    2、输入打印的纸宽和高，毫米(mm)为单位。注：如果不给高，那么，高是自适应的，比如小票打印就不用设置高。
	    3、选择对应的样板。
	    4、添加
	    
	    
 */
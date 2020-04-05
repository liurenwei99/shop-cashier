package com.cashier.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.Data;
import lombok.ToString;

/**
 * <p>IndexParams: 首页参数实体，用于封装接收前端请求的参数</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
@Data
@ToString
public class IndexParams {
	private String shopId;
	private String catId;
}

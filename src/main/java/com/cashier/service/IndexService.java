package com.cashier.service;

import java.util.Map;

import com.cashier.pojo.IndexParams;

/**
 * <p>IndexService: 首页的service接口 </p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
public interface IndexService {
    /**
     * 根据店铺id，初始化首页数据
     * @param params {@link com.cashier.pojo.IndexParams}
     * @return map对象
     */
	Map<String, Object> index(IndexParams params);
}

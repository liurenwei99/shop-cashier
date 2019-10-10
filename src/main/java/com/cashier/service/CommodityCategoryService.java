package com.cashier.service;

import com.cashier.pojo.CommodityCategory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommodityCategoryService {
    //查询门店显示类目
    List<CommodityCategory> stateSelect(String storeNumber);
}

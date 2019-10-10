package com.cashier.service;

import com.cashier.pojo.CommoditySpecifications;

import java.util.List;

public interface CommoditySpecificationsService {
    //商品编号查询商品规格
    List<CommoditySpecifications> commodityNumberSelect(String commodityNumber);
}

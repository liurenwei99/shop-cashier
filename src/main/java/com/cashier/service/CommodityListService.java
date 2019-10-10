package com.cashier.service;

import com.cashier.pojo.CommodityList;
import com.cashier.pojo.form.CommodityListForm;

import java.util.List;

public interface CommodityListService {
    //查询门店热销商品
    List<CommodityListForm> signSelect(String storeNumber);
    //根据类目编号查询商品
    List<CommodityListForm> categoryNumberSelect(String categoryNumber);
    //根据商品名称查找商品
    List<CommodityListForm> tradeNameSelect(String tradeName, String storeNumber);
    //根据商品序号查找商品
    List<CommodityListForm> commodityIdSelect(Integer commodityId, String storeNumber);
}

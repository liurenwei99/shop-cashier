package com.cashier.service;

import com.cashier.pojo.CommodityList;
import com.cashier.pojo.form.CommodityListForm;

import java.util.List;

public interface CommodityListFormService {
    //商品类目
    List<CommodityListForm> CommodityListForm(List<CommodityList> commodityLists);
}

package com.cashier.service.impl;

import com.cashier.pojo.CommodityList;
import com.cashier.mapper.*;
import com.cashier.pojo.form.*;
import com.cashier.service.CommodityListFormService;
import com.cashier.service.CommodityListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityListImpl implements CommodityListService {
    @Autowired
    private CommodityListMapper commodityListMapper;
    @Autowired
    private CommodityListFormService commodityListFormService;

    @Override
    public List<CommodityListForm> signSelect(String storeNumber) {
        System.out.println("commodityListMapper:"+commodityListMapper);
        List<CommodityList> commodityListList = commodityListMapper.signSelect(storeNumber);
        List<CommodityListForm> commodityListFormList = commodityListFormService.CommodityListForm(commodityListList);
        return commodityListFormList;
    }

    @Override
    public List<CommodityListForm> categoryNumberSelect(String categoryNumber) {
        List<CommodityList> commodityListList =  commodityListMapper.categoryNumberSelect(categoryNumber);
        List<CommodityListForm> commodityListFormList = commodityListFormService.CommodityListForm(commodityListList);
        return commodityListFormList;
    }

    @Override
    public List<CommodityListForm> tradeNameSelect(String tradeName, String storeNumber) {
        List<CommodityList> commodityListList = commodityListMapper.tradeNameSelect(tradeName , storeNumber);
        List<CommodityListForm> commodityListFormList = commodityListFormService.CommodityListForm(commodityListList);
        return commodityListFormList;
    }

    @Override
    public List<CommodityListForm> commodityIdSelect(Integer commodityId, String storeNumber) {
        List<CommodityList> commodityListList = commodityListMapper.commodityIdSelect(commodityId , storeNumber);
        List<CommodityListForm> commodityListFormList = commodityListFormService.CommodityListForm(commodityListList);
        return commodityListFormList;
    }
}

package com.cashier.service.impl;

import com.cashier.pojo.CommoditySpecifications;
import com.cashier.mapper.CommoditySpecificationsMapper;
import com.cashier.service.CommoditySpecificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommoditySpecificationsImpl implements CommoditySpecificationsService {
    @Autowired
    private CommoditySpecificationsMapper commoditySpecificationsMapper;
    @Override
    public List<CommoditySpecifications> commodityNumberSelect(String commodityNumber) {
        return commoditySpecificationsMapper.commodityNumberSelect(commodityNumber);
    }
}

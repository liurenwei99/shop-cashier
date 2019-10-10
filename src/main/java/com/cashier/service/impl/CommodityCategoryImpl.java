package com.cashier.service.impl;

import com.cashier.pojo.CommodityCategory;
import com.cashier.mapper.CommodityCategoryMapper;
import com.cashier.service.CommodityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityCategoryImpl implements CommodityCategoryService {
    @Autowired
    private CommodityCategoryMapper commodityCategoryMapper;

    @Override
    public List<CommodityCategory> stateSelect(String storeNumber) {
        return commodityCategoryMapper.stateSelect(storeNumber);
    }
}

package com.cashier.service.impl;

import com.cashier.pojo.CommodityList;
import com.cashier.pojo.form.CommodityListForm;
import com.cashier.service.CommodityListFormService;
import com.cashier.service.CommoditySpecificationsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityListFormImpl implements CommodityListFormService {
    @Autowired
    private CommoditySpecificationsService commoditySpecificationsService;

    @Override
    public List<CommodityListForm> CommodityListForm(List<CommodityList> commodityLists) {
        List<CommodityListForm> commodityListFormList = new ArrayList<>();
        for(CommodityList commodityList : commodityLists){
            CommodityListForm commodityListForm = new CommodityListForm();
            BeanUtils.copyProperties(commodityList,commodityListForm);
            commodityListForm.setCommoditySpecifications(commoditySpecificationsService.commodityNumberSelect(commodityList.getCommodityNumber()));
            commodityListFormList.add(commodityListForm);
        }
        return commodityListFormList;
    }
}

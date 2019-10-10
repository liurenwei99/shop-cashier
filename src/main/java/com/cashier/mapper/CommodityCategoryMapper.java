package com.cashier.mapper;

import com.cashier.pojo.CommodityCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface CommodityCategoryMapper {
    //查询门店显示类目
   List<CommodityCategory> stateSelect(@Param("storeNumber") String storeNumber);
}

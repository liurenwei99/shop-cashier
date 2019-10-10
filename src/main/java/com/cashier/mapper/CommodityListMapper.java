package com.cashier.mapper;

import com.cashier.pojo.CommodityList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityListMapper {
    //查询门店热销商品
    List<CommodityList> signSelect(@Param("storeNumber") String storeNumber);
    //根据类目编号查询商品
    List<CommodityList> categoryNumberSelect(@Param("categoryNumber") String categoryNumber);
    //根据商品名称查找商品
    List<CommodityList> tradeNameSelect(@Param("tradeName") String tradeName, @Param("storeNumber") String storeNumber);
    //根据商品序号查找商品
    List<CommodityList> commodityIdSelect(@Param("commodityId") Integer commodityId, @Param("storeNumber") String storeNumber);
}

package com.cashier.mapper;

import com.cashier.pojo.CommoditySpecifications;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommoditySpecificationsMapper {
    //商品编号查询商品规格
   List<CommoditySpecifications> commodityNumberSelect(@Param("commodityNumber") String commodityNumber);
}

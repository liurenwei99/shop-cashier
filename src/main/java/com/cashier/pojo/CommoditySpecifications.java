package com.cashier.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 商品规格表
 */
@Entity
@Data
public class CommoditySpecifications {
    @Id
    private String specificationsId;//规格Id
    private String specificationsTexture;//规格口感
    private String specificationsSize;//规格尺寸
    private String specificationsReserve;//规格预留
    private String specificationsReserve1;//规格预留
    private BigDecimal specificationsCost;//店内价格
    private BigDecimal specificationsAbroad;//外卖价格
    private Integer specificationsStock;//规格库存
    private BigDecimal boxCharges;//餐盒费
    private String commodityNumber;//商品编号
    private Timestamp createTime;//创建时间
}

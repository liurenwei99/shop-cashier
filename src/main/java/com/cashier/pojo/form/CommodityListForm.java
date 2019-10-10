package com.cashier.pojo.form;

import com.cashier.pojo.CommoditySpecifications;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
public class CommodityListForm {
    private String commodityNumber;
    private Integer commodityId;
    private String formulationNumber;
    private String categoryNumber;
    private String tradeName;
    private BigDecimal internalPrice;
    private BigDecimal takeawayPrice;
    private BigDecimal boxCharges;
    private Integer stock;
    private Integer upperShelf;
    private String commodityClassification;
    private Integer commodityOption;
    private Integer sign;
    private String commodityDescription;
    private String commodityPictures;
    private Integer salesVolume;
    private Timestamp createTime;
    private String categoryName;//类目名
    private List<CommoditySpecifications> commoditySpecifications;//商品规格
}

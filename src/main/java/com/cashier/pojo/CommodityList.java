package com.cashier.pojo;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 商品表
 * 周鹏
 * 2019/6/12
 */
@Entity
@Data
public class CommodityList {
  @Id
  private String commodityNumber;
  private String storeNumber;
  private String templateNumber;//模板编号
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

  @Override
  public String toString() {
    return "CommodityList{" +
            "commodityNumber='" + commodityNumber + '\'' +
            ", commodityId=" + commodityId +
            ", formulationNumber='" + formulationNumber + '\'' +
            ", categoryNumber='" + categoryNumber + '\'' +
            ", tradeName='" + tradeName + '\'' +
            ", internalPrice=" + internalPrice +
            ", takeawayPrice=" + takeawayPrice +
            ", boxCharges=" + boxCharges +
            ", stock=" + stock +
            ", upperShelf=" + upperShelf +
            ", commodityClassification='" + commodityClassification + '\'' +
            ", commodityOption=" + commodityOption +
            ", sign=" + sign +
            ", commodityDescription='" + commodityDescription + '\'' +
            ", commodityPictures='" + commodityPictures + '\'' +
            ", salesVolume=" + salesVolume +
            ", createTime=" + createTime +
            '}';
  }

}

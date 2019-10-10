package com.cashier.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 商品类目表
 * 周鹏
 * 2019/6/12
 */
@Entity
@Data
public class CommodityCategory {

  @Id
  private String categoryNumber;
  private String templateNumber;//模板类目编号
  private Integer categoryId;
  private Integer typeId;
  private Integer operationalLevel;
  private String categoryName;
  private Integer categoryState;
  private String storeNumber;
  private Timestamp createTime;
  @Override
  public String toString() {
    return "CommodityCategory{" +
            "categoryNumber='" + categoryNumber + '\'' +
            ", categoryId=" + categoryId +
            ", typeId=" + typeId +
            ", operationalLevel=" + operationalLevel +
            ", categoryName='" + categoryName + '\'' +
            ", categoryState=" + categoryState +
            ", storeNumber='" + storeNumber + '\'' +
            ", createTime=" + createTime +
            '}';
  }
}

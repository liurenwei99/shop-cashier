package com.cashier.pojo;
public class CashierLog{

	// 自增长主键
	private java.lang.Long id;

	// 日志类型，增删改查
	private java.lang.String type;

	// 简短描述
	private java.lang.String title;

	// 店铺id
	private java.lang.String shopId;

	// 详细描述
	private java.lang.String detail;

	// 操作人，id+名字等
	private java.lang.String own;

	// 创建时间
	private java.sql.Timestamp createTime;


	public void setId(java.lang.Long id){
		this.id=id;
	}
	public java.lang.Long getId(){
		return id;
	}


	public void setType(java.lang.String type){
		this.type=type;
	}
	public java.lang.String getType(){
		return type;
	}


	public void setTitle(java.lang.String title){
		this.title=title;
	}
	public java.lang.String getTitle(){
		return title;
	}


	public void setShopId(java.lang.String shopId){
		this.shopId=shopId;
	}
	public java.lang.String getShopId(){
		return shopId;
	}


	public void setDetail(java.lang.String detail){
		this.detail=detail;
	}
	public java.lang.String getDetail(){
		return detail;
	}


	public void setOwn(java.lang.String own){
		this.own=own;
	}
	public java.lang.String getOwn(){
		return own;
	}


	public void setCreateTime(java.sql.Timestamp createTime){
		this.createTime=createTime;
	}
	public java.sql.Timestamp getCreateTime(){
		return createTime;
	}

}
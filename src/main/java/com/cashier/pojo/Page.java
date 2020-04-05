package com.cashier.pojo;

/**
 * <p>Page: 分页 用于封装分页信息</p>  
 * <p>Company: www.91changqi.com</p>  
 * @author liurenwei
 * @Date 2019年10月13日
 * @version 1.0  
 */
public class Page {
    private Integer startNum;//起始记录 
    private Integer pageSize=20;// 默认查询20条
    private Integer currPage=1;// 默认为第一页
    public Page() {
    	super();
	}
    public Page(Integer currPage, Integer pageSize) {
    	this.setCurrPage(currPage);
    	this.setPageSize(pageSize);
	}
    
    public Integer getStartNum() {
		return startNum;
	}
	public void setStartNum(Integer startNum) {
		this.startNum = startNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		
		this.pageSize = pageSize;
	}
	public Integer getCurrPage() {
		
		return currPage;
	}
	public void setCurrPage(Integer currPage) {
		if( currPage != null ) {
			setStartNum((currPage-1) * this.pageSize);
		}
		this.currPage = currPage;
	}
	@Override
	public String toString() {
		return "Page [startNum=" + startNum + ", pageSize=" + pageSize + ", currPage=" + currPage + "]";
	}
    
	
	
}

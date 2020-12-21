package com.fb.springbootdemo.model;

public class PageInfo {
	private Integer startIndex = 1;
	private Integer pageSize = 30;
	private Integer pageNo;
	private Integer count;

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "PageInfo [startIndex=" + startIndex + ", pageSize=" + pageSize + ", pageNo=" + pageNo + ", count="
				+ count + "]";
	}

}

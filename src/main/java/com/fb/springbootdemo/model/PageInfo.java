package com.fb.springbootdemo.model;

public class PageInfo {
	private Integer startIndex;
	private Integer endIndex;
	private Integer pageNo;
	private Integer count;

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
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
		return "PageInfo [startIndex=" + startIndex + ", endIndex=" + endIndex + ", pageNo=" + pageNo + ", count="
				+ count + "]";
	}

}

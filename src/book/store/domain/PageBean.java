package book.store.domain;

import java.util.List;

public class PageBean {
	
	// 当前页
	private Integer currentPage;
	// 总记录数
	private Integer totalCount;
	// 每页显示的记录数
	private Integer pageSize;
	
	// 总页数, 使用总记录数除以每页记录数，只需要提供get方法
//	private Integer totalPage;
	
	// 开始位置，使用(currentPage - 1) * pageSize，只需要提供get方法
//	private Integer begin;
	
	// 条件查询中的条件信息
	private String url;
	
	// 每页记录的list集合
	private List<Book> list;
	
	
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalPage() {
		Integer totalPage = this.totalCount / this.pageSize;
		return (this.totalCount % this.pageSize == 0) ? totalPage : totalPage + 1;
	}
	public Integer getBegin() {
		return (currentPage - 1) * pageSize;
	}
	public List<Book> getList() {
		return list;
	}
	public void setList(List<Book> list) {
		this.list = list;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}

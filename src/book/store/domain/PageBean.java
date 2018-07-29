package book.store.domain;

import java.util.List;

public class PageBean {
	
	// ��ǰҳ
	private Integer currentPage;
	// �ܼ�¼��
	private Integer totalCount;
	// ÿҳ��ʾ�ļ�¼��
	private Integer pageSize;
	
	// ��ҳ��, ʹ���ܼ�¼������ÿҳ��¼����ֻ��Ҫ�ṩget����
//	private Integer totalPage;
	
	// ��ʼλ�ã�ʹ��(currentPage - 1) * pageSize��ֻ��Ҫ�ṩget����
//	private Integer begin;
	
	// ������ѯ�е�������Ϣ
	private String url;
	
	// ÿҳ��¼��list����
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

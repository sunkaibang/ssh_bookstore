package book.store.domain;

import java.util.HashSet;
import java.util.Set;

public class Category {
	private Integer cid;
	private String cname;
	
	private Set<Book> bookSet = new HashSet<Book>();

	public Category() {
		super();
	}

	public Category(Integer cid, String cname, Set<Book> bookSet) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.bookSet = bookSet;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Set<Book> getBookSet() {
		return bookSet;
	}

	public void setBookSet(Set<Book> bookSet) {
		this.bookSet = bookSet;
	}

}

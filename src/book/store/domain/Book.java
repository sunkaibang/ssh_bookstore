package book.store.domain;

import java.util.HashSet;
import java.util.Set;

public class Book {
	private Integer bid;
	private String bname;
	private Double bprice;
	private String bauther;
	private String bimage;
	
	private Category category;
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();

	public Book() {
		super();
	}


	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public Double getBprice() {
		return bprice;
	}

	public void setBprice(Double bprice) {
		this.bprice = bprice;
	}

	public String getBauther() {
		return bauther;
	}

	public void setBauther(String bauther) {
		this.bauther = bauther;
	}


	public String getBimage() {
		return bimage;
	}


	public void setBimage(String bimage) {
		this.bimage = bimage;
	}


	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
}

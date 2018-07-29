package book.store.domain;

import java.math.BigDecimal;

/**
 * 
 * 购物车条目
 *  每个条目有数和价格
 *  计算该条目的总价格
 */
public class CartItem {

	private Book book;				// 商品
	private Integer count;			// 数量
	
	// 当前条目的总价格
	public double getTotal() {
		BigDecimal bookPrice = new BigDecimal(String.valueOf(book.getBprice()));
		
		return bookPrice.multiply(new BigDecimal(count + "")).doubleValue();
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}

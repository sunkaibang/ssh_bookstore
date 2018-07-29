package book.store.domain;

import java.math.BigDecimal;

/**
 * 
 * ���ﳵ��Ŀ
 *  ÿ����Ŀ�����ͼ۸�
 *  �������Ŀ���ܼ۸�
 */
public class CartItem {

	private Book book;				// ��Ʒ
	private Integer count;			// ����
	
	// ��ǰ��Ŀ���ܼ۸�
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

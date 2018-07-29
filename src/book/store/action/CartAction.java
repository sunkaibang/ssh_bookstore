package book.store.action;

import org.apache.struts2.ServletActionContext;

import book.store.domain.Book;
import book.store.domain.Cart;
import book.store.domain.CartItem;
import book.store.service.BookService;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物车的action
 * 
 */
public class CartAction extends ActionSupport {

	private static final long serialVersionUID = 203894274862422418L;

	private CartItem cartItem;
	private BookService bookService;
	private Integer bid;

	// 添加购物车的条目
	public String addCartItem() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		
		Book book = bookService.findByBid(bid);
		cartItem.setBook(book);
		cart.addCartItem(cartItem);
		
		return "cartItem_list";
	}
	
	// 删除购物车条目
	public String deleteCartItem() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		cart.deleteCartItem(bid);
		
		return "cartItem_list";
	}
	
	// 清空购物车
	public String clearCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		cart.clearCart();
		
		return "cartItem_list";
	}
	
	

	public CartItem getCartItem() {
		return cartItem;
	}
	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}

	public BookService getBookService() {
		return bookService;
	}
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}


	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	
	
}

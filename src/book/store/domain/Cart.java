package book.store.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车
 *  购物车可以对购物车中的条目进行添加，删除，清空和计算总价
 *
 */
public class Cart {
	
	private Map<Integer, CartItem> cart = new HashMap<Integer, CartItem>();
	
	// 添加购物车条目，要判断添加的购物车条目是否存在
	public void addCartItem(CartItem cartItem) {
		if (cart.containsKey(cartItem.getBook().getBid())) {
			CartItem item = cart.get(cartItem.getBook().getBid());
			cartItem.setCount(item.getCount() + cartItem.getCount());
			cart.put(cartItem.getBook().getBid(), cartItem);
		} else {
			cart.put(cartItem.getBook().getBid(), cartItem);
		}
	}
	
	// 计算购物车总价
	public double getTotal() {
		BigDecimal total = new BigDecimal("0");
		
		for(CartItem item : cart.values()) {
			BigDecimal itemTotal = new BigDecimal(String.valueOf(item.getTotal()));
			total = total.add(itemTotal);
		}
		return total.doubleValue();
	}
	
	// 删除某个条目
	public void deleteCartItem(Integer bid) {
		if (cart.containsKey(bid)) {
			cart.remove(bid);
		}
	}
	
	// 清空
	public void clearCart() {
		cart.clear();
	}
	
	public Collection<CartItem> getCartItems() {
		return cart.values();
	}
}

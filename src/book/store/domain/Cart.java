package book.store.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * ���ﳵ
 *  ���ﳵ���ԶԹ��ﳵ�е���Ŀ������ӣ�ɾ������պͼ����ܼ�
 *
 */
public class Cart {
	
	private Map<Integer, CartItem> cart = new HashMap<Integer, CartItem>();
	
	// ��ӹ��ﳵ��Ŀ��Ҫ�ж���ӵĹ��ﳵ��Ŀ�Ƿ����
	public void addCartItem(CartItem cartItem) {
		if (cart.containsKey(cartItem.getBook().getBid())) {
			CartItem item = cart.get(cartItem.getBook().getBid());
			cartItem.setCount(item.getCount() + cartItem.getCount());
			cart.put(cartItem.getBook().getBid(), cartItem);
		} else {
			cart.put(cartItem.getBook().getBid(), cartItem);
		}
	}
	
	// ���㹺�ﳵ�ܼ�
	public double getTotal() {
		BigDecimal total = new BigDecimal("0");
		
		for(CartItem item : cart.values()) {
			BigDecimal itemTotal = new BigDecimal(String.valueOf(item.getTotal()));
			total = total.add(itemTotal);
		}
		return total.doubleValue();
	}
	
	// ɾ��ĳ����Ŀ
	public void deleteCartItem(Integer bid) {
		if (cart.containsKey(bid)) {
			cart.remove(bid);
		}
	}
	
	// ���
	public void clearCart() {
		cart.clear();
	}
	
	public Collection<CartItem> getCartItems() {
		return cart.values();
	}
}

package book.store.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import book.store.domain.Order;
import book.store.domain.User;

public class OrderDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	// 保存订单
	public void saveOrder(Order order) {
		hibernateTemplate.save(order);
	}
	
	// 按用户查询所有订单
	public List<Order> findOrdersByUser(User user) {
		List<Order> orderList = (List<Order>) hibernateTemplate.find("from Order where owner=?", user);
		return orderList;
	}
	
	// 通过订单号查询订单
	public Order findOrderByOid(Integer oid) {
		List<Order> orderList = (List<Order>) hibernateTemplate.find("from Order where oid=?", oid);
		if (orderList.size() > 0)
			return orderList.get(0);
		else 
			return null;
	}
	
	
	//修改订单
	public void updateOrder(Order order) {
		hibernateTemplate.update(order);
	}
	
	
	// 查询所有订单
	public List<Order> findAllOrders() {
		List<Order> orderList = (List<Order>) hibernateTemplate.find("from Order", null);
		return orderList;
	}
	
	// 按订单状态查找订单
	public List<Order> findOrderByState(int i) {
		List<Order> orderList = (List<Order>) hibernateTemplate.find("from Order where state=?", i);
		return orderList;
	}
	
	// 删除订单
	public void deleteOrder(Order order) {
		hibernateTemplate.delete(order);
		
	}

}

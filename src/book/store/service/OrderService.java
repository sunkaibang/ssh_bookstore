package book.store.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import book.store.dao.OrderDao;
import book.store.domain.Order;
import book.store.domain.User;

@Transactional
public class OrderService {

	private OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// 保存订单项
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}
	
	// 按用户查询所有订单
	public List<Order> findOrdersByUser(User user) {
		return orderDao.findOrdersByUser(user);
	}
	
	// 查询订单
	public Order findOrderByOid(Integer oid) {
		return orderDao.findOrderByOid(oid);
	}
	
	// 确认收货
	public void confirm(Integer oid) {
		Order order = orderDao.findOrderByOid(oid);
		order.setState(4);
		orderDao.updateOrder(order);
	}
	
	// 修改付钱之后的订单状态，修改到2
	public void pay(Order order) {
		order.setState(2);
		orderDao.updateOrder(order);
	}
	
	// 查找所有订单
	public List<Order> findAllOrders() {
		return orderDao.findAllOrders();
	}
	
	// 按照订单状态查找订单
	public List<Order> findOrderByState(int i) {
		return orderDao.findOrderByState(i);
	}
	
	// 修改订单状态
	public void modifyOrderState(Integer oid, Integer i) {
		Order order = orderDao.findOrderByOid(oid);
		order.setState(i);
		orderDao.updateOrder(order);
	}
	
	// 删除订单
	public void deleteOrder(Integer oid) {
		Order order = orderDao.findOrderByOid(oid);
		orderDao.deleteOrder(order);
	}
	
	// 修改订单
	public Order updateOrder(Integer oid, Order newOrder) {
		Order oldOrder = orderDao.findOrderByOid(oid);
		oldOrder.setState(newOrder.getState());
		oldOrder.setAdress(newOrder.getAdress());
		orderDao.updateOrder(oldOrder);
		return oldOrder;
	}
}

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

	// ���涩����
	public void saveOrder(Order order) {
		orderDao.saveOrder(order);
	}
	
	// ���û���ѯ���ж���
	public List<Order> findOrdersByUser(User user) {
		return orderDao.findOrdersByUser(user);
	}
	
	// ��ѯ����
	public Order findOrderByOid(Integer oid) {
		return orderDao.findOrderByOid(oid);
	}
	
	// ȷ���ջ�
	public void confirm(Integer oid) {
		Order order = orderDao.findOrderByOid(oid);
		order.setState(4);
		orderDao.updateOrder(order);
	}
	
	// �޸ĸ�Ǯ֮��Ķ���״̬���޸ĵ�2
	public void pay(Order order) {
		order.setState(2);
		orderDao.updateOrder(order);
	}
	
	// �������ж���
	public List<Order> findAllOrders() {
		return orderDao.findAllOrders();
	}
	
	// ���ն���״̬���Ҷ���
	public List<Order> findOrderByState(int i) {
		return orderDao.findOrderByState(i);
	}
	
	// �޸Ķ���״̬
	public void modifyOrderState(Integer oid, Integer i) {
		Order order = orderDao.findOrderByOid(oid);
		order.setState(i);
		orderDao.updateOrder(order);
	}
	
	// ɾ������
	public void deleteOrder(Integer oid) {
		Order order = orderDao.findOrderByOid(oid);
		orderDao.deleteOrder(order);
	}
	
	// �޸Ķ���
	public Order updateOrder(Integer oid, Order newOrder) {
		Order oldOrder = orderDao.findOrderByOid(oid);
		oldOrder.setState(newOrder.getState());
		oldOrder.setAdress(newOrder.getAdress());
		orderDao.updateOrder(oldOrder);
		return oldOrder;
	}
}
